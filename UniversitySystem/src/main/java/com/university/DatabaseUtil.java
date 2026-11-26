package com.university;

import java.io.IOException;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DatabaseUtil {
    private DatabaseUtil() {
    }

    public static Connection getConnection() throws SQLException {
        String databaseUrl = System.getenv("DATABASE_URL");
        if (databaseUrl == null || databaseUrl.isBlank()) {
            databaseUrl = readDatabaseUrlFromFiles();
        }
        if (databaseUrl == null || databaseUrl.isBlank()) {
            throw new SQLException(
                    "DATABASE_URL is not configured. Copy the repository's .env.example to .env "
                            + "and set your Neon PostgreSQL connection string.");
        }

        DatabaseConfiguration configuration = parseDatabaseUrl(databaseUrl.trim());
        return DriverManager.getConnection(
                configuration.jdbcUrl,
                configuration.username,
                configuration.password);
    }

    public static void initializeDatabase() throws SQLException {
        try (Connection connection = getConnection()) {
            connection.setAutoCommit(false);
            try (var statement = connection.createStatement()) {
                statement.execute("""
                        CREATE TABLE IF NOT EXISTS programs (
                            id SERIAL PRIMARY KEY,
                            name VARCHAR(150) NOT NULL UNIQUE
                        )
                        """);
                statement.execute("""
                        CREATE TABLE IF NOT EXISTS students (
                            id SERIAL PRIMARY KEY,
                            first_name VARCHAR(100) NOT NULL,
                            last_name VARCHAR(100) NOT NULL,
                            email VARCHAR(254) NOT NULL,
                            program_id INTEGER NOT NULL REFERENCES programs(id),
                            created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
                        )
                        """);
                statement.execute("""
                        CREATE UNIQUE INDEX IF NOT EXISTS students_email_lower_uq
                        ON students (LOWER(email))
                        """);
            } catch (SQLException exception) {
                rollback(connection, exception);
                throw exception;
            }

            try (var statement = connection.prepareStatement(
                    "INSERT INTO programs (name) VALUES (?) ON CONFLICT (name) DO NOTHING")) {
                statement.setString(1, "Bachelor of Computer Science");
                statement.executeUpdate();
                statement.setString(1, "Diploma in IT");
                statement.executeUpdate();
            } catch (SQLException exception) {
                rollback(connection, exception);
                throw exception;
            }
            connection.commit();
        }
    }

    private static void rollback(Connection connection, SQLException failure) {
        try {
            connection.rollback();
        } catch (SQLException rollbackFailure) {
            failure.addSuppressed(rollbackFailure);
        }
    }

    private static String readDatabaseUrlFromFiles() throws SQLException {
        Path repositoryRoot = findRepositoryRoot();
        for (String fileName : new String[]{".env.local", ".env"}) {
            Path file = repositoryRoot.resolve(fileName);
            if (!Files.isRegularFile(file)) {
                continue;
            }
            try {
                for (String line : Files.readAllLines(file, StandardCharsets.UTF_8)) {
                    String value = parseEnvironmentLine(line);
                    if (value != null) {
                        return value;
                    }
                }
            } catch (IOException exception) {
                throw new SQLException("Could not read " + fileName + ".", exception);
            }
        }
        return null;
    }

    private static Path findRepositoryRoot() {
        Path current = Paths.get(System.getProperty("user.dir")).toAbsolutePath().normalize();
        for (Path directory = current; directory != null; directory = directory.getParent()) {
            if (Files.isRegularFile(directory.resolve("UniversitySystem").resolve("pom.xml"))) {
                return directory;
            }
            Path projectPom = directory.resolve("pom.xml");
            if (Files.isRegularFile(projectPom)
                    && directory.getFileName() != null
                    && directory.getFileName().toString().equals("UniversitySystem")) {
                return directory.getParent();
            }
        }
        return current;
    }

    private static String parseEnvironmentLine(String line) {
        String trimmed = line.trim();
        if (trimmed.isEmpty() || trimmed.startsWith("#")) {
            return null;
        }
        if (trimmed.startsWith("export ")) {
            trimmed = trimmed.substring("export ".length()).trim();
        }
        int separator = trimmed.indexOf('=');
        if (separator < 0 || !trimmed.substring(0, separator).trim().equals("DATABASE_URL")) {
            return null;
        }

        String value = trimmed.substring(separator + 1).trim();
        if (value.length() >= 2) {
            char first = value.charAt(0);
            char last = value.charAt(value.length() - 1);
            if ((first == '"' && last == '"') || (first == '\'' && last == '\'')) {
                value = value.substring(1, value.length() - 1);
            }
        }
        return value;
    }

    private static DatabaseConfiguration parseDatabaseUrl(String databaseUrl) throws SQLException {
        String uriText = databaseUrl.startsWith("jdbc:")
                ? databaseUrl.substring("jdbc:".length())
                : databaseUrl;
        final URI uri;
        try {
            uri = URI.create(uriText);
        } catch (IllegalArgumentException exception) {
            throw new SQLException("DATABASE_URL must be a valid PostgreSQL connection URI.", exception);
        }
        String scheme = uri.getScheme();
        if (scheme == null
                || !(scheme.equalsIgnoreCase("postgres") || scheme.equalsIgnoreCase("postgresql"))
                || uri.getHost() == null
                || uri.getRawPath() == null
                || uri.getRawPath().length() < 2) {
            throw new SQLException(
                    "DATABASE_URL must use postgresql://USER:PASSWORD@HOST/DATABASE format.");
        }

        String rawUserInfo = uri.getRawUserInfo();
        if (rawUserInfo == null || !rawUserInfo.contains(":")) {
            throw new SQLException("DATABASE_URL must include a database username and password.");
        }
        int separator = rawUserInfo.indexOf(':');
        String username = decodeUriComponent(rawUserInfo.substring(0, separator));
        String password = decodeUriComponent(rawUserInfo.substring(separator + 1));
        if (username.isBlank() || password.isBlank()) {
            throw new SQLException("DATABASE_URL must include a database username and password.");
        }

        String host = uri.getHost();
        if (host.indexOf(':') >= 0 && !host.startsWith("[")) {
            host = "[" + host + "]";
        }
        StringBuilder jdbcUrl = new StringBuilder("jdbc:postgresql://").append(host);
        if (uri.getPort() >= 0) {
            jdbcUrl.append(':').append(uri.getPort());
        }
        jdbcUrl.append(uri.getRawPath());
        String query = uri.getRawQuery();
        if (query == null || query.isBlank()) {
            jdbcUrl.append("?sslmode=require");
        } else {
            jdbcUrl.append('?').append(query);
            if (!query.toLowerCase().contains("sslmode=")) {
                jdbcUrl.append("&sslmode=require");
            }
        }
        return new DatabaseConfiguration(jdbcUrl.toString(), username, password);
    }

    private static String decodeUriComponent(String value) {
        return URLDecoder.decode(value.replace("+", "%2B"), StandardCharsets.UTF_8);
    }

    private static final class DatabaseConfiguration {
        private final String jdbcUrl;
        private final String username;
        private final String password;

        private DatabaseConfiguration(String jdbcUrl, String username, String password) {
            this.jdbcUrl = jdbcUrl;
            this.username = username;
            this.password = password;
        }
    }
}

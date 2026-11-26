package com.university;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

public final class UniversitySystemTest {
    private UniversitySystemTest() {
    }

    public static void main(String[] args) throws SQLException {
        DatabaseUtil.initializeDatabase();
        int programId = findProgramId();
        if (programId == 0) {
            throw new SQLException("No programs are available for the integration test.");
        }

        String email = "week10-" + UUID.randomUUID() + "@example.invalid";
        int insertedId = 0;
        try {
            insertedId = insertStudent(email, programId);
            assertStudentExists(insertedId, "Integration", email);

            updateStudent(insertedId);
            assertStudentExists(insertedId, "Updated", email);

            deleteStudent(insertedId);
            insertedId = 0;
            if (containsStudent(email)) {
                throw new SQLException("The integration-test student still exists after deletion.");
            }
        } finally {
            if (insertedId != 0) {
                deleteStudent(insertedId);
            }
        }
        System.out.println("PASSED: PostgreSQL student insert, query, update, and delete.");
    }

    private static int findProgramId() throws SQLException {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT id FROM programs ORDER BY id LIMIT 1");
             ResultSet results = statement.executeQuery()) {
            return results.next() ? results.getInt(1) : 0;
        }
    }

    private static int insertStudent(String email, int programId) throws SQLException {
        String sql = """
                INSERT INTO students (first_name, last_name, email, program_id)
                VALUES ('Integration', 'Test', ?, ?)
                RETURNING id
                """;
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            statement.setInt(2, programId);
            try (ResultSet results = statement.executeQuery()) {
                if (!results.next()) {
                    throw new SQLException("The database did not return the integration-test student ID.");
                }
                return results.getInt(1);
            }
        }
    }

    private static void updateStudent(int id) throws SQLException {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE students SET first_name = 'Updated' WHERE id = ?")) {
            statement.setInt(1, id);
            if (statement.executeUpdate() != 1) {
                throw new SQLException("The integration-test student could not be updated.");
            }
        }
    }

    private static void deleteStudent(int id) throws SQLException {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM students WHERE id = ?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    private static void assertStudentExists(int id, String expectedFirstName, String email)
            throws SQLException {
        String sql = "SELECT first_name FROM students WHERE id = ? AND LOWER(email) = LOWER(?)";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.setString(2, email);
            try (ResultSet results = statement.executeQuery()) {
                if (!results.next() || !expectedFirstName.equals(results.getString("first_name"))) {
                    throw new SQLException(
                            "The integration-test student was not found with expected values.");
                }
            }
        }
    }

    private static boolean containsStudent(String email) throws SQLException {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT 1 FROM students WHERE LOWER(email) = LOWER(?)")) {
            statement.setString(1, email);
            try (ResultSet results = statement.executeQuery()) {
                return results.next();
            }
        }
    }
}

package com.university;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class UniversityRepository {
    private UniversityRepository() {
    }

    public static List<Program> loadPrograms() throws SQLException {
        String sql = "SELECT id, name FROM programs ORDER BY name";
        List<Program> programs = new ArrayList<>();
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet results = statement.executeQuery()) {
            while (results.next()) {
                programs.add(new Program(results.getInt("id"), results.getString("name")));
            }
        }
        return programs;
    }

    public static List<Student> loadStudents() throws SQLException {
        String sql = """
                SELECT s.id, s.first_name, s.last_name, s.email, s.program_id, p.name AS program_name
                FROM students s
                JOIN programs p ON p.id = s.program_id
                ORDER BY s.last_name, s.first_name, s.id
                """;
        List<Student> students = new ArrayList<>();
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet results = statement.executeQuery()) {
            while (results.next()) {
                students.add(readStudent(results));
            }
        }
        return students;
    }

    public static Student insertStudent(
            String firstName,
            String lastName,
            String email,
            int programId,
            String programName) throws SQLException {
        String sql = """
                INSERT INTO students (first_name, last_name, email, program_id)
                VALUES (?, ?, ?, ?)
                RETURNING id
                """;
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, firstName.trim());
            statement.setString(2, lastName.trim());
            statement.setString(3, email.trim());
            statement.setInt(4, programId);
            try (ResultSet results = statement.executeQuery()) {
                if (!results.next()) {
                    throw new SQLException("The database did not return the new student's ID.");
                }
                return new Student(results.getInt(1), firstName.trim(), lastName.trim(),
                        email.trim(), programId, programName);
            }
        }
    }

    public static void updateStudent(
            int id,
            String firstName,
            String lastName,
            String email,
            int programId) throws SQLException {
        String sql = """
                UPDATE students
                SET first_name = ?, last_name = ?, email = ?, program_id = ?
                WHERE id = ?
                """;
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, firstName.trim());
            statement.setString(2, lastName.trim());
            statement.setString(3, email.trim());
            statement.setInt(4, programId);
            statement.setInt(5, id);
            if (statement.executeUpdate() != 1) {
                throw new SQLException("The selected student no longer exists.");
            }
        }
    }

    public static void deleteStudent(int id) throws SQLException {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM students WHERE id = ?")) {
            statement.setInt(1, id);
            if (statement.executeUpdate() != 1) {
                throw new SQLException("The selected student no longer exists.");
            }
        }
    }

    private static Student readStudent(ResultSet results) throws SQLException {
        return new Student(
                results.getInt("id"),
                results.getString("first_name"),
                results.getString("last_name"),
                results.getString("email"),
                results.getInt("program_id"),
                results.getString("program_name"));
    }
}

package com.university;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public final class SeedStudents {
    private SeedStudents() {
    }

    public static void main(String[] args) throws SQLException {
        DatabaseUtil.initializeDatabase();
        String sql = """
                INSERT INTO students (first_name, last_name, email, program_id)
                SELECT ?, ?, ?, id FROM programs WHERE name = ?
                ON CONFLICT DO NOTHING
                """;
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            seedStudent(statement, "Maya", "Otieno", "maya.otieno@example.edu",
                    "Bachelor of Computer Science");
            seedStudent(statement, "David", "Kimani", "david.kimani@example.edu",
                    "Diploma in IT");
        }
        System.out.println("Sample students seeded (existing email addresses were left unchanged).");
    }

    private static void seedStudent(
            PreparedStatement statement,
            String firstName,
            String lastName,
            String email,
            String programName) throws SQLException {
        statement.setString(1, firstName);
        statement.setString(2, lastName);
        statement.setString(3, email);
        statement.setString(4, programName);
        statement.executeUpdate();
    }
}

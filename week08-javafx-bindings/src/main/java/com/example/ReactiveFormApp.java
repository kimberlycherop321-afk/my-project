package com.example;

import java.util.regex.Pattern;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ReactiveFormApp extends Application {
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    @Override
    public void start(Stage stage) {
        TextField nameField = new TextField();
        nameField.setPromptText("Full name");
        TextField emailField = new TextField();
        emailField.setPromptText("name@example.com");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("At least 8 characters");

        Label greeting = new Label();
        greeting.textProperty().bind(Bindings.createStringBinding(() -> {
            String name = nameField.getText().trim();
            return "Hello, " + (name.isEmpty() ? "there" : name) + "!";
        }, nameField.textProperty()));

        Label strength = new Label();
        strength.textProperty().bind(Bindings.createStringBinding(
                () -> "Password strength: " + getPasswordStrength(passwordField.getText()),
                passwordField.textProperty()));

        Button submitButton = new Button("Register");
        submitButton.disableProperty().bind(Bindings.createBooleanBinding(
                () -> !isValidName(nameField.getText())
                        || !isValidEmail(emailField.getText())
                        || !isValidPassword(passwordField.getText()),
                nameField.textProperty(),
                emailField.textProperty(),
                passwordField.textProperty()));

        Label feedback = new Label("Complete all fields to enable registration.");
        submitButton.setOnAction(event ->
                feedback.setText("Registration submitted for " + nameField.getText().trim() + "."));

        VBox root = new VBox(10,
                greeting,
                new Label("Name:"), nameField,
                new Label("Email:"), emailField,
                new Label("Password:"), passwordField, strength,
                submitButton, feedback);
        root.setPadding(new Insets(20));

        stage.setTitle("Week 8: Reactive Registration Form");
        stage.setScene(new Scene(root, 430, 430));
        stage.show();
    }

    private static boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    private static boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email.trim()).matches();
    }

    private static boolean isValidPassword(String password) {
        return password != null && password.length() >= 8;
    }

    private static String getPasswordStrength(String password) {
        if (password.isEmpty()) {
            return "Not entered";
        }

        int score = 0;
        if (password.length() >= 8) {
            score++;
        }
        if (password.matches(".*[A-Z].*")) {
            score++;
        }
        if (password.matches(".*[0-9].*")) {
            score++;
        }
        if (password.matches(".*[^A-Za-z0-9].*")) {
            score++;
        }

        if (score <= 1) {
            return "Weak";
        }
        if (score == 2) {
            return "Fair";
        }
        if (score == 3) {
            return "Strong";
        }
        return "Very strong";
    }

    public static void main(String[] args) {
        launch(args);
    }
}

package com.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CustomEventDemo extends Application {
    @Override
    public void start(Stage stage) {
        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter a demo username");
        usernameField.setMaxWidth(280);
        Label feedback = new Label("No custom event has been fired yet.");
        Button fireEventButton = new Button("Fire LoginSuccessEvent");

        VBox root = new VBox(14,
                new Label("Custom event source"),
                new HBox(8, new Label("Username:"), usernameField),
                fireEventButton,
                feedback);
        root.setPadding(new Insets(22));

        root.addEventHandler(LoginSuccessEvent.LOGIN_SUCCESS, event -> {
            feedback.setText("Received LoginSuccessEvent for " + event.getUsername() + ".");
            System.out.println("Received LoginSuccessEvent for " + event.getUsername() + ".");
        });
        fireEventButton.setOnAction(event -> {
            String username = usernameField.getText().trim();
            if (username.isEmpty()) {
                feedback.setText("Enter a username before firing the custom event.");
                return;
            }
            root.fireEvent(new LoginSuccessEvent(username));
        });

        stage.setTitle("Week 7: Custom Events");
        stage.setScene(new Scene(root, 520, 250));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

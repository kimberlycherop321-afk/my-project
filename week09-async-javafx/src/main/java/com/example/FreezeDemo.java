package com.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FreezeDemo extends Application {
    @Override
    public void start(Stage stage) {
        Label status = new Label("The window will stop responding during the five-second task.");
        Button workButton = new Button("Run slow work on the JavaFX thread");
        workButton.setOnAction(event -> {
            status.setText("Working on the JavaFX Application Thread...");
            try {
                Thread.sleep(5000);
                status.setText("Finished. The window was unresponsive while the work ran.");
            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
                status.setText("The slow work was interrupted.");
            }
        });

        VBox root = new VBox(14,
                new Label("Freeze Problem: blocking the JavaFX Application Thread"),
                new Label("Click the button, then try moving or resizing the window."),
                workButton, status);
        root.setPadding(new Insets(20));

        stage.setTitle("Week 9: The Freeze Problem");
        stage.setScene(new Scene(root, 520, 200));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

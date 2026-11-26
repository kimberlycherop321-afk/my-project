package com.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.concurrent.Task;
import javafx.stage.Stage;

public class TaskDemo extends Application {
    @Override
    public void start(Stage stage) {
        Label status = new Label("The five-second task will run in the background.");
        Button startButton = new Button("Start background work");
        Button cancelButton = new Button("Cancel");
        cancelButton.setDisable(true);

        startButton.setOnAction(event -> {
            startButton.setDisable(true);
            cancelButton.setDisable(false);
            status.setText("Working in the background. The window remains responsive.");

            Task<Void> task = new Task<Void>() {
                @Override
                protected Void call() throws InterruptedException {
                    Thread.sleep(5000);
                    return null;
                }
            };

            task.setOnSucceeded(done -> {
                status.setText("Background work finished.");
                startButton.setDisable(false);
                cancelButton.setDisable(true);
            });
            task.setOnCancelled(cancelled -> {
                status.setText("Background work cancelled.");
                startButton.setDisable(false);
                cancelButton.setDisable(true);
            });
            task.setOnFailed(failed -> {
                status.setText("Background work failed: " + task.getException().getMessage());
                startButton.setDisable(false);
                cancelButton.setDisable(true);
            });
            cancelButton.setOnAction(cancel -> task.cancel());

            Thread worker = new Thread(task, "week9-task-demo");
            worker.setDaemon(true);
            worker.start();
        });

        VBox root = new VBox(14,
                new Label("Task moves one-time work off the JavaFX Application Thread."),
                new HBox(10, startButton, cancelButton), status);
        root.setPadding(new Insets(20));

        stage.setTitle("Week 9: Background Task");
        stage.setScene(new Scene(root, 540, 200));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

package com.example;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ProgressDemo extends Application {
    private Task<Void> currentTask;

    @Override
    public void start(Stage stage) {
        ProgressBar progressBar = new ProgressBar(0);
        progressBar.setMaxWidth(Double.MAX_VALUE);
        Label status = new Label("Ready to simulate a download.");
        Button startButton = new Button("Start download");
        Button cancelButton = new Button("Cancel");
        cancelButton.setDisable(true);

        startButton.setOnAction(event -> {
            startButton.setDisable(true);
            cancelButton.setDisable(false);

            Task<Void> task = new Task<Void>() {
                @Override
                protected Void call() throws InterruptedException {
                    final int total = 100;
                    for (int step = 1; step <= total; step++) {
                        if (isCancelled()) {
                            return null;
                        }
                        updateProgress(step, total);
                        updateMessage("Downloading... " + step + "%");
                        Thread.sleep(50);
                    }
                    return null;
                }
            };
            currentTask = task;
            progressBar.progressProperty().unbind();
            progressBar.progressProperty().bind(task.progressProperty());
            status.textProperty().unbind();
            status.textProperty().bind(task.messageProperty());

            task.setOnSucceeded(done -> {
                status.textProperty().unbind();
                status.setText("Download complete.");
                startButton.setDisable(false);
                cancelButton.setDisable(true);
            });
            task.setOnCancelled(cancelled -> {
                status.textProperty().unbind();
                status.setText("Download cancelled.");
                startButton.setDisable(false);
                cancelButton.setDisable(true);
            });
            task.setOnFailed(failed -> {
                status.textProperty().unbind();
                status.setText("Download failed: " + task.getException().getMessage());
                startButton.setDisable(false);
                cancelButton.setDisable(true);
            });

            Thread worker = new Thread(task, "week9-progress-demo");
            worker.setDaemon(true);
            worker.start();
        });
        cancelButton.setOnAction(event -> {
            if (currentTask != null) {
                currentTask.cancel();
            }
        });

        VBox root = new VBox(14,
                new Label("Task progress and messages are safely bound to these controls."),
                progressBar, status, new HBox(10, startButton, cancelButton));
        root.setPadding(new Insets(20));

        stage.setTitle("Week 9: Live Task Progress");
        stage.setScene(new Scene(root, 520, 220));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

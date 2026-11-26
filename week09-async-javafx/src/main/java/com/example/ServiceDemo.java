package com.example;

import javafx.application.Application;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ServiceDemo extends Application {
    @Override
    public void start(Stage stage) {
        DataSyncService service = new DataSyncService();
        ProgressBar progressBar = new ProgressBar();
        progressBar.setMaxWidth(Double.MAX_VALUE);
        progressBar.progressProperty().bind(service.progressProperty());

        Label status = new Label();
        status.textProperty().bind(service.messageProperty());
        Label result = new Label("Ready to synchronize.");

        Button startButton = new Button("Start");
        Button cancelButton = new Button("Cancel");
        Button restartButton = new Button("Restart");

        service.stateProperty().addListener((observable, oldState, newState) -> {
            boolean running = newState == Worker.State.RUNNING;
            startButton.setDisable(running || newState != Worker.State.READY);
            cancelButton.setDisable(!running);
            restartButton.setDisable(running);
        });
        startButton.setOnAction(event -> {
            result.setText("");
            service.start();
        });
        cancelButton.setOnAction(event -> service.cancel());
        restartButton.setOnAction(event -> {
            result.setText("");
            service.restart();
        });

        service.setOnSucceeded(event -> result.setText("Completed: " + service.getValue()));
        service.setOnCancelled(event -> result.setText("Synchronization cancelled."));
        service.setOnFailed(event ->
                result.setText("Synchronization failed: " + service.getException().getMessage()));

        startButton.setDisable(false);
        cancelButton.setDisable(true);
        restartButton.setDisable(false);

        VBox root = new VBox(14,
                new Label("Service creates a fresh Task each time it is restarted."),
                progressBar, status, new HBox(10, startButton, cancelButton, restartButton), result);
        root.setPadding(new Insets(20));

        stage.setTitle("Week 9: Reusable Background Service");
        stage.setScene(new Scene(root, 560, 240));
        stage.show();
    }

    private static class DataSyncService extends Service<String> {
        @Override
        protected Task<String> createTask() {
            return new Task<String>() {
                @Override
                protected String call() throws InterruptedException {
                    final int total = 20;
                    for (int item = 1; item <= total; item++) {
                        if (isCancelled()) {
                            return null;
                        }
                        updateProgress(item, total);
                        updateMessage("Synchronizing item " + item + " of " + total);
                        Thread.sleep(150);
                    }
                    return "synchronized " + total + " items.";
                }
            };
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

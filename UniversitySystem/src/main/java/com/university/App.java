package com.university;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    private static final Logger LOGGER = Logger.getLogger(App.class.getName());
    private ScheduledExecutorService heartbeat;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/com/university/MainView.fxml"));
        Scene scene = new Scene(loader.load(), 1050, 680);
        stage.setTitle("University Management System");
        stage.setMinWidth(820);
        stage.setMinHeight(560);
        stage.setScene(scene);
        stage.show();

        heartbeat = Executors.newSingleThreadScheduledExecutor(task -> {
            Thread thread = new Thread(task, "university-heartbeat");
            thread.setDaemon(true);
            return thread;
        });
        heartbeat.scheduleAtFixedRate(
                () -> LOGGER.info("University Management System heartbeat"),
                5,
                5,
                TimeUnit.SECONDS);
    }

    @Override
    public void stop() {
        if (heartbeat != null) {
            heartbeat.shutdownNow();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

package com.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MouseKeyDemo extends Application {
    @Override
    public void start(Stage stage) {
        Label instructions = new Label(
                "Click inside the area, then press keys. Ctrl, Shift, Alt, and Meta states are shown.");
        Label mouseStatus = new Label("Mouse: no click yet.");
        TextArea eventLog = new TextArea();
        eventLog.setEditable(false);
        eventLog.setPrefRowCount(8);

        StackPane interactionArea = new StackPane(
                new Label("Click here, then press any key"));
        interactionArea.setFocusTraversable(true);
        interactionArea.setMinHeight(190);
        interactionArea.setStyle(
                "-fx-background-color: #e8f0fb;"
                        + "-fx-border-color: #7594bc;"
                        + "-fx-border-radius: 8;"
                        + "-fx-background-radius: 8;");
        interactionArea.setOnMouseClicked(event -> {
            interactionArea.requestFocus();
            String details = String.format(
                    "Mouse %s at local (%.1f, %.1f), scene (%.1f, %.1f); click count %d",
                    event.getButton(),
                    event.getX(),
                    event.getY(),
                    event.getSceneX(),
                    event.getSceneY(),
                    event.getClickCount());
            mouseStatus.setText(details);
            append(eventLog, details);
        });

        Scene scene = new Scene(new VBox(12,
                instructions,
                interactionArea,
                mouseStatus,
                eventLog), 700, 470);
        VBox root = (VBox) scene.getRoot();
        root.setPadding(new Insets(18));
        root.setAlignment(Pos.CENTER_LEFT);
        VBox.setVgrow(eventLog, javafx.scene.layout.Priority.ALWAYS);

        scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            String details = "Key: " + event.getCode()
                    + " | Ctrl=" + event.isControlDown()
                    + " Shift=" + event.isShiftDown()
                    + " Alt=" + event.isAltDown()
                    + " Meta=" + event.isMetaDown();
            append(eventLog, details);
        });

        stage.setTitle("Week 7: Mouse and Keyboard Events");
        stage.setScene(scene);
        stage.show();
        interactionArea.requestFocus();
    }

    private void append(TextArea log, String message) {
        log.appendText(message + System.lineSeparator());
        log.positionCaret(log.getLength());
        System.out.println(message);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

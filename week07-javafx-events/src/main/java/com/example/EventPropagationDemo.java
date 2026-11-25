package com.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EventPropagationDemo extends Application {
    private int logNumber;

    @Override
    public void start(Stage stage) {
        TextArea log = new TextArea();
        log.setEditable(false);
        log.setWrapText(true);
        log.setPrefRowCount(12);

        CheckBox consumeAtRoot = new CheckBox("Consume the click at the outer VBox filter");
        Button nestedButton = new Button("Click to trace event propagation");
        nestedButton.setStyle("-fx-font-size: 15px; -fx-padding: 12 20;");
        HBox innerBox = new HBox(nestedButton);
        innerBox.setAlignment(Pos.CENTER);
        innerBox.setPadding(new Insets(24));
        innerBox.setStyle("-fx-background-color: #e8f0fb; -fx-background-radius: 8;");

        Label heading = new Label("Click the nested button and follow the event phases.");
        VBox root = new VBox(12, heading, consumeAtRoot, innerBox, log);
        root.setPadding(new Insets(18));
        root.setAlignment(Pos.CENTER);
        VBox.setVgrow(log, javafx.scene.layout.Priority.ALWAYS);

        Scene scene = new Scene(root, 620, 480);
        stage.setTitle("Week 7: Event Propagation");
        stage.setScene(scene);

        stage.addEventFilter(MouseEvent.MOUSE_PRESSED, event ->
                logForButton(event, nestedButton, log, "FILTER Stage (capturing)"));
        scene.addEventFilter(MouseEvent.MOUSE_PRESSED, event ->
                logForButton(event, nestedButton, log, "FILTER Scene (capturing)"));
        root.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
            if (isInside(event, nestedButton)) {
                log(log, "FILTER outer VBox (capturing)");
                if (consumeAtRoot.isSelected()) {
                    log(log, "CONSUMED at outer VBox; the inner button and bubble handlers will not run");
                    event.consume();
                }
            }
        });
        innerBox.addEventFilter(MouseEvent.MOUSE_PRESSED, event ->
                logForButton(event, nestedButton, log, "FILTER inner HBox (capturing)"));
        nestedButton.addEventFilter(MouseEvent.MOUSE_PRESSED, event ->
                logForButton(event, nestedButton, log, "FILTER Button (capturing)"));

        nestedButton.addEventHandler(MouseEvent.MOUSE_PRESSED, event ->
                logForButton(event, nestedButton, log, "HANDLER Button (bubbling)"));
        innerBox.addEventHandler(MouseEvent.MOUSE_PRESSED, event ->
                logForButton(event, nestedButton, log, "HANDLER inner HBox (bubbling)"));
        root.addEventHandler(MouseEvent.MOUSE_PRESSED, event ->
                logForButton(event, nestedButton, log, "HANDLER outer VBox (bubbling)"));
        scene.addEventHandler(MouseEvent.MOUSE_PRESSED, event ->
                logForButton(event, nestedButton, log, "HANDLER Scene (bubbling)"));
        stage.addEventHandler(MouseEvent.MOUSE_PRESSED, event ->
                logForButton(event, nestedButton, log, "HANDLER Stage (bubbling)"));
        nestedButton.setOnAction(event -> log(log, "ACTION: Button action fired"));

        Button clearButton = new Button("Clear log");
        clearButton.setOnAction(event -> {
            log.clear();
            logNumber = 0;
        });
        root.getChildren().add(2, clearButton);
        stage.show();
    }

    private void logForButton(
            MouseEvent event,
            Button nestedButton,
            TextArea log,
            String message) {
        if (isInside(event, nestedButton)) {
            log(log, message);
        }
    }

    private boolean isInside(MouseEvent event, Button nestedButton) {
        if (!(event.getTarget() instanceof javafx.scene.Node)) {
            return false;
        }
        javafx.scene.Node node = (javafx.scene.Node) event.getTarget();
        while (node != null) {
            if (node == nestedButton) {
                return true;
            }
            node = node.getParent();
        }
        return false;
    }

    private void log(TextArea area, String message) {
        String line = String.format("%02d  %s%n", ++logNumber, message);
        area.appendText(line);
        area.positionCaret(area.getLength());
        System.out.print(line);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

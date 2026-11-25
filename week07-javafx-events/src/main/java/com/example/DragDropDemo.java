package com.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DragDropDemo extends Application {
    @Override
    public void start(Stage stage) {
        Label source = new Label("Drag this text");
        source.setStyle(
                "-fx-background-color: #dce8f7;"
                        + "-fx-padding: 16;"
                        + "-fx-background-radius: 8;"
                        + "-fx-border-color: #8aa6ca;"
                        + "-fx-border-radius: 8;");

        Label targetMessage = new Label("Drop text here");
        StackPane target = new StackPane(targetMessage);
        target.setMinSize(320, 150);
        target.setAlignment(Pos.CENTER);
        target.setStyle(
                "-fx-background-color: #f3f6fb;"
                        + "-fx-border-color: #8aa6ca;"
                        + "-fx-border-style: dashed;"
                        + "-fx-border-radius: 8;"
                        + "-fx-background-radius: 8;");
        Label status = new Label("Drag the source label into the target area.");

        source.setOnDragDetected(event -> {
            Dragboard dragboard = source.startDragAndDrop(TransferMode.COPY);
            ClipboardContent content = new ClipboardContent();
            content.putString(source.getText());
            dragboard.setContent(content);
            status.setText("Dragging text...");
            event.consume();
        });
        target.setOnDragOver(event -> {
            if (event.getGestureSource() != target && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.COPY);
            }
            event.consume();
        });
        target.setOnDragEntered(event -> {
            if (event.getGestureSource() != target && event.getDragboard().hasString()) {
                target.setStyle(
                        "-fx-background-color: #e3f5e8;"
                                + "-fx-border-color: #39874e;"
                                + "-fx-border-style: dashed;"
                                + "-fx-border-radius: 8;"
                                + "-fx-background-radius: 8;");
            }
        });
        target.setOnDragExited(event -> target.setStyle(
                "-fx-background-color: #f3f6fb;"
                        + "-fx-border-color: #8aa6ca;"
                        + "-fx-border-style: dashed;"
                        + "-fx-border-radius: 8;"
                        + "-fx-background-radius: 8;"));
        target.setOnDragDropped(event -> {
            Dragboard dragboard = event.getDragboard();
            boolean success = false;
            if (dragboard.hasString()) {
                targetMessage.setText("Dropped: " + dragboard.getString());
                status.setText("Drop completed successfully.");
                success = true;
            } else {
                status.setText("The dragged item did not contain text.");
            }
            event.setDropCompleted(success);
            event.consume();
        });
        source.setOnDragDone(event -> {
            if (event.getTransferMode() == TransferMode.COPY) {
                status.setText("Text copied to the drop target.");
            }
            event.consume();
        });

        HBox sourceRow = new HBox(source);
        sourceRow.setAlignment(Pos.CENTER);
        VBox root = new VBox(18,
                new Label("Drag-and-drop with JavaFX"),
                sourceRow,
                target,
                status);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        stage.setTitle("Week 7: Drag and Drop");
        stage.setScene(new Scene(root, 520, 360));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

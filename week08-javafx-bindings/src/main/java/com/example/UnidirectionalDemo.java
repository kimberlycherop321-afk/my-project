package com.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UnidirectionalDemo extends Application {
    @Override
    public void start(Stage stage) {
        Slider slider = new Slider(0, 100, 35);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(25);
        slider.setBlockIncrement(1);

        Label valueLabel = new Label();
        valueLabel.textProperty().bind(slider.valueProperty().asString("Slider value: %.0f%%"));

        ProgressBar progressBar = new ProgressBar();
        progressBar.setMaxWidth(Double.MAX_VALUE);
        progressBar.progressProperty().bind(slider.valueProperty().divide(100));

        VBox root = new VBox(16,
                new Label("Move the slider. The label and progress bar update through one-way bindings."),
                slider, valueLabel, progressBar);
        root.setPadding(new Insets(20));

        stage.setTitle("Week 8: Unidirectional Binding");
        stage.setScene(new Scene(root, 500, 220));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

package com.example;

import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ListenerDemo extends Application {
    @Override
    public void start(Stage stage) {
        Person person = new Person("Alex", 20);
        TextField nameField = new TextField(person.getName());
        nameField.setPromptText("Name");
        nameField.textProperty().bindBidirectional(person.nameProperty());

        Slider ageSlider = new Slider(0, 100, person.getAge());
        ageSlider.setShowTickLabels(true);
        ageSlider.setShowTickMarks(true);
        Label ageLabel = new Label();
        ageLabel.textProperty().bind(person.ageProperty().asString("Age: %.0f"));
        ageSlider.valueProperty().addListener((observable, oldValue, newValue) ->
                person.setAge((int) Math.round(newValue.doubleValue())));

        TextArea log = new TextArea();
        log.setEditable(false);
        log.setWrapText(true);
        log.setPrefRowCount(8);

        person.nameProperty().addListener((InvalidationListener) observable ->
                log.appendText("InvalidationListener: name property became invalid."
                        + System.lineSeparator()));
        person.nameProperty().addListener((ChangeListener<String>) (observable, oldValue, newValue) ->
                log.appendText(String.format("ChangeListener: name changed from \"%s\" to \"%s\".%n",
                        oldValue, newValue)));
        person.ageProperty().addListener((observable, oldValue, newValue) ->
                log.appendText(String.format("ChangeListener: age changed from %d to %d.%n",
                        oldValue.intValue(), newValue.intValue())));

        VBox root = new VBox(10,
                new Label("Edit the name or move the age slider to observe property listeners."),
                new Label("Name:"), nameField, ageLabel, ageSlider,
                new Label("Listener log:"), log);
        root.setPadding(new Insets(18));

        stage.setTitle("Week 8: Observable Properties and Listeners");
        stage.setScene(new Scene(root, 520, 430));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

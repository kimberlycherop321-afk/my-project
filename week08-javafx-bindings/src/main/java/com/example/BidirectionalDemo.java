package com.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BidirectionalDemo extends Application {
    @Override
    public void start(Stage stage) {
        Person person = new Person("Taylor", 25);
        TextField firstField = new TextField();
        TextField secondField = new TextField();
        firstField.setPromptText("Type here");
        secondField.setPromptText("The other field stays in sync");
        firstField.textProperty().bindBidirectional(person.nameProperty());
        secondField.textProperty().bindBidirectional(person.nameProperty());

        Label modelLabel = new Label();
        modelLabel.textProperty().bind(person.nameProperty().concat(" is stored in the Person model."));

        VBox root = new VBox(12,
                new Label("Edit either text field. Both controls and the model property stay synchronized."),
                new Label("First text field:"), firstField,
                new Label("Second text field:"), secondField,
                modelLabel);
        root.setPadding(new Insets(20));

        stage.setTitle("Week 8: Bidirectional Binding");
        stage.setScene(new Scene(root, 520, 260));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

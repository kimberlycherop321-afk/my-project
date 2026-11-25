package programmaticdemo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        Label message = new Label("Welcome to your first JavaFX window.");
        message.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Button button = new Button("Click me");
        button.setOnAction(event -> message.setText("The button was clicked!"));

        VBox content = new VBox(16, message, button);
        content.setAlignment(Pos.CENTER);
        content.setPadding(new Insets(24));

        stage.setTitle("Week 6: First JavaFX Window");
        stage.setScene(new Scene(content, 440, 260));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

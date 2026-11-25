package programmaticdemo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DashboardApp extends Application {
    @Override
    public void start(Stage stage) {
        Label status = new Label("Welcome to your dashboard.");
        Label title = new Label("Student Dashboard");
        title.setStyle("-fx-font-size: 23px; -fx-font-weight: bold; -fx-text-fill: #1d2c43;");
        Label user = new Label("Signed in as Alex Student");
        HBox header = new HBox(16, title, user);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(18, 22, 18, 22));
        header.setStyle("-fx-background-color: white;");
        HBox.setHgrow(title, Priority.ALWAYS);

        VBox navigation = new VBox(10,
                new Label("NAVIGATION"),
                navigationButton("Overview", status),
                navigationButton("Courses", status),
                navigationButton("Assignments", status),
                navigationButton("Grades", status));
        navigation.setPadding(new Insets(18));
        navigation.setPrefWidth(190);
        navigation.setStyle("-fx-background-color: #e8eef7;");

        HBox cards = new HBox(16,
                createCard("Courses", "4", "Active this term"),
                createCard("Assignments", "3", "Due this week"),
                createCard("Average grade", "A-", "Across all courses"));
        cards.setAlignment(Pos.TOP_CENTER);
        cards.setPadding(new Insets(20));
        for (javafx.scene.Node card : cards.getChildren()) {
            HBox.setHgrow(card, Priority.ALWAYS);
        }

        Label footer = new Label();
        footer.textProperty().bind(status.textProperty());
        footer.setPadding(new Insets(12, 20, 12, 20));
        footer.setStyle("-fx-background-color: white; -fx-text-fill: #475569;");

        BorderPane root = new BorderPane();
        root.setTop(header);
        root.setLeft(navigation);
        root.setCenter(cards);
        root.setBottom(footer);
        root.setStyle("-fx-background-color: #f2f5fa; -fx-font-family: 'Arial';");

        stage.setTitle("Week 6: Student Dashboard");
        stage.setScene(new Scene(root, 900, 560));
        stage.show();
    }

    private Button navigationButton(String label, Label status) {
        Button button = new Button(label);
        button.setMaxWidth(Double.MAX_VALUE);
        button.setOnAction(event -> status.setText(label + " selected."));
        return button;
    }

    private VBox createCard(String heading, String value, String description) {
        Label title = new Label(heading);
        title.setStyle("-fx-text-fill: #475569; -fx-font-size: 14px;");
        Label number = new Label(value);
        number.setStyle("-fx-text-fill: #1d4f91; -fx-font-size: 32px; -fx-font-weight: bold;");
        Label detail = new Label(description);
        detail.setStyle("-fx-text-fill: #64748b;");
        VBox card = new VBox(12, title, number, detail);
        card.setAlignment(Pos.CENTER_LEFT);
        card.setPadding(new Insets(20));
        card.setMinWidth(150);
        card.setMaxWidth(Double.MAX_VALUE);
        card.setStyle(
                "-fx-background-color: white;"
                        + "-fx-background-radius: 10;"
                        + "-fx-effect: dropshadow(gaussian, rgba(30, 50, 80, 0.12), 12, 0.1, 0, 3);");
        return card;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

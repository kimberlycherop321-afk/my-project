package programmaticdemo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LayoutDemo extends Application {
    @Override
    public void start(Stage stage) {
        TabPane layouts = new TabPane(
                createTab("VBox", createVBox()),
                createTab("HBox", createHBox()),
                createTab("GridPane", createGridPane()),
                createTab("BorderPane", createBorderPane()));

        Label introduction = new Label(
                "Choose a tab to see how each layout arranges its controls.");
        VBox root = new VBox(12, introduction, layouts);
        root.setPadding(new Insets(16));
        VBox.setVgrow(layouts, Priority.ALWAYS);

        stage.setTitle("Week 6: JavaFX Layouts");
        stage.setScene(new Scene(root, 620, 420));
        stage.show();
    }

    private Tab createTab(String title, javafx.scene.Node content) {
        Tab tab = new Tab(title, content);
        tab.setClosable(false);
        return tab;
    }

    private VBox createVBox() {
        VBox pane = new VBox(12,
                new Label("VBox stacks controls vertically."),
                new Button("First"),
                new Button("Second"),
                new Button("Third"));
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(24));
        return pane;
    }

    private HBox createHBox() {
        HBox pane = new HBox(12,
                new Label("HBox places controls in a row:"),
                new Button("One"),
                new Button("Two"),
                new Button("Three"));
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(24));
        return pane;
    }

    private GridPane createGridPane() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(12);
        grid.setPadding(new Insets(24));
        grid.add(new Label("GridPane arranges cells:"), 0, 0, 2, 1);
        grid.add(new Label("Name"), 0, 1);
        grid.add(new TextField(), 1, 1);
        grid.add(new Label("Email"), 0, 2);
        grid.add(new TextField(), 1, 2);
        Button submit = new Button("Submit");
        grid.add(submit, 1, 3);
        GridPane.setHgrow(grid.getChildren().get(2), Priority.ALWAYS);
        return grid;
    }

    private BorderPane createBorderPane() {
        BorderPane pane = new BorderPane();
        Label top = new Label("TOP: header");
        top.setMaxWidth(Double.MAX_VALUE);
        top.setAlignment(Pos.CENTER);
        top.setStyle("-fx-background-color: #dce8f7; -fx-padding: 16;");

        VBox sidebar = new VBox(10,
                new Label("LEFT"),
                new Button("Home"),
                new Button("Courses"),
                new Button("Help"));
        sidebar.setPadding(new Insets(16));
        sidebar.setStyle("-fx-background-color: #eef2f8;");

        Label center = new Label("CENTER: main content");
        center.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        center.setAlignment(Pos.CENTER);
        center.setStyle("-fx-padding: 24;");
        Label bottom = new Label("BOTTOM: status bar");
        bottom.setMaxWidth(Double.MAX_VALUE);
        bottom.setStyle("-fx-background-color: #dce8f7; -fx-padding: 10;");

        pane.setTop(top);
        pane.setLeft(sidebar);
        pane.setCenter(center);
        pane.setBottom(bottom);
        return pane;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

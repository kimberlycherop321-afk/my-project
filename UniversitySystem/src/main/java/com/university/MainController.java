package com.university;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class MainController {
    @FXML
    private StackPane contentArea;

    @FXML
    private void initialize() {
        showDashboard();
    }

    @FXML
    private void showDashboard() {
        Label title = new Label("University Management System");
        title.setStyle("-fx-font-size: 26px; -fx-font-weight: bold;");
        Label message = new Label(
                "Manage students and academic programs. Database access runs in background tasks.");
        message.setWrapText(true);
        VBox dashboard = new VBox(12, title, message);
        dashboard.setAlignment(Pos.CENTER_LEFT);
        dashboard.setPadding(new Insets(36));
        contentArea.getChildren().setAll(dashboard);
    }

    @FXML
    private void showStudents() {
        try {
            Parent studentView = FXMLLoader.load(
                    MainController.class.getResource("/com/university/StudentView.fxml"));
            contentArea.getChildren().setAll(studentView);
        } catch (IOException exception) {
            throw new IllegalStateException("Could not load the student-management view.", exception);
        }
    }
}

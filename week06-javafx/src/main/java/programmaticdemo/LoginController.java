package programmaticdemo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label feedbackLabel;

    @FXML
    private void handleLogin(ActionEvent event) {
        String username = usernameField.getText().trim();
        int passwordLength = passwordField.getText().length();
        if (username.isEmpty()) {
            feedbackLabel.setText("Enter a username.");
        } else if (passwordLength < 6) {
            feedbackLabel.setText("Password must contain at least 6 characters.");
        } else {
            feedbackLabel.setText("Welcome, " + username + "! (Demo only; no sign-in occurs.)");
        }
    }
}

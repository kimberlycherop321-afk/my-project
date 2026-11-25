package programmaticdemo;

import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FXMLApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        URL view = FXMLApp.class.getResource("/programmaticdemo/login.fxml");
        if (view == null) {
            throw new IOException("Could not find /programmaticdemo/login.fxml on the classpath.");
        }

        Parent root = FXMLLoader.load(view);
        stage.setTitle("Week 6: FXML Login Demo");
        stage.setScene(new Scene(root, 460, 360));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

# Week 6: Introduction to Modern JavaFX

- **Project folder:** `week06-javafx/`
- **Run the default JavaFX window** from the repository root:

  ```bash
  mvn -f week06-javafx/pom.xml javafx:run
  ```

- The default main class is `programmaticdemo.Main`. The project requires JDK 17+, Maven 3.8+, network access for the first dependency download, and a graphical desktop.
- **Scripts:** From the repository root, run `./scripts/run-week6.sh` in Git Bash/Linux/macOS or `.\scripts\run-week6.ps1` in PowerShell.

## Activities

1. **First JavaFX Window** (`programmaticdemo.Main`) creates a `Stage` and `Scene`, then places a label and interactive button inside a `VBox`.
2. **JavaFX Layouts** (`programmaticdemo.LayoutDemo`) demonstrates `VBox`, `HBox`, `GridPane`, and `BorderPane` on separate tabs.
3. **FXML Login** (`programmaticdemo.FXMLApp`) loads `login.fxml` with `FXMLLoader` and connects controls to `LoginController` using `@FXML`. The form is a local demonstration and does not authenticate users.
4. **Dashboard** (`programmaticdemo.DashboardApp`) combines `BorderPane`, `VBox`, and `HBox` with inline CSS styling.

Run an activity other than the default by setting the Maven property:

```bash
mvn -f week06-javafx/pom.xml javafx:run -Djavafx.mainClass=programmaticdemo.LayoutDemo
```

Use `programmaticdemo.FXMLApp` or `programmaticdemo.DashboardApp` as the property value for those activities. See `week06-javafx/SelfAssessment.md` for review questions and suggested answers.

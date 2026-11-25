# Week 6: Introduction to Modern JavaFX

This Maven project introduces JavaFX windows, layout panes, FXML controllers, and a styled dashboard.

## Run the default activity

From the repository root, run:

```bash
mvn -f week06-javafx/pom.xml javafx:run
```

The default main class is `programmaticdemo.Main`. Maven downloads the JavaFX dependencies the first time the project is built.

## Activities

| Activity | Main class | What it demonstrates |
| --- | --- | --- |
| First JavaFX Window | `programmaticdemo.Main` | `Application`, `Stage`, `Scene`, `VBox`, label, and button |
| JavaFX Layouts | `programmaticdemo.LayoutDemo` | `VBox`, `HBox`, `GridPane`, and `BorderPane` |
| FXML Login | `programmaticdemo.FXMLApp` | `FXMLLoader`, `login.fxml`, and `LoginController` |
| Dashboard | `programmaticdemo.DashboardApp` | Nested panes, cards, and inline CSS |

Run a specific activity by overriding the Maven property:

```bash
mvn -f week06-javafx/pom.xml javafx:run -Djavafx.mainClass=programmaticdemo.LayoutDemo
```

Replace `programmaticdemo.LayoutDemo` with `programmaticdemo.FXMLApp` or `programmaticdemo.DashboardApp` to run another activity. `programmaticdemo.Main` is the default.

## Menu script

From the repository root, run `./scripts/run-week6.sh` in Git Bash/Linux/macOS or `.\scripts\run-week6.ps1` in PowerShell.

## Requirements

- JDK 17 or newer.
- Maven 3.8 or newer.
- A graphical desktop environment.

See [SelfAssessment.md](./SelfAssessment.md) for review questions and suggested answers.

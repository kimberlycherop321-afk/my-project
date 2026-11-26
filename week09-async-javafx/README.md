# Week 9: Asynchronous Programming in JavaFX

This Maven project demonstrates the JavaFX Application Thread, background `Task`s, live progress updates, and reusable `Service`s.

## Run the default activity

From the repository root:

```bash
mvn -f week09-async-javafx/pom.xml javafx:run
```

The default main class is `com.example.FreezeDemo`. Select a different activity by overriding the Maven main-class property:

```bash
mvn -f week09-async-javafx/pom.xml javafx:run -Djavafx.mainClass=com.example.ProgressDemo
```

Available main classes are `com.example.FreezeDemo`, `com.example.TaskDemo`, `com.example.ProgressDemo`, and `com.example.ServiceDemo`.

## Activities

| Activity | Main class | Demonstration |
| --- | --- | --- |
| Freeze Problem | `com.example.FreezeDemo` | Deliberately blocks the JavaFX Application Thread for five seconds |
| Background Task | `com.example.TaskDemo` | Runs one-time work off the UI thread and handles success, failure, and cancellation |
| Live Progress | `com.example.ProgressDemo` | Binds progress and status controls to a background `Task` |
| Reusable Service | `com.example.ServiceDemo` | Starts, cancels, and restarts a `Service<String>` |

## Menu script

From the repository root, run `./scripts/run-week9.sh` in Git Bash/Linux/macOS or `.\scripts\run-week9.ps1` in PowerShell.

## Requirements

- JDK 17 or newer.
- Maven 3.8 or newer.
- A graphical desktop environment.

See [SelfAssessment.md](./SelfAssessment.md) for questions and suggested answers.

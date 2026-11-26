# Week 8: Properties and Bindings in JavaFX

This Maven project demonstrates observable JavaFX properties, listeners, one-way and two-way bindings, and a reactive registration form.

## Run the default activity

From the repository root:

```bash
mvn -f week08-javafx-bindings/pom.xml javafx:run
```

The default main class is `com.example.ListenerDemo`. Run another activity by overriding the Maven main-class property, for example:

```bash
mvn -f week08-javafx-bindings/pom.xml javafx:run -Djavafx.mainClass=com.example.ReactiveFormApp
```

Available main classes are `com.example.ListenerDemo`, `com.example.UnidirectionalDemo`, `com.example.BidirectionalDemo`, and `com.example.ReactiveFormApp`.

## Activities

| Activity | Main class | Demonstration |
| --- | --- | --- |
| Observable Model and Listeners | `com.example.ListenerDemo` | `Person` properties with change and invalidation listeners |
| Unidirectional Binding | `com.example.UnidirectionalDemo` | A slider updates a label and progress bar |
| Bidirectional Binding | `com.example.BidirectionalDemo` | Two text fields and a `Person` name property stay synchronized |
| Reactive Form | `com.example.ReactiveFormApp` | Live greeting, password strength, validation, and an automatically enabled submit button |

## Menu script

From the repository root, run `./scripts/run-week8.sh` in Git Bash/Linux/macOS or `.\scripts\run-week8.ps1` in PowerShell.

## Requirements

- JDK 17 or newer.
- Maven 3.8 or newer.
- A graphical desktop environment.

See [SelfAssessment.md](./SelfAssessment.md) for questions and suggested answers.

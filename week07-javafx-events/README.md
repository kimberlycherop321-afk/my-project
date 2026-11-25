# Week 7: Event Handling in JavaFX

This Maven project demonstrates event propagation, mouse and keyboard input, drag-and-drop, and custom events.

## Run the default activity

From the repository root, run:

```bash
mvn -f week07-javafx-events/pom.xml javafx:run
```

The default main class is `com.example.EventPropagationDemo`.

## Activities

| Activity | Main class | Demonstration |
| --- | --- | --- |
| Event Propagation | `com.example.EventPropagationDemo` | Capturing filters, bubbling handlers, and consuming a mouse-press event |
| Mouse and Keyboard | `com.example.MouseKeyDemo` | Mouse coordinates, key codes, and modifier states |
| Drag and Drop | `com.example.DragDropDemo` | Copying text between controls with transfer modes |
| Custom Events | `com.example.CustomEventDemo` | Defining and firing `LoginSuccessEvent` |

Run a specific activity by overriding the Maven main-class property:

```bash
mvn -f week07-javafx-events/pom.xml javafx:run -Djavafx.mainClass=com.example.MouseKeyDemo
```

Replace `com.example.MouseKeyDemo` with `com.example.DragDropDemo` or `com.example.CustomEventDemo` to run a different example.

## Menu script

From the repository root, run `./scripts/run-week7.sh` in Git Bash/Linux/macOS or `.\scripts\run-week7.ps1` in PowerShell.

## Requirements

- JDK 17 or newer.
- Maven 3.8 or newer.
- A graphical desktop environment.

See [SelfAssessment.md](./SelfAssessment.md) for review questions and suggested answers.

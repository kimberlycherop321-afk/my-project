# Week 7: Event Handling in JavaFX

- **Project folder:** `week07-javafx-events/`
- **Run the default event-propagation demo** from the repository root:

  ```bash
  mvn -f week07-javafx-events/pom.xml javafx:run
  ```

- The default main class is `com.example.EventPropagationDemo`. Use the Maven property `-Djavafx.mainClass=com.example.MouseKeyDemo` (or another activity class) to select a different demo.
- **Scripts:** From the repository root, run `./scripts/run-week7.sh` in Git Bash/Linux/macOS or `.\scripts\run-week7.ps1` in PowerShell.

## Activities

1. **Event Propagation** (`EventPropagationDemo.java`) logs filters during capture and handlers during bubbling. A checkbox consumes the nested button's mouse-press event at the outer container.
2. **Mouse and Keyboard Events** (`MouseKeyDemo.java`) displays mouse coordinates, key codes, and Ctrl/Shift/Alt/Meta state.
3. **Drag and Drop** (`DragDropDemo.java`) transfers text from a source label to a target area using JavaFX dragboard and transfer modes.
4. **Custom Events** (`LoginSuccessEvent.java`, `CustomEventDemo.java`) defines an application-specific event that carries a username.

Run an activity directly by setting `javafx.mainClass`, for example:

```bash
mvn -f week07-javafx-events/pom.xml javafx:run -Djavafx.mainClass=com.example.DragDropDemo
```

See `week07-javafx-events/SelfAssessment.md` for review questions and suggested answers.

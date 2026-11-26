# Week 8: Properties and Bindings in JavaFX

- **Project folder:** `week08-javafx-bindings/`
- **Run the default listener demo** from the repository root:

  ```bash
  mvn -f week08-javafx-bindings/pom.xml javafx:run
  ```

- The default class is `com.example.ListenerDemo`. Choose another activity with `-Djavafx.mainClass=com.example.UnidirectionalDemo` (or `BidirectionalDemo` / `ReactiveFormApp`).
- **Scripts:** From the repository root, use `./scripts/run-week8.sh` in Git Bash/Linux/macOS or `.\scripts\run-week8.ps1` in PowerShell.

## Activities

1. **Observable Model and Listeners** (`Person.java`, `ListenerDemo.java`) uses `StringProperty` and `IntegerProperty`, with change and invalidation listeners.
2. **Unidirectional Binding** (`UnidirectionalDemo.java`) binds a label and progress bar to a slider.
3. **Bidirectional Binding** (`BidirectionalDemo.java`) connects two text fields to the same `Person` model property.
4. **Reactive Form** (`ReactiveFormApp.java`) shows a live greeting and password-strength feedback, validates the fields, and enables Submit only when the form is valid.

Run an activity directly by setting `javafx.mainClass`, for example:

```bash
mvn -f week08-javafx-bindings/pom.xml javafx:run -Djavafx.mainClass=com.example.ReactiveFormApp
```

See `week08-javafx-bindings/SelfAssessment.md` for review questions and suggested answers.

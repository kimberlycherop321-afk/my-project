# Week 9: Asynchronous Programming in JavaFX

- **Project folder:** `week09-async-javafx/`
- **Run the default freeze demo** from the repository root:

  ```bash
  mvn -f week09-async-javafx/pom.xml javafx:run
  ```

- The default class is `com.example.FreezeDemo`. Select another activity with `-Djavafx.mainClass=com.example.TaskDemo` (or `ProgressDemo` / `ServiceDemo`).
- **Scripts:** From the repository root, use `./scripts/run-week9.sh` in Git Bash/Linux/macOS or `.\scripts\run-week9.ps1` in PowerShell.

## Activities

1. **Freeze Problem** (`FreezeDemo.java`) intentionally runs a five-second blocking operation on the JavaFX Application Thread.
2. **Background Task** (`TaskDemo.java`) moves one-time work to a worker thread and reports success, failure, and cancellation.
3. **Live Progress** (`ProgressDemo.java`) uses `Task.updateProgress()` and `updateMessage()` with bound UI controls.
4. **Reusable Service** (`ServiceDemo.java`) implements a `Service<String>` with Start, Cancel, and Restart controls.

Run an activity directly by setting `javafx.mainClass`, for example:

```bash
mvn -f week09-async-javafx/pom.xml javafx:run -Djavafx.mainClass=com.example.ServiceDemo
```

See `week09-async-javafx/SelfAssessment.md` for review questions and suggested answers.

# Week 4: Event Handling in Swing

- **Folder:** `week04-event-handling/`
- **Run the GUI launcher** from the workspace root:

  ```powershell
  javac week04-event-handling\Week4Launcher.java
  java -cp week04-event-handling Week4Launcher
  ```

- The launcher compiles and opens the selected activity. It requires a graphical desktop environment and a JDK.
- **Scripts:** From inside `week04-event-handling/`, run `./scripts/run-week4.sh` in Git Bash/Linux/macOS or `.\scripts\run-week4.ps1` in PowerShell.

## Goals

- Handle button actions with inner classes, anonymous classes, and lambdas.
- Share listeners and identify event sources with `getSource()` and action commands.
- React to item selection and live text changes.
- Add keyboard shortcuts, input validation, mouse drawing, and keyboard movement.

## Activities

### Activity 1: First Interactive Button (`activity1-actionlistener-basics/`)

`FirstButton.java` updates a label when its button is clicked.

```bash
javac week04-event-handling/activity1-actionlistener-basics/FirstButton.java
java -cp week04-event-handling/activity1-actionlistener-basics FirstButton
```

### Activity 2: Listener Styles and Event Sources (`activity2-listener-techniques/`)

- `ListenerDemo.java` compares an inner class, anonymous class, and lambda.
- `EventSourceDemo.java` shares one listener across buttons and inspects event sources and commands.

```bash
javac week04-event-handling/activity2-listener-techniques/*.java
java -cp week04-event-handling/activity2-listener-techniques ListenerDemo
```

Replace `ListenerDemo` with `EventSourceDemo` to run the event-source example.

### Activity 3: Item and Document Listeners (`activity3-item-and-document-listeners/`)

- `ItemListenerDemo.java` reports checkbox and radio-button selection changes.
- `DocumentListenerDemo.java` counts characters as text changes.
- `LiveSearchDemo.java` filters a list as the search query changes; Ctrl+F focuses the search field.

```bash
javac week04-event-handling/activity3-item-and-document-listeners/*.java
java -cp week04-event-handling/activity3-item-and-document-listeners LiveSearchDemo
```

Replace `LiveSearchDemo` with `ItemListenerDemo` or `DocumentListenerDemo` to run another example.

### Activity 4: Counter App with Shortcuts (`activity4-counter-app/`)

`CounterApp.java` supports increment, decrement, and reset buttons, plus Ctrl+Up, Ctrl+Down, and Ctrl+R.

```bash
javac week04-event-handling/activity4-counter-app/CounterApp.java
java -cp week04-event-handling/activity4-counter-app CounterApp
```

### Activity 5: Interactive Registration Form (`activity5-interactive-form/`)

`RegistrationForm.java` checks that the name is present, the email has a basic valid format, and the password has at least eight characters. It is a local demonstration and does not submit data.

```bash
javac week04-event-handling/activity5-interactive-form/RegistrationForm.java
java -cp week04-event-handling/activity5-interactive-form RegistrationForm
```

### Activity 6: Mouse and Keyboard Events (`activity6-mouse-and-key/`)

- `DrawingApp.java` draws strokes with mouse press and drag events.
- `KeyboardMover.java` moves a square with the arrow keys or WASD.

```bash
javac week04-event-handling/activity6-mouse-and-key/*.java
java -cp week04-event-handling/activity6-mouse-and-key DrawingApp
```

Replace `DrawingApp` with `KeyboardMover` to run the keyboard example.

See `week04-event-handling/SelfAssessment.md` for review questions and suggested answers.

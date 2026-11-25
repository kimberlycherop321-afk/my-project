# Week 3: Building Swing GUIs & Layouts

- **Folder:** `week03-swing-gui/`
- **Run the GUI launcher** from the workspace root:

  ```powershell
  javac week03-swing-gui\Week3Launcher.java
  java -cp week03-swing-gui Week3Launcher
  ```

- The launcher compiles and opens the selected activity. It requires a graphical desktop environment.
- **Scripts:** From inside `week03-swing-gui/`, run `./scripts/run-week3.sh` in Git Bash/Linux/macOS or `.\scripts\run-week3.ps1` in PowerShell.

## Goals

By the end of Week 3, you should be able to:

- Create a working Swing window with `JFrame`.
- Add common Swing controls, including labels, buttons, text fields, checkboxes, and combo boxes.
- Arrange components with `FlowLayout`, `BorderLayout`, and `GridLayout`.
- Start Swing UI code safely on the Event Dispatch Thread (EDT).
- Keep the UI responsive while slow work runs in the background.

## Core Concepts

A `JFrame` is a top-level window. Place `JPanel`s inside it to group components such as labels, buttons, and text inputs.

Layout managers arrange components without fixed pixel coordinates:

- `FlowLayout` places components from left to right and wraps them when needed.
- `BorderLayout` divides its container into `NORTH`, `SOUTH`, `EAST`, `WEST`, and `CENTER`.
- `GridLayout` gives each cell in a row-and-column grid the same size.

Swing event handling and UI updates run on the EDT. Start a GUI with `SwingUtilities.invokeLater(...)`. Keep slow work off the EDT, then use `SwingUtilities.invokeLater(...)` to update the UI when the work completes.

## Activities

### Activity 1: First Swing Window (`activity1-first-window/`)

Creates a `JFrame` with a title, size, close operation, and screen-centered position.

```bash
javac week03-swing-gui/activity1-first-window/FirstWindow.java
java -cp week03-swing-gui/activity1-first-window FirstWindow
```

### Activity 2: Basic Components Tour (`activity2-basic-components/`)

Shows labels, text and password fields, a checkbox, grouped radio buttons, a combo box, a scrollable notes area, and a button that summarizes the selections.

```bash
javac week03-swing-gui/activity2-basic-components/ComponentShowcase.java
java -cp week03-swing-gui/activity2-basic-components ComponentShowcase
```

### Activity 3: Layout Managers (`activity3-layout-managers/`)

Four resizable demos show the behavior of the layouts:

- `FlowLayoutDemo.java` — left-to-right flow and wrapping
- `BorderLayoutDemo.java` — five regions and the expanding center
- `GridLayoutDemo.java` — equal-sized keypad cells
- `CombinedLayoutDemo.java` — nested panels using multiple layouts

```bash
javac week03-swing-gui/activity3-layout-managers/*.java
java -cp week03-swing-gui/activity3-layout-managers CombinedLayoutDemo
```

Replace `CombinedLayoutDemo` with `FlowLayoutDemo`, `BorderLayoutDemo`, or `GridLayoutDemo` to run a different layout example.

### Activity 4: The EDT Rule (`activity4-edt-rule/`)

- `FreezeDemo.java` intentionally blocks the EDT for four seconds to show why the window freezes.
- `FixedDemo.java` runs the slow work on a background thread and posts the result back to the EDT.

```bash
javac week03-swing-gui/activity4-edt-rule/*.java
java -cp week03-swing-gui/activity4-edt-rule FixedDemo
```

### Activity 5: Student Portal Mockup (`activity5-complete-mockup/`)

Builds a static student dashboard from nested panels and layout managers. Navigation buttons provide feedback but do not connect to a backend.

```bash
javac week03-swing-gui/activity5-complete-mockup/StudentPortal.java
java -cp week03-swing-gui/activity5-complete-mockup StudentPortal
```

See `week03-swing-gui/SelfAssessment.md` for review questions and suggested answers.

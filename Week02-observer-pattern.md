# Week 2: The Observer Pattern

- **Folder:** `Week02-observer-pattern/`
- **Run the GUI launcher** (from the workspace root):

  ```powershell
  javac Week02-observer-pattern\Week2Launcher.java
  java -cp Week02-observer-pattern Week2Launcher
  ```

- The launcher compiles the selected activity and displays its output. It requires a graphical desktop environment.
- **Scripts:** Run `bash Week02-observer-pattern/scripts/run-week2.sh` (Git Bash/Linux/macOS) or `.\Week02-observer-pattern\scripts\run-week2.ps1` (PowerShell) from the workspace root for a terminal-based menu.

## Goals

By the end of Week 2, you should be able to:

- Build the Observer Pattern (Subject + Observer) from scratch in Java.
- Explain why `java.util.Observer` and `Observable` were deprecated in Java 9.
- Refactor tightly coupled code into a clean, decoupled observer setup.
- Compare Custom Observer, `PropertyChangeListener`, and JavaFX Properties.

## Core Idea

Instead of one class hardcoding updates to every screen or logger that cares about its data, the Subject keeps a list of Observers. Whenever the Subject's data changes, it loops through the list and notifies them all.

- **Subject (Publisher):** Holds state and manages `registerObserver()`, `removeObserver()`, and `notifyObservers()`.
- **Observer (Subscriber):** Implements `update(...)` to react when notified.

## Activities

### Activity 1: Weather Station (`activity1-weather-station/`)

A `WeatherData` subject tracks temperature, humidity, and pressure, and notifies three displays whenever the weather changes:

- `CurrentConditionsDisplay` — shows current temp & humidity
- `StatisticsDisplay` — tracks min, max, and average temp
- `ForecastDisplay` — predicts weather from pressure changes

Run it:

```bash
javac Week02-observer-pattern/activity1-weather-station/*.java
java -cp Week02-observer-pattern/activity1-weather-station WeatherStationMain
```

### Activity 2: Legacy `java.util.Observer` (`activity2-legacy-observer/`)

Shows how Java's old built-in `Observable` / `Observer` worked, and why Java 9 deprecated them:

- `Observable` is a class, not an interface — so your model can't extend anything else.
- `setChanged()` is `protected` — you can't compose it easily.
- Notifications aren't type-safe (`Object` arg) and give no detail about which property changed.

See `LegacyLimitations.md` for details.

Run it:

```bash
javac Week02-observer-pattern/activity2-legacy-observer/*.java
java -cp Week02-observer-pattern/activity2-legacy-observer LegacyObserverMain
```

### Activity 3: Refactor Tightly Coupled Code (`activity3-custom-observer/`)

Compares a messy hardcoded class (`TightlyCoupledSystem.java`) with a clean observer version (`RefactoredMain.java`) that notifies `LabelDisplay`, `BarChartDisplay`, and `TextLogger` without knowing their concrete types.

```bash
javac Week02-observer-pattern/activity3-custom-observer/*.java
java -cp Week02-observer-pattern/activity3-custom-observer RefactoredMain
```

### Activity 4: Comparing Java Notification Tools (`activity4-comparison/`)

Compares three ways to observe changes in Java:

| Approach | Best For | Pros | Cons |
| :--- | :--- | :--- | :--- |
| Custom Observer | Simple, custom domain events | Full control, zero dependencies | You write the boilerplate |
| `PropertyChangeListener` | Standard JavaBeans / Swing | Built into the JDK, reports old & new values | Uses string property names |
| JavaFX Properties | Modern JavaFX UIs | Built-in UI binding (`bind()`) | Requires JavaFX |

Run the JDK-only comparison example:

```bash
javac Week02-observer-pattern/activity4-comparison/*.java
java -cp Week02-observer-pattern/activity4-comparison ComparisonMain
```

The JavaFX row is a conceptual comparison; the example avoids a JavaFX dependency so the activities compile with a standard JDK.

See `Comparison.md` and `Week02-observer-pattern/SelfAssessment.md` for the full comparison and self-check.
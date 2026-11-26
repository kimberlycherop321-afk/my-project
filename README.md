# Java Event-Driven Programming Portfolio

This repository is arranged in course order, from the Week 1 Java fundamentals and event-driven exercises through the Week 10 University Management System capstone. Each week links to its code, guide, and launch option.

## Requirements

- JDK 17 or newer.
- Maven 3.8 or newer for Weeks 6–10.
- A graphical desktop for Swing and JavaFX applications.
- For the Week 10 database features, configure a PostgreSQL connection as described in the [capstone guide](./week10-university-system.md). Node.js/npm is optional and only needed for the Week 10 npm commands.

## Course Modules: Weeks 1–10

Work through the modules in order. The guides describe the activities and their individual source files; each code folder groups its files by activity.

| Week | Topic | Code and files | Guide | Launcher / script |
| --- | --- | --- | --- | --- |
| 1 | Event-driven basics and bug fixes | [Week 1 files](#week-1) (stored at the repository root) | [Bug fixes](./BugFixes.md) · [Self-assessment](./SelfAssessment.md) | Run individual Java classes; see [Week 1 files](#week-1) |
| 2 | Observer Pattern | [Week02-observer-pattern/](./Week02-observer-pattern/) | [Week 2 guide](./Week02-observer-pattern.md) | [Shell](./Week02-observer-pattern/scripts/run-week2.sh) · [PowerShell](./Week02-observer-pattern/scripts/run-week2.ps1) |
| 3 | Swing GUIs and layouts | [week03-swing-gui/](./week03-swing-gui/) | [Week 3 guide](./week03-swing-gui.md) | [Shell](./week03-swing-gui/scripts/run-week3.sh) · [PowerShell](./week03-swing-gui/scripts/run-week3.ps1) |
| 4 | Event handling in Swing | [week04-event-handling/](./week04-event-handling/) | [Week 4 guide](./week04-event-handling/week04-event-handling.md) | [Shell](./week04-event-handling/scripts/run-week4.sh) · [PowerShell](./week04-event-handling/scripts/run-week4.ps1) |
| 5 | Advanced Swing components | [week05-advanced-swing/](./week05-advanced-swing/) | [Week 5 guide](./week05-advanced-swing/week05-advanced-swing.md) | [Shell](./week05-advanced-swing/scripts/run-week5.sh) · [PowerShell](./week05-advanced-swing/scripts/run-week5.ps1) |
| 6 | Introduction to JavaFX | [week06-javafx/](./week06-javafx/) | [Week 6 guide](./week06-javafx.md) | `mvn -f week06-javafx/pom.xml javafx:run` · [Shell](./scripts/run-week6.sh) · [PowerShell](./scripts/run-week6.ps1) |
| 7 | JavaFX event handling | [week07-javafx-events/](./week07-javafx-events/) | [Week 7 guide](./week07-javafx-events.md) | `mvn -f week07-javafx-events/pom.xml javafx:run` · [Shell](./scripts/run-week7.sh) · [PowerShell](./scripts/run-week7.ps1) |
| 8 | JavaFX properties and bindings | [week08-javafx-bindings/](./week08-javafx-bindings/) | [Week 8 guide](./week08-javafx-bindings.md) | `mvn -f week08-javafx-bindings/pom.xml javafx:run` · [Shell](./scripts/run-week8.sh) · [PowerShell](./scripts/run-week8.ps1) |
| 9 | Asynchronous programming in JavaFX | [week09-async-javafx/](./week09-async-javafx/) | [Week 9 guide](./week09-async-javafx.md) | `mvn -f week09-async-javafx/pom.xml javafx:run` · [Shell](./scripts/run-week9.sh) · [PowerShell](./scripts/run-week9.ps1) |
| 10 | Capstone: University Management System | [UniversitySystem/](./UniversitySystem/) | [Week 10 guide](./week10-university-system.md) | `mvn -f UniversitySystem/pom.xml javafx:run` · [Shell](./scripts/run-university.sh) · [PowerShell](./scripts/run-university.ps1) |

For Weeks 2–5, use the linked launcher scripts or follow each guide's compile-and-run commands. For Weeks 6–10, run the Maven command shown above or use its linked script. On Windows, use the PowerShell script; on Linux/macOS or Git Bash, use the shell script.

<a id="week-1"></a>
## Week 1 — Event-Driven Basics and Bug Fixes

Week 1 examples are kept at the repository root rather than in a separate folder:

- Calculator comparison: [ProceduralCalculator.java](./ProceduralCalculator.java) and [EventDrivenCalculator.java](./EventDrivenCalculator.java).
- Smart-home event example: [SmartHomePrototype.java](./SmartHomePrototype.java).
- Bug demonstrations: [Bug1_BlockingEDT.java](./Bug1_BlockingEDT.java), [Bug2_MissingListener.java](./Bug2_MissingListener.java), [Bug3_WrongThread.java](./Bug3_WrongThread.java), and [Bug4_MemoryLeak.java](./Bug4_MemoryLeak.java).
- Explanations and review: [BugFixes.md](./BugFixes.md) and [SelfAssessment.md](./SelfAssessment.md).

Compile and run a Week 1 example from the repository root with the JDK, for example:

```bash
javac EventDrivenCalculator.java
java EventDrivenCalculator
```

## Week 10 Database Setup

The capstone uses PostgreSQL (Neon supported). Copy [`.env.example`](./.env.example) to `.env` and set your own `DATABASE_URL` before launching or running the integration test. The `.env` file is ignored by Git; do not commit database credentials. See the [Week 10 guide](./week10-university-system.md) for run and test instructions.

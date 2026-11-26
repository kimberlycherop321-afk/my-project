# Event-Driven Programming in Java

A collection of Java activities and desktop projects covering event-driven programming, Swing, JavaFX, and a university management capstone.

## Requirements

- JDK 17 or newer.
- Maven for JavaFX projects (Weeks 6–10).
- A graphical desktop environment for the GUI applications.

## Weekly Modules

The projects are arranged in course order. Each guide includes activity details and instructions.

| Week | Topic | Project | Guide | Run |
| --- | --- | --- | --- | --- |
| 1 | Event-driven basics and bug fixes | [Week 1 files](#week-1-files) | [Bug fixes](./BugFixes.md) · [Self-assessment](./SelfAssessment.md) | Run individual Java files from the repository root |
| 2 | Observer Pattern | [Week02-observer-pattern/](./Week02-observer-pattern/) | [Week 2 guide](./Week02-observer-pattern.md) | [Git Bash/Linux/macOS](./Week02-observer-pattern/scripts/run-week2.sh) · [PowerShell](./Week02-observer-pattern/scripts/run-week2.ps1) |
| 3 | Swing GUIs and layouts | [week03-swing-gui/](./week03-swing-gui/) | [Week 3 guide](./week03-swing-gui.md) | [Git Bash/Linux/macOS](./week03-swing-gui/scripts/run-week3.sh) · [PowerShell](./week03-swing-gui/scripts/run-week3.ps1) |
| 4 | Event handling in Swing | [week04-event-handling/](./week04-event-handling/) | [Week 4 guide](./week04-event-handling/week04-event-handling.md) | [Git Bash/Linux/macOS](./week04-event-handling/scripts/run-week4.sh) · [PowerShell](./week04-event-handling/scripts/run-week4.ps1) |
| 5 | Advanced Swing components | [week05-advanced-swing/](./week05-advanced-swing/) | [Week 5 guide](./week05-advanced-swing/week05-advanced-swing.md) | [Git Bash/Linux/macOS](./week05-advanced-swing/scripts/run-week5.sh) · [PowerShell](./week05-advanced-swing/scripts/run-week5.ps1) |
| 6 | Introduction to JavaFX | [week06-javafx/](./week06-javafx/) | [Week 6 guide](./week06-javafx.md) | `mvn -f week06-javafx/pom.xml javafx:run` · [Shell script](./scripts/run-week6.sh) · [PowerShell script](./scripts/run-week6.ps1) |
| 7 | JavaFX event handling | [week07-javafx-events/](./week07-javafx-events/) | [Week 7 guide](./week07-javafx-events.md) | `mvn -f week07-javafx-events/pom.xml javafx:run` · [Shell script](./scripts/run-week7.sh) · [PowerShell script](./scripts/run-week7.ps1) |
| 8 | JavaFX properties and bindings | [week08-javafx-bindings/](./week08-javafx-bindings/) | [Week 8 guide](./week08-javafx-bindings.md) | `mvn -f week08-javafx-bindings/pom.xml javafx:run` · [Shell script](./scripts/run-week8.sh) · [PowerShell script](./scripts/run-week8.ps1) |
| 9 | Asynchronous programming in JavaFX | [week09-async-javafx/](./week09-async-javafx/) | [Week 9 guide](./week09-async-javafx.md) | `mvn -f week09-async-javafx/pom.xml javafx:run` · [Shell script](./scripts/run-week9.sh) · [PowerShell script](./scripts/run-week9.ps1) |
| 10 | University Management System capstone | [UniversitySystem/](./UniversitySystem/) | [Week 10 guide](./week10-university-system.md) | `mvn -f UniversitySystem/pom.xml javafx:run` · [Shell script](./scripts/run-university.sh) · [PowerShell script](./scripts/run-university.ps1) |

Use the `.sh` scripts from Git Bash, Linux, or macOS and the `.ps1` scripts from PowerShell. Run scripts from the repository root.

## Week 1 Files

Week 1 exercises are at the repository root:

- Calculators: [ProceduralCalculator.java](./ProceduralCalculator.java) and [EventDrivenCalculator.java](./EventDrivenCalculator.java).
- Smart-home example: [SmartHomePrototype.java](./SmartHomePrototype.java).
- Bug demonstrations: [Bug1_BlockingEDT.java](./Bug1_BlockingEDT.java), [Bug2_MissingListener.java](./Bug2_MissingListener.java), [Bug3_WrongThread.java](./Bug3_WrongThread.java), and [Bug4_MemoryLeak.java](./Bug4_MemoryLeak.java).

## Week 10 Database Setup

Copy [`.env.example`](./.env.example) to `.env` and set `DATABASE_URL` before using the capstone's database features. Keep credentials private and do not commit `.env`. See [the Week 10 guide](./week10-university-system.md) and [UniversitySystem README](./UniversitySystem/README.md) for setup and testing details.

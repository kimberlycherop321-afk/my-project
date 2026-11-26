# Java Event-Driven Programming Portfolio

Java exercises and desktop applications covering event-driven programming, Swing, JavaFX, and a university management capstone.

## Requirements

- JDK 17 or newer.
- Maven 3.8 or newer for JavaFX projects.
- A graphical desktop for GUI applications.
- PostgreSQL connection settings for the University Management System.

## Weekly Modules

Use the modules in order. Each project folder groups its activity files; its linked guide describes the activities and run instructions.

| Week | Topic | Project files | Guide | Quick launch |
| --- | --- | --- | --- | --- |
| 1 | Event-driven basics and bug fixes | [Week 1 files](#week-1-files) at repository root | [Bug fixes](./BugFixes.md) · [Self-assessment](./SelfAssessment.md) | Compile and run individual Java files from the repository root |
| 2 | Observer Pattern | [Week02-observer-pattern/](./Week02-observer-pattern/) | [Week 2 guide](./Week02-observer-pattern.md) | [Shell launcher](./Week02-observer-pattern/scripts/run-week2.sh) · [PowerShell launcher](./Week02-observer-pattern/scripts/run-week2.ps1) |
| 3 | Swing GUIs and layouts | [week03-swing-gui/](./week03-swing-gui/) | [Week 3 guide](./week03-swing-gui.md) | [Shell launcher](./week03-swing-gui/scripts/run-week3.sh) · [PowerShell launcher](./week03-swing-gui/scripts/run-week3.ps1) |
| 4 | Event handling in Swing | [week04-event-handling/](./week04-event-handling/) | [Week 4 guide](./week04-event-handling/week04-event-handling.md) | [Shell launcher](./week04-event-handling/scripts/run-week4.sh) · [PowerShell launcher](./week04-event-handling/scripts/run-week4.ps1) |
| 5 | Advanced Swing components | [week05-advanced-swing/](./week05-advanced-swing/) | [Week 5 guide](./week05-advanced-swing/week05-advanced-swing.md) | [Shell launcher](./week05-advanced-swing/scripts/run-week5.sh) · [PowerShell launcher](./week05-advanced-swing/scripts/run-week5.ps1) |
| 6 | Introduction to JavaFX | [week06-javafx/](./week06-javafx/) | [Week 6 guide](./week06-javafx.md) | `mvn -f week06-javafx/pom.xml javafx:run` · [Shell](./scripts/run-week6.sh) · [PowerShell](./scripts/run-week6.ps1) |
| 7 | JavaFX event handling | [week07-javafx-events/](./week07-javafx-events/) | [Week 7 guide](./week07-javafx-events.md) | `mvn -f week07-javafx-events/pom.xml javafx:run` · [Shell](./scripts/run-week7.sh) · [PowerShell](./scripts/run-week7.ps1) |
| 8 | JavaFX properties and bindings | [week08-javafx-bindings/](./week08-javafx-bindings/) | [Week 8 guide](./week08-javafx-bindings.md) | `mvn -f week08-javafx-bindings/pom.xml javafx:run` · [Shell](./scripts/run-week8.sh) · [PowerShell](./scripts/run-week8.ps1) |
| 9 | Asynchronous programming in JavaFX | [week09-async-javafx/](./week09-async-javafx/) | [Week 9 guide](./week09-async-javafx.md) | `mvn -f week09-async-javafx/pom.xml javafx:run` · [Shell](./scripts/run-week9.sh) · [PowerShell](./scripts/run-week9.ps1) |
| 10 | University Management System capstone | [UniversitySystem/](./UniversitySystem/) | [Week 10 guide](./week10-university-system.md) | `mvn -f UniversitySystem/pom.xml javafx:run` · [Shell](./scripts/run-university.sh) · [PowerShell](./scripts/run-university.ps1) |

For Weeks 2–5, run a launcher from the linked project scripts or follow the guide's compile-and-run commands. For Weeks 6–10, use the Maven command or a matching script.

## Week 1 Files

- Calculators: [ProceduralCalculator.java](./ProceduralCalculator.java), [EventDrivenCalculator.java](./EventDrivenCalculator.java).
- Smart-home example: [SmartHomePrototype.java](./SmartHomePrototype.java).
- Bug demonstrations: [Bug1_BlockingEDT.java](./Bug1_BlockingEDT.java), [Bug2_MissingListener.java](./Bug2_MissingListener.java), [Bug3_WrongThread.java](./Bug3_WrongThread.java), [Bug4_MemoryLeak.java](./Bug4_MemoryLeak.java).

## Capstone Database Setup

Copy [`.env.example`](./.env.example) to `.env` and set your own `DATABASE_URL` before using the capstone's database features. `.env` is ignored by Git; do not commit real credentials. See the [Week 10 guide](./week10-university-system.md) for details.

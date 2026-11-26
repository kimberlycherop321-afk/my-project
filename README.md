Event-Driven Programming in Java

A collection of hands-on activities, guides, and Java projects exploring Event-Driven Programming, the Observer Pattern, Swing, JavaFX, and a final Capstone application.

Requirements

- Java JDK 11+ (JDK 17 or 21 recommended)
- Maven (required for JavaFX projects in Weeks 6–10)

Verify your setup:

java -version
javac -version
mvn -version

Quick Start

1. Build the Entire Project

Compile all Java files across the repository:

Environment| Build| Clean Rebuild
Git Bash / macOS / Linux| "./build"| "./build --clean"
Windows CMD / PowerShell| ".\build.bat"| ".\build.bat --clean"
npm (optional)| "npm run build"| "npm run build:clean"

2. Launch the Interactive Menu

The easiest way to access and run activities from any week is through the interactive menu.

Git Bash / macOS / Linux:

./scripts/run-all.sh

Windows PowerShell:

.\scripts\run-all.ps1

Note for Git Bash users on Windows: Always use forward slashes ("./scripts/...") rather than backslashes (".\scripts\...").

3. Launch a Visual Dashboard Directly

For Weeks 2–5, you can launch each week's GUI dashboard directly using the following commands:

java -cp Week02-observer-pattern Week2Launcher
java -cp week03-swing-gui Week3Launcher
java -cp week04-event-handling Week4Launcher
java -cp week05-advanced-swing Week5Launcher

For Weeks 6–10 (JavaFX + Maven):

Run the following commands to launch each JavaFX application:

mvn -f week06-javafx/pom.xml javafx:run
mvn -f week07-javafx-events/pom.xml javafx:run
mvn -f week08-javafx-bindings/pom.xml javafx:run
mvn -f week09-async-javafx/pom.xml javafx:run
mvn -f UniversitySystem/pom.xml javafx:run

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
Script Flags

Each weekly script provides the following command-line options:

- "--all" / "-All" — Execute all activities available for the selected week.
- "--launcher" / "-Launcher" — Start the graphical launcher for Weeks 2–9.




WEEK 10 Capstone: University Management System

The final project is a JavaFX-based desktop application integrated with a PostgreSQL database hosted on Neon.

Note: Instead of using a locally installed MySQL server, this project uses Neon, a serverless cloud-based PostgreSQL service. This makes database setup easier and allows the application to run without requiring users to install and configure a database locally.

Prerequisites and Environment Configuration

Before running the application, complete the following setup:

1. Visit "neon.tech" (https://neon.tech) and create a free PostgreSQL database.
2. Copy the database connection string provided by Neon.
3. In the root directory of the project, duplicate ".env.example" and rename the copy to ".env" (or ".env.local").
4. Add your Neon database connection string to the "DATABASE_URL" variable:

DATABASE_URL="postgresql://USER:PASSWORD@YOUR-NEON-HOST.neon.tech/neondb?sslmode=require"

Database initialization: The application automatically creates the "programs" and "students" tables and inserts sample program records when it is launched for the first time.

Running and Testing the Application

Start the University Management System

Using Maven:

mvn -f UniversitySystem/pom.xml javafx:run

Alternatively, run it through npm:

npm run start:university

Run the database integration tests

npm run test:university

Executing Individual Java Activities

For convenience, individual Java activities from Weeks 1–5 can also be compiled and executed directly from the project's root directory.

General commands:

javac <folder>/<FileName>.java
java -cp <folder> <MainClassName>

Example:

javac Week1_Activity/EventDrivenCalculator.java
java -cp Week1_Activity EventDrivenCalculator

Author and License

Author: Kimberly Cherop
Email: kimberlycherop321@gmail.com

License: ISC License
Copyright (c) 2026 Kimberly Cherop.

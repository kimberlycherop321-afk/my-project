# Java Event-Driven Programming Portfolio

A collection of lab exercises, reference guides, and desktop GUI projects exploring event-driven architecture in Java—covering the Observer Pattern, Swing, JavaFX, and a final Capstone application.

## Environment Setup

- **Java Development Kit:** JDK 17 or 21 recommended (JDK 11 minimum)
- **Build Tool:** Apache Maven (required for JavaFX modules in Weeks 6–10)

Verify your setup in the terminal:

    java -version
    javac -version
    mvn -version

## Getting Started

### 1. Compile the Project
Build all Java source files across the repository:

| Environment | Standard Build | Clean Rebuild |
| :--- | :--- | :--- |
| **Linux / macOS / Git Bash** | `./build` | `./build --clean` |
| **Windows (CMD / PowerShell)** | `.\build.bat` | `.\build.bat --clean` |
| **Node Scripts (Optional)** | `npm run build` | `npm run build:clean` |

### 2. Run the Interactive Launcher Menu
Use the CLI menu script to select and run individual weekly exercises:
* **Linux / macOS / Git Bash:** `./scripts/run-all.sh` *(always use forward slashes in Git Bash)*
* **Windows PowerShell:** `.\scripts\run-all.ps1`

### 3. Launch Modules Directly
**Swing GUI Dashboards (Weeks 2–5):**

    java -cp Week02-observer-pattern Week2Launcher
    java -cp week03-swing-gui Week3Launcher
    java -cp week04-event-handling Week4Launcher
    java -cp week05-advanced-swing Week5Launcher

For the Week 2 activity breakdown and individual run commands, see the [Observer Pattern guide](./Week02-observer-pattern.md).

**JavaFX Applications via Maven (Weeks 6–10):**

    mvn -f week06-javafx/pom.xml javafx:run
    mvn -f week07-javafx-events/pom.xml javafx:run
    mvn -f week08-javafx-bindings/pom.xml javafx:run
    mvn -f week09-async-javafx/pom.xml javafx:run
    mvn -f UniversitySystem/pom.xml javafx:run

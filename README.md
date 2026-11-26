# Java Event-Driven Programming Portfolio

A collection of Java exercises and desktop applications covering event-driven programming, Swing, JavaFX, and a university management capstone.

## Requirements

- JDK 17 or newer.
- Maven 3.8 or newer for JavaFX projects.
- A graphical desktop for the GUI applications.
- PostgreSQL connection settings for the university management application.

## Running the Applications

For Swing activities, use the launcher or run instructions in the relevant project guide.

For a JavaFX project, run Maven from the repository root with its project POM, for example:

```bash
mvn -f week06-javafx/pom.xml javafx:run
```

The university management app can be started with:

```bash
mvn -f UniversitySystem/pom.xml javafx:run
```

Configure `DATABASE_URL` before opening the student management view. See the [University System guide](./week10-university-system.md) and [`.env.example`](./.env.example). Do not commit real database credentials.

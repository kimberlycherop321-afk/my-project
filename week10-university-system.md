# Week 10 Capstone: University Management System

- **Project folder:** `UniversitySystem/`
- **Run:** `mvn -f UniversitySystem/pom.xml javafx:run` or `npm run start:university`.
- **Scripts:** `./scripts/run-university.sh` in Git Bash/Linux/macOS or `.\scripts\run-university.ps1` in PowerShell.
- **Database:** PostgreSQL (Neon supported). Copy the root `.env.example` to `.env` and set `DATABASE_URL`; never commit real credentials.

The JavaFX app provides dashboard and student-management views. It uses observable JavaFX models, a searchable/sortable `TableView`, form validation, and background `Task`s for database operations. Startup initializes the tables and default programs. A scheduled heartbeat runs off the UI thread and is shut down when the app exits.

To run the database integration test (requires a configured database), use `npm run test:university`. It creates a unique temporary student and cleans it up after checking insert, query, update, and delete. Use `npm run seed:university` to add optional sample students.

See [UniversitySystem/README.md](./UniversitySystem/README.md) for configuration and operation details.

# University Management System

The Week 10 JavaFX capstone manages university students and programs using PostgreSQL. All database work is performed in background tasks so the JavaFX Application Thread remains responsive.

## Requirements

- JDK 17 or newer.
- Maven 3.8 or newer.
- A PostgreSQL database with SSL enabled (Neon is supported).

## Database configuration

From the repository root, copy `.env.example` to `.env` and set `DATABASE_URL` to your own PostgreSQL connection string:

```text
DATABASE_URL="postgresql://USER:PASSWORD@YOUR-NEON-HOST.neon.tech/neondb?sslmode=require"
```

You can instead set `DATABASE_URL` as an environment variable. The environment variable takes precedence over `.env.local` and `.env`. Credentials are not included in the repository; `.env` and `.env.local` are ignored by Git.

On the first connection the application creates the `programs` and `students` tables, creates a case-insensitive unique index for student email, and inserts the default programs if they do not already exist. The app does not automatically add sample student records.

## Run the application

From the repository root:

```bash
mvn -f UniversitySystem/pom.xml javafx:run
```

Or use `npm run start:university` or the menu scripts `./scripts/run-university.sh` (Git Bash/Linux/macOS) and `.\scripts\run-university.ps1` (PowerShell).

Open **Students** to load records, search, add, update, or delete students. The Add button stays disabled until the required fields contain a valid name, email, and program.

## Test and seed data

The database integration test requires a configured database. It inserts a uniquely named temporary record, verifies insert/query/update/delete, then removes the record:

```bash
npm run test:university
```

You can seed two sample students explicitly with:

```bash
npm run seed:university
```

Existing sample-email rows are not duplicated.

After packaging, you can also run the headless database test directly on Windows:

```powershell
mvn -f UniversitySystem/pom.xml package
java -cp "UniversitySystem/target/classes;UniversitySystem/lib/postgresql-42.7.3.jar" com.university.UniversitySystemTest
```

On macOS/Linux, use `:` instead of `;` between the classpath entries. The standalone JDBC jar is also used by the npm test and seed scripts; normal app execution uses the Maven-managed driver.

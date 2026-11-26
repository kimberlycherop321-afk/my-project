#!/usr/bin/env sh
set -eu

ROOT=$(CDPATH= cd -- "$(dirname -- "$0")/.." && pwd)
POM="$ROOT/UniversitySystem/pom.xml"

echo "Week 10: University Management System"
echo "The app requires DATABASE_URL in the environment or a .env file in the repository root."
mvn -f "$POM" javafx:run

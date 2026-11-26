#!/usr/bin/env sh
set -eu

ROOT=$(CDPATH= cd -- "$(dirname -- "$0")/.." && pwd)
POM="$ROOT/week09-async-javafx/pom.xml"

echo "Week 9: Asynchronous Programming in JavaFX"
echo "1. Freeze Problem"
echo "2. Background Task"
echo "3. Live Progress"
echo "4. Reusable Service"
printf "Choose an activity (1-4): "
read -r choice

case "$choice" in
  1) main="com.example.FreezeDemo" ;;
  2) main="com.example.TaskDemo" ;;
  3) main="com.example.ProgressDemo" ;;
  4) main="com.example.ServiceDemo" ;;
  *) echo "Invalid choice."; exit 1 ;;
esac

mvn -f "$POM" javafx:run "-Djavafx.mainClass=$main"

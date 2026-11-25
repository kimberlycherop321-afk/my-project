#!/usr/bin/env sh
set -eu

ROOT=$(CDPATH= cd -- "$(dirname -- "$0")/.." && pwd)
POM="$ROOT/week07-javafx-events/pom.xml"

echo "Week 7: Event Handling in JavaFX"
echo "1. Event Propagation"
echo "2. Mouse and Keyboard Events"
echo "3. Drag and Drop"
echo "4. Custom Events"
printf "Choose an activity (1-4): "
read -r choice

case "$choice" in
  1) main="com.example.EventPropagationDemo" ;;
  2) main="com.example.MouseKeyDemo" ;;
  3) main="com.example.DragDropDemo" ;;
  4) main="com.example.CustomEventDemo" ;;
  *) echo "Invalid choice."; exit 1 ;;
esac

mvn -f "$POM" javafx:run "-Djavafx.mainClass=$main"

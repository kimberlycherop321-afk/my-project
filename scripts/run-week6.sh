#!/usr/bin/env sh
set -eu

ROOT=$(CDPATH= cd -- "$(dirname -- "$0")/.." && pwd)
POM="$ROOT/week06-javafx/pom.xml"

echo "Week 6: Introduction to Modern JavaFX"
echo "1. First JavaFX Window"
echo "2. JavaFX Layouts"
echo "3. FXML Login"
echo "4. Dashboard"
printf "Choose an activity (1-4): "
read -r choice

case "$choice" in
  1) main="programmaticdemo.Main" ;;
  2) main="programmaticdemo.LayoutDemo" ;;
  3) main="programmaticdemo.FXMLApp" ;;
  4) main="programmaticdemo.DashboardApp" ;;
  *) echo "Invalid choice."; exit 1 ;;
esac

mvn -f "$POM" javafx:run "-Djavafx.mainClass=$main"

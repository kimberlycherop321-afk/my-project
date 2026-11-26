#!/usr/bin/env sh
set -eu

ROOT=$(CDPATH= cd -- "$(dirname -- "$0")/.." && pwd)
POM="$ROOT/week08-javafx-bindings/pom.xml"

echo "Week 8: Properties and Bindings in JavaFX"
echo "1. Observable Model and Listeners"
echo "2. Unidirectional Binding"
echo "3. Bidirectional Binding"
echo "4. Reactive Form"
printf "Choose an activity (1-4): "
read -r choice

case "$choice" in
  1) main="com.example.ListenerDemo" ;;
  2) main="com.example.UnidirectionalDemo" ;;
  3) main="com.example.BidirectionalDemo" ;;
  4) main="com.example.ReactiveFormApp" ;;
  *) echo "Invalid choice."; exit 1 ;;
esac

mvn -f "$POM" javafx:run "-Djavafx.mainClass=$main"

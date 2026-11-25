#!/usr/bin/env sh
set -e

ROOT=$(CDPATH= cd -- "$(dirname -- "$0")/.." && pwd)

echo "Week 2: Observer Pattern"
echo "1. Weather Station"
echo "2. Legacy Observer"
echo "3. Custom Observer Refactor"
echo "4. Notification Comparison"
printf "Choose an activity (1-4): "
read -r choice

case "$choice" in
  1) folder="$ROOT/activity1-weather-station"; main="WeatherStationMain" ;;
  2) folder="$ROOT/activity2-legacy-observer"; main="LegacyObserverMain" ;;
  3) folder="$ROOT/activity3-custom-observer"; main="RefactoredMain" ;;
  4) folder="$ROOT/activity4-comparison"; main="ComparisonMain" ;;
  *) echo "Invalid choice."; exit 1 ;;
esac

javac "$folder"/*.java
java -cp "$folder" "$main"

#!/usr/bin/env sh
set -eu

ROOT=$(CDPATH= cd -- "$(dirname -- "$0")/.." && pwd)

echo "Week 3: Swing GUIs & Layouts"
echo "1. First Swing Window"
echo "2. Basic Components Tour"
echo "3. FlowLayout Demo"
echo "4. BorderLayout Demo"
echo "5. GridLayout Demo"
echo "6. Combined Layout Demo"
echo "7. Freeze the EDT (intentional)"
echo "8. Keep the EDT Responsive"
echo "9. Student Portal Mockup"
printf "Choose an activity (1-9): "
read -r choice

case "$choice" in
  1) folder="$ROOT/activity1-first-window"; main="FirstWindow" ;;
  2) folder="$ROOT/activity2-basic-components"; main="ComponentShowcase" ;;
  3) folder="$ROOT/activity3-layout-managers"; main="FlowLayoutDemo" ;;
  4) folder="$ROOT/activity3-layout-managers"; main="BorderLayoutDemo" ;;
  5) folder="$ROOT/activity3-layout-managers"; main="GridLayoutDemo" ;;
  6) folder="$ROOT/activity3-layout-managers"; main="CombinedLayoutDemo" ;;
  7) folder="$ROOT/activity4-edt-rule"; main="FreezeDemo" ;;
  8) folder="$ROOT/activity4-edt-rule"; main="FixedDemo" ;;
  9) folder="$ROOT/activity5-complete-mockup"; main="StudentPortal" ;;
  *) echo "Invalid choice."; exit 1 ;;
esac

javac -Xlint:all -Werror "$folder"/*.java
java -cp "$folder" "$main"

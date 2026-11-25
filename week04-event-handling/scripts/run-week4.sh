#!/usr/bin/env sh
set -eu

ROOT=$(CDPATH= cd -- "$(dirname -- "$0")/.." && pwd)

echo "Week 4: Event Handling in Swing"
echo "1. First Interactive Button"
echo "2. Listener Styles"
echo "3. Event Sources"
echo "4. ItemListener"
echo "5. DocumentListener Character Counter"
echo "6. Live Search"
echo "7. Counter App with Shortcuts"
echo "8. Interactive Registration Form"
echo "9. Mouse Drawing App"
echo "10. Keyboard Mover"
printf "Choose an activity (1-10): "
read -r choice

case "$choice" in
  1) folder="$ROOT/activity1-actionlistener-basics"; main="FirstButton" ;;
  2) folder="$ROOT/activity2-listener-techniques"; main="ListenerDemo" ;;
  3) folder="$ROOT/activity2-listener-techniques"; main="EventSourceDemo" ;;
  4) folder="$ROOT/activity3-item-and-document-listeners"; main="ItemListenerDemo" ;;
  5) folder="$ROOT/activity3-item-and-document-listeners"; main="DocumentListenerDemo" ;;
  6) folder="$ROOT/activity3-item-and-document-listeners"; main="LiveSearchDemo" ;;
  7) folder="$ROOT/activity4-counter-app"; main="CounterApp" ;;
  8) folder="$ROOT/activity5-interactive-form"; main="RegistrationForm" ;;
  9) folder="$ROOT/activity6-mouse-and-key"; main="DrawingApp" ;;
  10) folder="$ROOT/activity6-mouse-and-key"; main="KeyboardMover" ;;
  *) echo "Invalid choice."; exit 1 ;;
esac

javac -Xlint:all -Werror "$folder"/*.java
java -cp "$folder" "$main"

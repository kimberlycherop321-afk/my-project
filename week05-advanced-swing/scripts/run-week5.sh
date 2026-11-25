#!/usr/bin/env sh
set -eu

ROOT=$(CDPATH= cd -- "$(dirname -- "$0")/.." && pwd)

echo "Week 5: Advanced Swing Components"
echo "1. JList and Custom Renderer"
echo "2. JTable and Table Model"
echo "3. JTree and JTabbedPane"
echo "4. Menus, Toolbar, and Dialogs"
echo "5. Capstone File Manager"
printf "Choose an activity (1-5): "
read -r choice

case "$choice" in
  1) folder="$ROOT/activity1-jlist-renderer"; main="ContactListDemo" ;;
  2) folder="$ROOT/activity2-jtable"; main="StudentTableDemo" ;;
  3) folder="$ROOT/activity3-jtree-tabs"; main="FileExplorerDemo" ;;
  4) folder="$ROOT/activity4-menus-dialogs"; main="MiniEditor" ;;
  5) folder="$ROOT/activity5-capstone-file-manager"; main="FileManager" ;;
  *) echo "Invalid choice."; exit 1 ;;
esac

javac -Xlint:all -Werror "$folder"/*.java
java -cp "$folder" "$main"

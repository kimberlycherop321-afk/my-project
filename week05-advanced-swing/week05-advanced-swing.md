# Week 5: Advanced Swing Components

- **Folder:** `week05-advanced-swing/`
- **Run the GUI launcher** from the workspace root:

  ```powershell
  javac week05-advanced-swing\Week5Launcher.java
  java -cp week05-advanced-swing Week5Launcher
  ```

- The launcher compiles and opens the selected activity. It requires a graphical desktop environment and a JDK.
- **Scripts:** From inside `week05-advanced-swing/`, run `./scripts/run-week5.sh` in Git Bash/Linux/macOS or `.\scripts\run-week5.ps1` in PowerShell.

## Goals

- Build scrollable lists with `JList` and custom renderers.
- Display, sort, edit, add, and delete tabular data with `JTable` and `DefaultTableModel`.
- Organize a tree and tabbed viewer with `JTree`, `JTabbedPane`, and `JSplitPane`.
- Add menus, toolbars, keyboard accelerators, and file dialogs.
- Combine advanced Swing components in a read-only local file browser.

## Activities

### Activity 1: JList and Custom Renderer (`activity1-jlist-renderer/`)

`ContactListDemo.java` renders each contact with a name, email, and colored status badge.

```bash
javac week05-advanced-swing/activity1-jlist-renderer/ContactListDemo.java
java -cp week05-advanced-swing/activity1-jlist-renderer ContactListDemo
```

### Activity 2: JTable and Table Models (`activity2-jtable/`)

`StudentTableDemo.java` uses a sortable `JTable` backed by `DefaultTableModel`. Edit student values, add rows, or delete the selected row.

```bash
javac week05-advanced-swing/activity2-jtable/StudentTableDemo.java
java -cp week05-advanced-swing/activity2-jtable StudentTableDemo
```

### Activity 3: JTree and JTabbedPane (`activity3-jtree-tabs/`)

`FileExplorerDemo.java` connects a sample workspace tree to an overview and details viewer in a `JTabbedPane`, arranged with a draggable `JSplitPane`.

```bash
javac week05-advanced-swing/activity3-jtree-tabs/FileExplorerDemo.java
java -cp week05-advanced-swing/activity3-jtree-tabs FileExplorerDemo
```

### Activity 4: Menus, Toolbars, and Dialogs (`activity4-menus-dialogs/`)

`MiniEditor.java` demonstrates File/Edit/Help menus, a toolbar, Ctrl+O and Ctrl+S, and `JFileChooser` open/save dialogs. Text is read and written as UTF-8.

```bash
javac week05-advanced-swing/activity4-menus-dialogs/MiniEditor.java
java -cp week05-advanced-swing/activity4-menus-dialogs MiniEditor
```

### Activity 5: Capstone File Manager (`activity5-capstone-file-manager/`)

`FileManager.java` combines a lazy-loading folder `JTree`, sortable file `JTable`, toolbar, menus, folder chooser, and file-details dialogs. It does not modify files.

```bash
javac week05-advanced-swing/activity5-capstone-file-manager/FileManager.java
java -cp week05-advanced-swing/activity5-capstone-file-manager FileManager
```

See `week05-advanced-swing/SelfAssessment.md` for review questions and suggested answers.

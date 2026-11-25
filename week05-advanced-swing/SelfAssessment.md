# Week 5 Self-Assessment: Advanced Swing Components

## Questions

1. What is the difference between a component and its model in Swing?
2. Why should a `JList` be placed inside a `JScrollPane`?
3. What is the purpose of a custom `ListCellRenderer`?
4. Why should table data be added to `DefaultTableModel` rather than directly to `JTable`?
5. How does a `TableRowSorter` affect row indexes?
6. What does `JSplitPane` provide?
7. How do `JTree` selection events update the tabbed viewer in `FileExplorerDemo`?
8. What is the purpose of `JTabbedPane`?
9. How do the Open and Save actions in `MiniEditor` use `JFileChooser`?
10. What is the purpose of a menu accelerator such as Ctrl+S?
11. Why does the capstone load subfolders when a tree node is expanded?
12. Which file operations does the capstone perform, and which does it avoid?

## Suggested Answers

1. A component displays and receives interaction with data; its model stores that data and notifies the view when it changes.
2. `JList` does not provide scrolling by itself. A `JScrollPane` also supplies scroll bars when the list is larger than its viewport.
3. A `ListCellRenderer` controls how each list item is painted, allowing multiple fields, colors, or badges instead of only `toString()`.
4. `DefaultTableModel` owns the rows and fires table-model events so the `JTable` can repaint and update sorting.
5. Sorting changes the visible row order. Convert a selected view row to a model row before reading or deleting model data.
6. `JSplitPane` places two components side by side or one above the other with a draggable divider.
7. A tree selection listener reads the selected node and updates the overview and details tabs.
8. `JTabbedPane` displays one panel at a time and lets the user switch between named views.
9. `JFileChooser` lets the user select a file. The editor reads or writes its UTF-8 text and reports I/O errors in a dialog.
10. An accelerator invokes a menu action from the keyboard without opening the menu first.
11. Lazy loading avoids enumerating every nested directory up front and keeps the initial tree responsive.
12. The capstone lists folders and file metadata, changes directories, and shows file details. It does not create, edit, or delete files.

## Try It

- Scroll the contact list and observe the custom name, email, and status rendering.
- Edit cells in the student table, sort columns, and add/delete rows.
- Select different tree nodes and switch between the viewer tabs.
- Open and save a text file in `MiniEditor`; try Ctrl+O and Ctrl+S.
- Navigate folders in the capstone, sort table columns, and inspect a file without modifying it.

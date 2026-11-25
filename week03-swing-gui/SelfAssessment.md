# Week 3 Self-Assessment: Swing GUIs & Layouts

## Questions

1. What is the role of a `JFrame` in a Swing application?
2. Why should components be arranged with layout managers instead of fixed pixel coordinates?
3. How do `FlowLayout`, `BorderLayout`, and `GridLayout` arrange components?
4. What is the Event Dispatch Thread (EDT), and where should Swing windows be created?
5. Why does calling `Thread.sleep(...)` in a button listener make a window unresponsive?
6. How can a background task safely update a Swing component?
7. Why are radio buttons that represent one choice usually placed in a `ButtonGroup`?
8. What does `JScrollPane` provide for a text area or other component?
9. Why does the Student Portal activity use nested panels?

## Suggested Answers

1. A `JFrame` is a top-level window that contains panels and other Swing components.
2. Layout managers adapt component placement and size to the available space and window resizing.
3. `FlowLayout` places components in a row and wraps them, `BorderLayout` provides five regions, and `GridLayout` gives each cell equal size.
4. The EDT is Swing's event-handling thread. Create and update Swing UI on it, commonly by starting application code with `SwingUtilities.invokeLater(...)`.
5. Event handlers run on the EDT. Sleeping there prevents event processing and repainting until the handler returns.
6. Run slow work on a worker thread, then use `SwingUtilities.invokeLater(...)` to update Swing components on the EDT.
7. A `ButtonGroup` ensures that only one radio button in the group can be selected at a time.
8. It adds scrolling when the component's content is larger than its visible area.
9. Nested panels let each part of the window use a suitable layout manager, making the interface easier to organize and resize.

## Try It

- Resize the FlowLayout and BorderLayout demos and observe how their components move.
- Resize the GridLayout demo and compare the button sizes.
- Click the blocking button in `FreezeDemo`, then repeat with `FixedDemo`.
- Add another course card to the Student Portal without using `setBounds(...)`.

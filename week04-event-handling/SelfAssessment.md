# Week 4 Self-Assessment: Event Handling in Swing

## Questions

1. When should you use `DocumentListener` instead of `ActionListener` on a text field?
2. What is the difference between `ActionListener` and `ItemListener`?
3. What are three ways to write an `ActionListener`?
4. How can one listener identify which of several buttons fired an event?
5. Why does `DocumentListener` provide separate insert, remove, and change methods?
6. Why should related radio buttons be placed in a `ButtonGroup`?
7. How do keyboard shortcuts work in the Counter App?
8. What is the difference between a mouse click and a mouse drag event?
9. Why does `KeyboardMover` request focus for its drawing panel?
10. Why is `KeyAdapter` useful when handling keyboard input?
11. What fields does the Registration Form validate, and what feedback does it show?
12. Why should a Swing event handler avoid doing long-running work on the EDT?

## Suggested Answers

1. Use `DocumentListener` to react to every text change, such as updating a live character count or search result. An `ActionListener` on a text field normally fires when Enter is pressed.
2. An `ActionListener` handles an action, such as a button click. An `ItemListener` handles an item's selected or deselected state.
3. A listener can be written as a named or inner class, an anonymous class, or a lambda expression.
4. Check `event.getSource()` or compare `event.getActionCommand()` with commands assigned to the buttons.
5. A document can change through insertion, removal, or attribute changes, so the interface has callbacks for each change type.
6. A `ButtonGroup` makes radio buttons mutually exclusive, so only one choice in the group can be selected.
7. The app maps Ctrl+Up to increment, Ctrl+Down to decrement, and Ctrl+R to reset using Swing input and action maps.
8. A click is a press and release at a position. A drag reports pointer movement while a mouse button remains held.
9. A `KeyListener` only receives key events while its component has keyboard focus.
10. `KeyAdapter` supplies empty listener methods, so a class can override only the keyboard events it needs.
11. It requires a name, checks the email format, and requires a password of at least eight characters. It displays either the validation errors or a success-style demo message.
12. Swing event handlers run on the Event Dispatch Thread (EDT). Long-running work there prevents the interface from repainting and responding.

## Try It

- Click the three buttons in `ListenerDemo` and compare the listener implementations.
- Type and delete text in `DocumentListenerDemo` and `LiveSearchDemo`.
- Use both buttons and keyboard shortcuts in `CounterApp`.
- Submit the Registration Form with missing and valid fields.
- Draw several strokes, clear the canvas, then move the square with both arrow keys and WASD.

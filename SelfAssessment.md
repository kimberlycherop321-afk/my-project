# Week 1 Self-Assessment

## Questions

1. What are the four parts of an event-driven system?
2. How does procedural execution differ from event-driven execution?
3. What does the event queue hold?
4. Why should long tasks not run on the Swing EDT?
5. How do you update a Swing label from a background thread?
6. What does an `ActionListener` receive when a button is clicked?
7. Why is clearing a collection important after removing its UI components?

## Answers

1. Event, event source, event listener, and event handler.
2. Procedural code controls the sequence of steps; event-driven code waits for and responds to events.
3. The event queue holds events in the order they are waiting to be dispatched.
4. It can block the EDT, freezing window drawing and user interaction.
5. Wrap the label update in `SwingUtilities.invokeLater`.
6. An `ActionEvent`, although a lambda does not have to use it.
7. Components kept in the collection remain strongly referenced and can cause unnecessary memory retention.

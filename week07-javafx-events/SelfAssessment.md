# Week 7 Self-Assessment: Event Handling in JavaFX

## Questions

1. What are the capturing and bubbling phases of JavaFX event delivery?
2. Which phase runs first: event filters or event handlers?
3. What does `event.consume()` do?
4. Why does the propagation demo listen for a mouse-press event?
5. How does `addEventFilter` differ from `addEventHandler`?
6. What information can a JavaFX `MouseEvent` provide?
7. Why is a scene-level key filter useful in `MouseKeyDemo`?
8. What is a `Dragboard` used for?
9. Why must a drop target call `acceptTransferModes(...)` during `DRAG_OVER`?
10. What does `event.setDropCompleted(success)` communicate?
11. What is a custom JavaFX event type used for?
12. How does `CustomEventDemo` deliver a `LoginSuccessEvent` to its listener?

## Suggested Answers

1. Capturing travels from the outer event target toward the source node. Bubbling travels back from the source node to its ancestors.
2. Event filters run first during capturing; event handlers run later during bubbling if the event was not consumed.
3. It marks the event as handled and prevents it from continuing farther through the dispatch chain.
4. Consuming the press at the outer container prevents the nested button from receiving that press and responding to it. The demo can show which filters and handlers run.
5. `addEventFilter` observes or handles an event on its way down. `addEventHandler` observes or handles it on its way back up.
6. A mouse event can provide the button, click count, local and scene coordinates, and modifier-key states.
7. The scene filter can observe key presses from controls in the scene without requiring each control to register its own listener.
8. A `Dragboard` carries transferred data, such as text, during a drag-and-drop gesture.
9. The target must accept a supported transfer mode to tell JavaFX the drop is valid; otherwise the drop handler will not receive a successful drop.
10. It reports whether the target accepted and completed the drop to the drag source.
11. A custom event type represents an application-specific event and can carry relevant data to interested handlers.
12. The button fires the custom event on the root node with `root.fireEvent(...)`, and the root's handler receives it.

## Try It

- Click the nested button with and without the consume checkbox selected; compare the event log.
- Click the mouse-and-key event area and test Ctrl, Shift, Alt, and Meta modifiers.
- Drag the source label into the target box.
- Enter a username and fire the custom login-success event.

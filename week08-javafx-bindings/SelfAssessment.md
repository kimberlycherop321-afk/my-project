# Week 8 Self-Assessment: Properties and Bindings in JavaFX

## Questions

1. What is the difference between a Java field and a JavaFX property?
2. What values does a `ChangeListener` receive?
3. What does an `InvalidationListener` tell you?
4. What happens if you call `setText(...)` on a property while it has a unidirectional binding?
5. How does a bidirectional binding differ from a unidirectional binding?
6. In `UnidirectionalDemo`, which property is the source of the label and progress-bar values?
7. Why does `BidirectionalDemo` bind both text fields to the same model property?
8. How does the reactive form keep its greeting and password-strength label current?
9. Why is the Register button disabled when the input is invalid?
10. Why do bindings handle pasted and programmatically changed text without extra key handlers?

## Suggested Answers

1. A JavaFX property exposes a value and notifies listeners and bindings when that value changes. A plain field does not provide that observable behavior by itself.
2. The observable property, its old value, and its new value.
3. The observable's value is invalid and may need to be recomputed or read. It does not provide old and new values.
4. JavaFX throws a runtime exception because a bound property cannot be set directly while the binding is active.
5. A unidirectional binding sends updates from one source to one target. A bidirectional binding synchronizes changes in either direction.
6. The slider's `valueProperty()` is the source; the label and progress bar observe values derived from it.
7. Both fields share the same model value, so edits in either field update the model and the other field.
8. String bindings depend on the name and password text properties and recompute when those dependencies change.
9. The button's `disableProperty` is bound to the inverse of the form's validity condition, so it updates automatically.
10. Bindings observe property changes rather than particular keyboard events, so they also respond to paste, clear, and programmatic updates.

## Try It

- Change the name and age in the listener demo; compare the change and invalidation log entries.
- Move the slider in the unidirectional demo and observe both outputs.
- Edit each bidirectional text field in turn.
- Try invalid and valid names, email addresses, and passwords in the reactive form.

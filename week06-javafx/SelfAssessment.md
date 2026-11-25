# Week 6 Self-Assessment: Introduction to Modern JavaFX

## Questions

1. What is the difference between a `Stage` and a `Scene`?
2. What is a JavaFX `Node`?
3. What does an `Application` subclass do?
4. When is a `VBox` more appropriate than an `HBox`?
5. Which layout pane is useful for forms arranged in rows and columns?
6. What regions does a `BorderPane` provide?
7. What is the difference between building a view in Java and describing it in FXML?
8. What does `@FXML` do?
9. Why does `FXMLApp` use `FXMLLoader`?
10. What is the purpose of the `onAction` attribute in `login.fxml`?
11. Why should a controller validate form fields before showing success feedback?
12. How does the dashboard's inline CSS affect its controls?

## Suggested Answers

1. A `Stage` is the top-level window. A `Scene` contains the root node and the interface shown inside that window.
2. A `Node` is an item in the scene graph, such as a control, shape, or layout pane.
3. It provides the JavaFX application lifecycle. JavaFX calls `start(Stage)` when the toolkit is ready.
4. Use a `VBox` to stack items vertically; use an `HBox` to place them side by side.
5. `GridPane` arranges controls in rows and columns and is useful for forms.
6. `BorderPane` provides top, bottom, left, right, and center regions.
7. Programmatic views create controls in Java code. FXML describes the view declaratively in XML and can be edited in Scene Builder.
8. `@FXML` allows `FXMLLoader` to inject matching fields and call controller methods, including private members.
9. `FXMLLoader` reads the FXML resource, creates the scene-graph nodes, and connects them to the declared controller.
10. `onAction` names the controller method that runs when the button is activated.
11. Validation prevents incomplete or obviously invalid input from being treated as acceptable. The login activity is a local demo and does not authenticate users.
12. Inline CSS sets visual properties such as background, spacing, font size, color, and shadows without a separate stylesheet.

## Try It

- Click the button in `Main` and observe the label update.
- Switch between all four tabs in `LayoutDemo` and resize the window.
- Open `login.fxml` in Scene Builder, change a label, and run `FXMLApp`.
- Submit the login form with blank and complete fields.
- Select different dashboard navigation buttons and compare the inline-styled cards.

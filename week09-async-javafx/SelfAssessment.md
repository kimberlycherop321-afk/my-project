# Week 9 Self-Assessment: Asynchronous Programming in JavaFX

## Questions

1. What work is performed by the JavaFX Application Thread?
2. Why does `FreezeDemo` make the window appear unresponsive?
3. Why is `Task.call()` a suitable place for slow work?
4. Can a completed `Task` be run again?
5. How do `updateProgress()` and `updateMessage()` communicate with the UI?
6. Why can a progress bar bind to a `Task` property while `call()` runs in the background?
7. What is the difference between `Task` and `Service`?
8. How does cancelling a task affect a blocking operation such as `Thread.sleep()`?
9. When would you use `Platform.runLater(...)`?
10. Why should a background task not update JavaFX controls directly?

## Suggested Answers

1. It processes user input, event handlers, layout, and rendering for the JavaFX scene.
2. The button handler calls `Thread.sleep()` on the JavaFX Application Thread, preventing that thread from processing input and redraws until the sleep ends.
3. `Task.call()` runs on a worker thread when the task is submitted to one, keeping slow work off the UI thread.
4. No. A `Task` is single-use; create a new task or use a `Service` to create tasks for repeated work.
5. They publish observable values that JavaFX safely makes available to the task's properties on the JavaFX Application Thread.
6. The task's update methods marshal state changes to the JavaFX Application Thread, where the bound UI properties are observed.
7. A `Task` represents one execution. A `Service` is a reusable task factory with lifecycle operations such as `start()`, `cancel()`, and `restart()`.
8. Cancelling a task may interrupt the worker thread. Blocking work should propagate or handle `InterruptedException` and stop promptly.
9. Use it when code running on a plain background thread needs to schedule a UI update on the JavaFX Application Thread.
10. JavaFX scene graph controls are not thread-safe; modifying them from another thread can cause race conditions and runtime errors.

## Try It

- Run the freeze demo and try to interact with the window during its five-second delay.
- Compare it with the Task demo and verify the window remains responsive.
- Watch the progress bar and status message in the progress demo; try cancelling.
- Start, cancel, and restart the service demo.

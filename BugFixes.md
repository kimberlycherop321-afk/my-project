# Swing Bug Fixes

## 1. Blocking the EDT
Calling `Thread.sleep(5000)` in an action listener blocks the EDT, preventing the window from redrawing or processing other events. Move slow work to a background thread and send only UI updates back with `SwingUtilities.invokeLater`.

## 2. Missing Listener
A button does not perform any work until an `ActionListener` is attached. A lambda such as `button.addActionListener(event -> ...)` can update state and the UI.

## 3. Updating UI from the Wrong Thread
Swing components should be accessed from the EDT. Background threads may calculate or fetch data, but UI updates must be queued with `SwingUtilities.invokeLater`.

## 4. Memory Leak
Removing components from a `JPanel` is not enough if another collection keeps references to them. Clear the collection whenever the removed components are no longer needed.

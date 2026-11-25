import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

public class MiniEditor {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MiniEditor::createAndShow);
    }

    private static void createAndShow() {
        JFrame frame = new JFrame("Mini Editor - Untitled");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextArea editor = new JTextArea();
        editor.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
        editor.setLineWrap(true);
        editor.setWrapStyleWord(true);
        JLabel status = new JLabel("Ready.");
        File[] currentFile = {null};

        Runnable openAction = () -> openFile(frame, editor, status, currentFile);
        Runnable saveAction = () -> saveFile(frame, editor, status, currentFile, false);
        Runnable saveAsAction = () -> saveFile(frame, editor, status, currentFile, true);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem openItem = new JMenuItem("Open...");
        openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        openItem.addActionListener(event -> openAction.run());
        JMenuItem saveItem = new JMenuItem("Save");
        saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        saveItem.addActionListener(event -> saveAction.run());
        JMenuItem saveAsItem = new JMenuItem("Save As...");
        saveAsItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_S,
                InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK));
        saveAsItem.addActionListener(event -> saveAsAction.run());
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(event -> frame.dispose());
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(saveAsItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        JMenu editMenu = new JMenu("Edit");
        addEditorAction(editMenu, "Cut", editor, "cut", KeyEvent.VK_X);
        addEditorAction(editMenu, "Copy", editor, "copy", KeyEvent.VK_C);
        addEditorAction(editMenu, "Paste", editor, "paste", KeyEvent.VK_V);
        editMenu.addSeparator();
        JMenuItem selectAllItem = new JMenuItem("Select All");
        selectAllItem.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
        selectAllItem.addActionListener(event -> editor.selectAll());
        editMenu.add(selectAllItem);

        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(event -> JOptionPane.showMessageDialog(
                frame,
                "A small Swing text editor demonstrating menus, a toolbar, and file dialogs.",
                "About Mini Editor",
                JOptionPane.INFORMATION_MESSAGE));
        helpMenu.add(aboutItem);
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);

        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        JButton openButton = new JButton("Open");
        openButton.addActionListener(event -> openAction.run());
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(event -> saveAction.run());
        toolBar.add(openButton);
        toolBar.add(saveButton);

        JPanel content = new JPanel(new BorderLayout());
        content.add(toolBar, BorderLayout.NORTH);
        content.add(new JScrollPane(editor), BorderLayout.CENTER);
        content.add(status, BorderLayout.SOUTH);
        content.setBorder(BorderFactory.createEmptyBorder(4, 6, 6, 6));

        frame.setJMenuBar(menuBar);
        frame.add(content);
        frame.setSize(760, 520);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void addEditorAction(
            JMenu menu,
            String label,
            JTextArea editor,
            String actionName,
            int keyCode) {
        JMenuItem item = new JMenuItem(label);
        item.setAccelerator(KeyStroke.getKeyStroke(keyCode, InputEvent.CTRL_DOWN_MASK));
        item.addActionListener(event -> {
            switch (actionName) {
                case "cut":
                    editor.cut();
                    break;
                case "copy":
                    editor.copy();
                    break;
                case "paste":
                    editor.paste();
                    break;
                default:
                    throw new IllegalArgumentException("Unknown editor action: " + actionName);
            }
        });
        menu.add(item);
    }

    private static void openFile(
            JFrame frame,
            JTextArea editor,
            JLabel status,
            File[] currentFile) {
        File startDirectory = currentFile[0] == null ? null : currentFile[0].getParentFile();
        JFileChooser chooser = new JFileChooser(startDirectory);
        if (chooser.showOpenDialog(frame) != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File selectedFile = chooser.getSelectedFile();
        try {
            editor.setText(Files.readString(selectedFile.toPath(), StandardCharsets.UTF_8));
            currentFile[0] = selectedFile;
            frame.setTitle("Mini Editor - " + selectedFile.getName());
            status.setText("Opened " + selectedFile.getAbsolutePath());
            editor.setCaretPosition(0);
        } catch (IOException exception) {
            showFileError(frame, "Could not open file", exception);
        }
    }

    private static void saveFile(
            JFrame frame,
            JTextArea editor,
            JLabel status,
            File[] currentFile,
            boolean chooseDestination) {
        File destination = currentFile[0];
        if (destination == null || chooseDestination) {
            File startDirectory = destination == null ? null : destination.getParentFile();
            JFileChooser chooser = new JFileChooser(startDirectory);
            if (chooser.showSaveDialog(frame) != JFileChooser.APPROVE_OPTION) {
                return;
            }
            destination = chooser.getSelectedFile();
        }

        try {
            Files.writeString(destination.toPath(), editor.getText(), StandardCharsets.UTF_8);
            currentFile[0] = destination;
            frame.setTitle("Mini Editor - " + destination.getName());
            status.setText("Saved " + destination.getAbsolutePath());
        } catch (IOException exception) {
            showFileError(frame, "Could not save file", exception);
        }
    }

    private static void showFileError(JFrame frame, String title, IOException exception) {
        JOptionPane.showMessageDialog(
                frame,
                exception.getMessage(),
                title,
                JOptionPane.ERROR_MESSAGE);
    }
}

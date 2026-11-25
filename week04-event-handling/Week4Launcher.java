import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

public class Week4Launcher {
    private static final String[] ACTIVITIES = {
        "First Interactive Button",
        "Listener Styles",
        "Event Sources",
        "ItemListener",
        "DocumentListener Character Counter",
        "Live Search",
        "Counter App with Shortcuts",
        "Interactive Registration Form",
        "Mouse Drawing App",
        "Keyboard Mover"
    };

    private static final String[] FOLDERS = {
        "activity1-actionlistener-basics",
        "activity2-listener-techniques",
        "activity2-listener-techniques",
        "activity3-item-and-document-listeners",
        "activity3-item-and-document-listeners",
        "activity3-item-and-document-listeners",
        "activity4-counter-app",
        "activity5-interactive-form",
        "activity6-mouse-and-key",
        "activity6-mouse-and-key"
    };

    private static final String[] MAIN_CLASSES = {
        "FirstButton",
        "ListenerDemo",
        "EventSourceDemo",
        "ItemListenerDemo",
        "DocumentListenerDemo",
        "LiveSearchDemo",
        "CounterApp",
        "RegistrationForm",
        "DrawingApp",
        "KeyboardMover"
    };

    public static void main(String[] args) {
        if (GraphicsEnvironment.isHeadless()) {
            System.err.println("Week4Launcher requires a graphical desktop environment.");
            return;
        }
        SwingUtilities.invokeLater(Week4Launcher::createAndShow);
    }

    private static void createAndShow() {
        JFrame frame = new JFrame("Week 4: Event Handling in Swing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JComboBox<String> activitySelector = new JComboBox<>(ACTIVITIES);
        JLabel status = new JLabel("Choose an activity and select Launch.");
        JButton launchButton = new JButton("Launch");

        launchButton.addActionListener(event -> {
            int selectedIndex = activitySelector.getSelectedIndex();
            launchButton.setEnabled(false);
            status.setText("Compiling " + ACTIVITIES[selectedIndex] + "...");

            SwingWorker<String, Void> worker = new SwingWorker<String, Void>() {
                @Override
                protected String doInBackground() throws Exception {
                    return compileAndLaunch(selectedIndex);
                }

                @Override
                protected void done() {
                    launchButton.setEnabled(true);
                    try {
                        status.setText(get());
                    } catch (InterruptedException exception) {
                        Thread.currentThread().interrupt();
                        showError(frame, "Launch interrupted", exception.getMessage());
                        status.setText("Launch interrupted.");
                    } catch (ExecutionException exception) {
                        Throwable cause = exception.getCause();
                        String message = cause == null ? exception.getMessage() : cause.getMessage();
                        showError(frame, "Activity failed", message);
                        status.setText("Activity failed. See the error dialog for details.");
                    }
                }
            };
            worker.execute();
        });

        JPanel selectorPanel = new JPanel(new BorderLayout(8, 8));
        selectorPanel.setBorder(BorderFactory.createEmptyBorder(18, 18, 10, 18));
        selectorPanel.add(new JLabel("Activity:"), BorderLayout.NORTH);
        selectorPanel.add(activitySelector, BorderLayout.CENTER);
        selectorPanel.add(launchButton, BorderLayout.EAST);

        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        statusPanel.setBorder(BorderFactory.createEmptyBorder(0, 18, 14, 18));
        statusPanel.add(status);

        frame.add(selectorPanel, BorderLayout.CENTER);
        frame.add(statusPanel, BorderLayout.SOUTH);
        frame.setSize(580, 190);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static String compileAndLaunch(int selectedIndex)
            throws IOException, InterruptedException {
        File activityRoot = findActivityRoot();
        File activityFolder = new File(activityRoot, FOLDERS[selectedIndex]);
        File[] sourceFiles = activityFolder.listFiles(
                (directory, name) -> name.endsWith(".java"));
        if (sourceFiles == null || sourceFiles.length == 0) {
            throw new IOException("No Java source files found in " + activityFolder);
        }
        Arrays.sort(sourceFiles, (first, second) -> first.getName().compareTo(second.getName()));

        List<String> compileCommand = new ArrayList<>();
        compileCommand.add(javaTool("javac"));
        for (File sourceFile : sourceFiles) {
            compileCommand.add(sourceFile.getName());
        }

        Process compileProcess = new ProcessBuilder(compileCommand)
                .redirectErrorStream(true)
                .directory(activityFolder)
                .start();
        String compileOutput;
        int compileExitCode;
        try {
            compileOutput = readOutput(compileProcess);
            compileExitCode = compileProcess.waitFor();
        } catch (InterruptedException exception) {
            compileProcess.destroyForcibly();
            throw exception;
        }
        if (compileExitCode != 0) {
            throw new IOException("Compilation failed for " + FOLDERS[selectedIndex]
                    + " (exit code " + compileExitCode + "):\n" + compileOutput);
        }

        new ProcessBuilder(
                javaTool("java"),
                "-cp",
                activityFolder.getAbsolutePath(),
                MAIN_CLASSES[selectedIndex])
                .directory(activityRoot)
                .inheritIO()
                .start();
        return "Launched " + MAIN_CLASSES[selectedIndex] + ". Close its window when finished.";
    }

    private static String javaTool(String toolName) {
        String suffix = System.getProperty("os.name").startsWith("Windows") ? ".exe" : "";
        String executableName = toolName + suffix;
        File executable = new File(
                new File(System.getProperty("java.home"), "bin"),
                executableName);
        return executable.isFile() ? executable.getAbsolutePath() : executableName;
    }

    private static File findActivityRoot() {
        File currentDirectory = new File(System.getProperty("user.dir")).getAbsoluteFile();
        File childActivityRoot = new File(currentDirectory, "week04-event-handling");
        return childActivityRoot.isDirectory() ? childActivityRoot : currentDirectory;
    }

    private static String readOutput(Process process) throws IOException {
        StringBuilder output = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append(System.lineSeparator());
            }
        }
        return output.toString();
    }

    private static void showError(JFrame parent, String title, String message) {
        JTextArea textArea = new JTextArea(message == null ? "Unknown error." : message, 12, 64);
        textArea.setEditable(false);
        JOptionPane.showMessageDialog(
                parent,
                new JScrollPane(textArea),
                title,
                JOptionPane.ERROR_MESSAGE);
    }
}

import java.awt.GraphicsEnvironment;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Week2Launcher {
    private static final String[] ACTIVITIES = {
        "Weather Station",
        "Legacy Observer",
        "Custom Observer Refactor",
        "Notification Comparison"
    };

    private static final String[] FOLDERS = {
        "activity1-weather-station",
        "activity2-legacy-observer",
        "activity3-custom-observer",
        "activity4-comparison"
    };

    private static final String[] MAIN_CLASSES = {
        "WeatherStationMain",
        "LegacyObserverMain",
        "RefactoredMain",
        "ComparisonMain"
    };

    public static void main(String[] args) {
        if (GraphicsEnvironment.isHeadless()) {
            System.err.println("Week2Launcher requires a graphical desktop environment.");
            return;
        }

        Object selection = JOptionPane.showInputDialog(
                null,
                "Choose a Week 2 activity:",
                "Observer Pattern Activities",
                JOptionPane.QUESTION_MESSAGE,
                null,
                ACTIVITIES,
                ACTIVITIES[0]);
        if (selection == null) return;

        int selectedIndex = -1;
        for (int i = 0; i < ACTIVITIES.length; i++) {
            if (ACTIVITIES[i].equals(selection)) {
                selectedIndex = i;
                break;
            }
        }
        if (selectedIndex < 0) return;

        try {
            String output = run(FOLDERS[selectedIndex], MAIN_CLASSES[selectedIndex]);
            showOutput(ACTIVITIES[selectedIndex], output, JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException exception) {
            showOutput("Activity failed", exception.getMessage(), JOptionPane.ERROR_MESSAGE);
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            showOutput("Activity interrupted", exception.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }

    private static String run(String folderName, String mainClass)
            throws IOException, InterruptedException {
        File base = new File(System.getProperty("user.dir"));
        File activityRoot = new File(base, "Week02-observer-pattern");
        if (!activityRoot.isDirectory()) {
            activityRoot = base;
        }

        File activityFolder = new File(activityRoot, folderName);
        File[] sourceFiles = activityFolder.listFiles(
                (directory, name) -> name.endsWith(".java"));
        if (sourceFiles == null || sourceFiles.length == 0) {
            throw new IOException("No Java source files found in " + activityFolder);
        }

        List<String> compileCommand = new ArrayList<>();
        compileCommand.add("javac");
        for (File sourceFile : sourceFiles) {
            compileCommand.add(sourceFile.getName());
        }
        Process compileProcess = new ProcessBuilder(compileCommand)
                .redirectErrorStream(true)
                .directory(activityFolder)
                .start();
        String output = readOutput(compileProcess);
        int compileExitCode = compileProcess.waitFor();
        if (compileExitCode != 0) {
            throw new IOException("Compilation failed for " + folderName
                    + " (exit code " + compileExitCode + "):\n" + output);
        }

        Process activityProcess = new ProcessBuilder("java", "-cp", folderName, mainClass)
                .redirectErrorStream(true)
                .directory(activityRoot)
                .start();
        output += readOutput(activityProcess);
        int runExitCode = activityProcess.waitFor();
        if (runExitCode != 0) {
            throw new IOException(mainClass + " failed (exit code " + runExitCode + "):\n"
                    + output);
        }
        return output;
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

    private static void showOutput(String title, String output, int messageType) {
        JTextArea textArea = new JTextArea(output, 18, 72);
        textArea.setEditable(false);
        JOptionPane.showMessageDialog(
                null,
                new JScrollPane(textArea),
                title,
                messageType);
    }
}

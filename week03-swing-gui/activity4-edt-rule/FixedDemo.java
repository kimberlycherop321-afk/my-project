import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class FixedDemo {
    private static final int WORK_DURATION_MILLIS = 4000;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FixedDemo::createAndShow);
    }

    private static void createAndShow() {
        JFrame frame = new JFrame("Responsive EDT Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel instructions = new JLabel(
                "<html>Slow work runs on a background thread.<br>"
                        + "The window remains responsive while it runs.</html>",
                SwingConstants.CENTER);
        JLabel status = new JLabel("Ready.", SwingConstants.CENTER);
        JButton workButton = new JButton("Start background work");
        workButton.addActionListener(event -> {
            workButton.setEnabled(false);
            status.setText("Working in the background...");

            Thread worker = new Thread(() -> {
                String result;
                try {
                    Thread.sleep(WORK_DURATION_MILLIS);
                    result = "Background work finished.";
                } catch (InterruptedException exception) {
                    Thread.currentThread().interrupt();
                    result = "Background work was interrupted.";
                }

                String completedMessage = result;
                SwingUtilities.invokeLater(() -> {
                    status.setText(completedMessage);
                    workButton.setEnabled(true);
                });
            }, "week3-background-work");
            worker.setDaemon(true);
            worker.start();
        });

        JPanel controls = new JPanel(new FlowLayout());
        controls.add(workButton);
        controls.add(status);

        frame.add(instructions, BorderLayout.CENTER);
        frame.add(controls, BorderLayout.SOUTH);
        frame.setSize(520, 220);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class Bug1_BlockingEDT {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Fix 1: Do Not Block the EDT");
            JLabel status = new JLabel("Ready");
            JButton start = new JButton("Run slow task");

            // The slow work runs on a background thread. The EDT remains free.
            start.addActionListener(event -> {
                status.setText("Working...");
                new Thread(() -> {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException interrupted) {
                        Thread.currentThread().interrupt();
                    }
                    SwingUtilities.invokeLater(() -> status.setText("Finished"));
                }).start();
            });

            frame.setLayout(new java.awt.FlowLayout());
            frame.add(status);
            frame.add(start);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(320, 120);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}

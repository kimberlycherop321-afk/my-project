import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class Bug3_WrongThread {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Fix 3: Update Swing on the EDT");
            JLabel timeLabel = new JLabel("Time:");
            frame.add(timeLabel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(260, 100);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            new Thread(() -> {
                while (frame.isDisplayable()) {
                    String time = LocalTime.now()
                            .format(DateTimeFormatter.ofPattern("HH:mm:ss"));
                    // Swing components must be updated through the EDT.
                    SwingUtilities.invokeLater(() -> timeLabel.setText("Time: " + time));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException interrupted) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            }).start();
        });
    }
}

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class FreezeDemo {
    private static final int WORK_DURATION_MILLIS = 4000;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FreezeDemo::createAndShow);
    }

    private static void createAndShow() {
        JFrame frame = new JFrame("EDT Freeze Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel instructions = new JLabel(
                "<html>Click the button, then try moving or resizing this window.<br>"
                        + "It will stop responding while the event thread is blocked.</html>",
                SwingConstants.CENTER);
        JLabel status = new JLabel("Ready to demonstrate the freeze.", SwingConstants.CENTER);
        JButton freezeButton = new JButton("Block the EDT for 4 seconds");
        freezeButton.addActionListener(event -> {
            status.setText("The EDT is blocked; this label cannot repaint yet.");
            try {
                Thread.sleep(WORK_DURATION_MILLIS);
                status.setText("The EDT is free again.");
            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
                status.setText("The demonstration was interrupted.");
            }
        });

        JPanel controls = new JPanel(new FlowLayout());
        controls.add(freezeButton);
        controls.add(status);

        frame.add(instructions, BorderLayout.CENTER);
        frame.add(controls, BorderLayout.SOUTH);
        frame.setSize(520, 220);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class Bug2_MissingListener {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Fix 2: Add an ActionListener");
            JLabel countLabel = new JLabel("Count: 0");
            JButton incrementButton = new JButton("Increment");
            final int[] count = {0};

            incrementButton.addActionListener(event -> {
                count[0]++;
                countLabel.setText("Count: " + count[0]);
            });

            frame.setLayout(new java.awt.FlowLayout());
            frame.add(countLabel);
            frame.add(incrementButton);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(260, 120);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}

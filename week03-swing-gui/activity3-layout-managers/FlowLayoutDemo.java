import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class FlowLayoutDemo {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(FlowLayoutDemo::createAndShow);
    }

    private static void createAndShow() {
        JFrame frame = new JFrame("FlowLayout Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel explanation = new JLabel(
                "Resize the window: components flow left-to-right and wrap.",
                SwingConstants.CENTER);
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 12));
        for (int number = 1; number <= 8; number++) {
            buttons.add(new JButton("Button " + number));
        }

        frame.add(explanation, BorderLayout.NORTH);
        frame.add(buttons, BorderLayout.CENTER);
        frame.setSize(560, 260);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

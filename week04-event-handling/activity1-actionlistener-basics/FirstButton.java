import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class FirstButton {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(FirstButton::createAndShow);
    }

    private static void createAndShow() {
        JFrame frame = new JFrame("First Interactive Button");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel message = new JLabel("The button has not been clicked yet.", SwingConstants.CENTER);
        JButton button = new JButton("Click me!");
        button.addActionListener(event -> message.setText("Button clicked!"));

        JPanel content = new JPanel(new BorderLayout(8, 8));
        content.add(message, BorderLayout.CENTER);

        JPanel controls = new JPanel(new FlowLayout());
        controls.add(button);
        content.add(controls, BorderLayout.SOUTH);

        frame.add(content);
        frame.setSize(420, 180);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

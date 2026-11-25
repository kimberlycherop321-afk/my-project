import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class FirstWindow {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(FirstWindow::createAndShow);
    }

    private static void createAndShow() {
        JFrame frame = new JFrame("My First Swing Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel greeting = new JLabel("Hello, Swing!", SwingConstants.CENTER);
        greeting.setFont(greeting.getFont().deriveFont(Font.BOLD, 24f));
        frame.add(greeting, BorderLayout.CENTER);

        frame.setSize(420, 220);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

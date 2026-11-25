import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class BorderLayoutDemo {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(BorderLayoutDemo::createAndShow);
    }

    private static void createAndShow() {
        JFrame frame = new JFrame("BorderLayout Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(8, 8));

        frame.add(region("NORTH", new Color(225, 238, 255)), BorderLayout.NORTH);
        frame.add(region("SOUTH", new Color(255, 239, 220)), BorderLayout.SOUTH);
        frame.add(region("WEST", new Color(230, 247, 232)), BorderLayout.WEST);
        frame.add(region("EAST", new Color(250, 230, 238)), BorderLayout.EAST);

        JTextArea center = new JTextArea(
                "CENTER gets the remaining space.\nResize the window to see it expand.");
        center.setEditable(false);
        center.setLineWrap(true);
        center.setWrapStyleWord(true);
        frame.add(new JScrollPane(center), BorderLayout.CENTER);

        frame.setSize(620, 340);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static JPanel region(String name, Color color) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 8));
        panel.setBackground(color);
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panel.setPreferredSize(new Dimension(100, 54));
        panel.add(new JLabel(name, SwingConstants.CENTER));
        panel.add(new JButton(name));
        return panel;
    }
}

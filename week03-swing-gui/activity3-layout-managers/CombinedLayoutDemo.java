import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class CombinedLayoutDemo {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(CombinedLayoutDemo::createAndShow);
    }

    private static void createAndShow() {
        JFrame frame = new JFrame("Combined Layout Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel page = new JPanel(new BorderLayout(10, 10));
        page.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        JLabel heading = new JLabel("A Dashboard Built with Nested Panels", SwingConstants.CENTER);
        page.add(heading, BorderLayout.NORTH);

        JPanel tools = new JPanel(new GridLayout(0, 1, 6, 6));
        tools.setBorder(BorderFactory.createTitledBorder("Tools"));
        tools.add(new JButton("Home"));
        tools.add(new JButton("Courses"));
        tools.add(new JButton("Grades"));

        JPanel cards = new JPanel(new GridLayout(2, 2, 8, 8));
        cards.setBorder(BorderFactory.createTitledBorder("Overview"));
        cards.add(new JButton("Current Courses"));
        cards.add(new JButton("Assignments"));
        cards.add(new JButton("Announcements"));
        cards.add(new JButton("Help"));

        JPanel center = new JPanel(new BorderLayout(8, 8));
        center.add(tools, BorderLayout.WEST);
        center.add(cards, BorderLayout.CENTER);
        page.add(center, BorderLayout.CENTER);

        JPanel footer = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        footer.add(new JLabel("BorderLayout + GridLayout + FlowLayout"));
        page.add(footer, BorderLayout.SOUTH);

        frame.add(page);
        frame.setSize(680, 380);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

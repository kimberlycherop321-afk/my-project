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

public class StudentPortal {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(StudentPortal::createAndShow);
    }

    private static void createAndShow() {
        JFrame frame = new JFrame("Student Portal Mockup");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel status = new JLabel("Welcome to your student dashboard.");

        JPanel header = new JPanel(new BorderLayout());
        header.setBorder(BorderFactory.createEmptyBorder(12, 16, 12, 16));
        JLabel title = new JLabel("STUDENT PORTAL");
        title.setFont(title.getFont().deriveFont(20f));
        header.add(title, BorderLayout.WEST);
        header.add(new JLabel("Signed in as Alex Student"), BorderLayout.EAST);

        JPanel navigation = new JPanel(new GridLayout(0, 1, 6, 6));
        navigation.setBorder(BorderFactory.createTitledBorder("Navigation"));
        addNavigationButton(navigation, "Dashboard", status);
        addNavigationButton(navigation, "My Courses", status);
        addNavigationButton(navigation, "Assignments", status);
        addNavigationButton(navigation, "Grades", status);
        addNavigationButton(navigation, "Help", status);

        JPanel courses = new JPanel(new GridLayout(1, 3, 10, 10));
        courses.add(courseCard("Java Programming", "CS 205", "Next: Swing GUI lab"));
        courses.add(courseCard("Data Structures", "CS 210", "Next: Trees worksheet"));
        courses.add(courseCard("Statistics", "MATH 104", "Next: Chapter 4 quiz"));

        JPanel assignments = informationPanel(
                "Upcoming Assignments",
                "Swing GUI lab - Friday",
                "Trees worksheet - Monday",
                "Chapter 4 quiz - Tuesday");
        JPanel announcements = informationPanel(
                "Announcements",
                "Advising hours are posted.",
                "Library closes at 8:00 PM.",
                "Remember to check course updates.");

        JPanel lowerSections = new JPanel(new GridLayout(1, 2, 10, 10));
        lowerSections.add(assignments);
        lowerSections.add(announcements);

        JPanel dashboard = new JPanel(new BorderLayout(10, 10));
        dashboard.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel welcome = new JLabel("Welcome back, Alex!", SwingConstants.LEFT);
        welcome.setFont(welcome.getFont().deriveFont(18f));
        dashboard.add(welcome, BorderLayout.NORTH);

        JPanel dashboardContent = new JPanel(new BorderLayout(10, 10));
        dashboardContent.add(courses, BorderLayout.NORTH);
        dashboardContent.add(lowerSections, BorderLayout.CENTER);
        dashboard.add(dashboardContent, BorderLayout.CENTER);

        JPanel mainContent = new JPanel(new BorderLayout(10, 10));
        mainContent.setBorder(BorderFactory.createEmptyBorder(0, 12, 12, 12));
        navigation.setPreferredSize(new java.awt.Dimension(170, 0));
        mainContent.add(navigation, BorderLayout.WEST);
        mainContent.add(dashboard, BorderLayout.CENTER);

        JPanel footer = new JPanel(new FlowLayout(FlowLayout.LEADING));
        footer.setBorder(BorderFactory.createEmptyBorder(0, 12, 8, 12));
        footer.add(status);

        frame.add(header, BorderLayout.NORTH);
        frame.add(mainContent, BorderLayout.CENTER);
        frame.add(footer, BorderLayout.SOUTH);
        frame.setMinimumSize(new java.awt.Dimension(760, 480));
        frame.setSize(920, 590);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void addNavigationButton(
            JPanel navigation,
            String label,
            JLabel status) {
        JButton button = new JButton(label);
        button.addActionListener(event -> status.setText(
                label + " selected (this is a static mockup)."));
        navigation.add(button);
    }

    private static JPanel courseCard(String name, String code, String nextItem) {
        JPanel card = new JPanel(new BorderLayout(4, 8));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEtchedBorder(),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        JLabel courseName = new JLabel(name);
        courseName.setFont(courseName.getFont().deriveFont(15f));
        card.add(courseName, BorderLayout.NORTH);
        card.add(new JLabel("<html>" + code + "<br>" + nextItem + "</html>"),
                BorderLayout.CENTER);
        return card;
    }

    private static JPanel informationPanel(String title, String... items) {
        JPanel panel = new JPanel(new GridLayout(0, 1, 4, 4));
        panel.setBorder(BorderFactory.createTitledBorder(title));
        for (String item : items) {
            panel.add(new JLabel("\u2022 " + item));
        }
        return panel;
    }
}

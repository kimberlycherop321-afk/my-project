import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Arrays;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class ComponentShowcase {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(ComponentShowcase::createAndShow);
    }

    private static void createAndShow() {
        JFrame frame = new JFrame("Basic Swing Components");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextField nameField = new JTextField(18);
        JPasswordField passwordField = new JPasswordField(18);
        JCheckBox updatesCheckBox = new JCheckBox("Send me course updates");

        JRadioButton morningRadio = new JRadioButton("Morning");
        JRadioButton eveningRadio = new JRadioButton("Evening");
        ButtonGroup classTimeGroup = new ButtonGroup();
        classTimeGroup.add(morningRadio);
        classTimeGroup.add(eveningRadio);

        JPanel classTimePanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 4, 0));
        classTimePanel.add(morningRadio);
        classTimePanel.add(eveningRadio);

        JComboBox<String> majorSelector = new JComboBox<>(
                new String[] {"Computer Science", "Business", "Design", "Engineering"});

        JPanel form = new JPanel(new GridLayout(0, 2, 10, 10));
        form.add(new JLabel("Student name:"));
        form.add(nameField);
        form.add(new JLabel("Password:"));
        form.add(passwordField);
        form.add(new JLabel("Preferred class time:"));
        form.add(classTimePanel);
        form.add(new JLabel("Major:"));
        form.add(majorSelector);
        form.add(new JLabel("Email preferences:"));
        form.add(updatesCheckBox);
        form.setBorder(BorderFactory.createTitledBorder("Form Components"));

        JTextArea notes = new JTextArea(5, 36);
        notes.setLineWrap(true);
        notes.setWrapStyleWord(true);
        JScrollPane notesScrollPane = new JScrollPane(notes);
        notesScrollPane.setBorder(BorderFactory.createTitledBorder("Notes (scrollable)"));

        JLabel result = new JLabel("Try the controls, then select Show Selections.");
        JButton showSelections = new JButton("Show Selections");
        showSelections.addActionListener(event -> {
            String classTime = morningRadio.isSelected()
                    ? "Morning"
                    : eveningRadio.isSelected() ? "Evening" : "Not selected";
            char[] password = passwordField.getPassword();
            int passwordLength = password.length;
            Arrays.fill(password, '\0');

            result.setText("Name: " + emptyAsLabel(nameField.getText())
                    + " | Password characters: " + passwordLength
                    + " | Class: " + classTime
                    + " | Major: " + majorSelector.getSelectedItem()
                    + " | Updates: " + (updatesCheckBox.isSelected() ? "Yes" : "No"));
        });

        JPanel actions = new JPanel(new BorderLayout(8, 8));
        actions.add(showSelections, BorderLayout.WEST);
        actions.add(result, BorderLayout.CENTER);

        JPanel content = new JPanel(new BorderLayout(12, 12));
        content.setBorder(BorderFactory.createEmptyBorder(14, 14, 14, 14));
        content.add(form, BorderLayout.NORTH);
        content.add(notesScrollPane, BorderLayout.CENTER);
        content.add(actions, BorderLayout.SOUTH);

        frame.add(new JScrollPane(content));
        frame.setSize(720, 520);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static String emptyAsLabel(String value) {
        return value.isEmpty() ? "Not entered" : value;
    }
}

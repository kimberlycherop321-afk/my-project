import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.Arrays;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class RegistrationForm {
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$");

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RegistrationForm::createAndShow);
    }

    private static void createAndShow() {
        JFrame frame = new JFrame("Interactive Registration Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextField nameField = new JTextField(22);
        JTextField emailField = new JTextField(22);
        JPasswordField passwordField = new JPasswordField(22);
        JLabel feedback = new JLabel("Enter your details, then select Register.");
        JButton submitButton = new JButton("Register");
        submitButton.addActionListener(event -> {
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            char[] password = passwordField.getPassword();
            int passwordLength = password.length;
            Arrays.fill(password, '\0');

            StringBuilder errors = new StringBuilder();
            if (name.isEmpty()) {
                errors.append("Enter your name. ");
            }
            if (!EMAIL_PATTERN.matcher(email).matches()) {
                errors.append("Enter a valid email address. ");
            }
            if (passwordLength < 8) {
                errors.append("Password must contain at least 8 characters.");
            }

            if (errors.length() == 0) {
                feedback.setForeground(new Color(0, 110, 0));
                feedback.setText("Details look good. This demo does not submit data.");
            } else {
                feedback.setForeground(new Color(170, 0, 0));
                feedback.setText(errors.toString().trim());
            }
        });

        JPanel fields = new JPanel(new GridLayout(0, 2, 10, 10));
        fields.add(new JLabel("Full name:"));
        fields.add(nameField);
        fields.add(new JLabel("Email:"));
        fields.add(emailField);
        fields.add(new JLabel("Password (8+ characters):"));
        fields.add(passwordField);
        fields.setBorder(BorderFactory.createEmptyBorder(16, 16, 8, 16));

        JPanel actions = new JPanel(new BorderLayout(8, 8));
        actions.add(submitButton, BorderLayout.WEST);
        actions.add(feedback, BorderLayout.CENTER);
        actions.setBorder(BorderFactory.createEmptyBorder(8, 16, 16, 16));

        frame.add(fields, BorderLayout.CENTER);
        frame.add(actions, BorderLayout.SOUTH);
        frame.getRootPane().setDefaultButton(submitButton);
        frame.setSize(600, 260);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

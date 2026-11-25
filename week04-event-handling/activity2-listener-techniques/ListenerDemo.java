import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class ListenerDemo {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(ListenerDemo::createAndShow);
    }

    private static void createAndShow() {
        JFrame frame = new JFrame("Three ActionListener Styles");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel status = new JLabel("Select a button to compare listener styles.");
        JButton innerClassButton = new JButton("Inner class");
        JButton anonymousButton = new JButton("Anonymous class");
        JButton lambdaButton = new JButton("Lambda");

        class InnerClassListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent event) {
                status.setText("Handled by an inner class.");
            }
        }

        innerClassButton.addActionListener(new InnerClassListener());
        anonymousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                status.setText("Handled by an anonymous class.");
            }
        });
        lambdaButton.addActionListener(event -> status.setText("Handled by a lambda."));

        JPanel buttons = new JPanel(new FlowLayout());
        buttons.add(innerClassButton);
        buttons.add(anonymousButton);
        buttons.add(lambdaButton);

        JPanel content = new JPanel(new java.awt.BorderLayout(8, 8));
        content.add(buttons, java.awt.BorderLayout.CENTER);
        content.add(status, java.awt.BorderLayout.SOUTH);
        content.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 12, 12, 12));

        frame.add(content);
        frame.setSize(520, 160);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

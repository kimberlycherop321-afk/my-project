import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class DocumentListenerDemo {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(DocumentListenerDemo::createAndShow);
    }

    private static void createAndShow() {
        JFrame frame = new JFrame("Live Character Counter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextField input = new JTextField(24);
        JLabel count = new JLabel("Characters: 0");
        Runnable updateCount = () -> count.setText("Characters: " + input.getDocument().getLength());
        input.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent event) {
                updateCount.run();
            }

            @Override
            public void removeUpdate(DocumentEvent event) {
                updateCount.run();
            }

            @Override
            public void changedUpdate(DocumentEvent event) {
                updateCount.run();
            }
        });

        JPanel entry = new JPanel(new FlowLayout(FlowLayout.LEADING));
        entry.add(new JLabel("Type here:"));
        entry.add(input);

        JPanel content = new JPanel(new BorderLayout(8, 8));
        content.setBorder(BorderFactory.createEmptyBorder(14, 14, 14, 14));
        content.add(entry, BorderLayout.CENTER);
        content.add(count, BorderLayout.SOUTH);

        frame.add(content);
        frame.setSize(480, 150);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class EventSourceDemo {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(EventSourceDemo::createAndShow);
    }

    private static void createAndShow() {
        JFrame frame = new JFrame("Shared Listener and Event Sources");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton saveButton = new JButton("Save");
        JButton loadButton = new JButton("Load");
        JButton clearButton = new JButton("Clear");
        saveButton.setActionCommand("save");
        loadButton.setActionCommand("load");
        clearButton.setActionCommand("clear");

        JLabel status = new JLabel("Click a button to inspect its event.");
        ActionListener sharedListener = event -> {
            String sourceName;
            if (event.getSource() == saveButton) {
                sourceName = "Save button";
            } else if (event.getSource() == loadButton) {
                sourceName = "Load button";
            } else if (event.getSource() == clearButton) {
                sourceName = "Clear button";
            } else {
                sourceName = "Unknown source";
            }
            status.setText(sourceName + " fired command \"" + event.getActionCommand() + "\".");
        };

        saveButton.addActionListener(sharedListener);
        loadButton.addActionListener(sharedListener);
        clearButton.addActionListener(sharedListener);

        JPanel buttons = new JPanel(new FlowLayout());
        buttons.add(saveButton);
        buttons.add(loadButton);
        buttons.add(clearButton);

        JPanel content = new JPanel(new BorderLayout(8, 8));
        content.add(buttons, BorderLayout.CENTER);
        content.add(status, BorderLayout.SOUTH);
        content.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 12, 12, 12));

        frame.add(content);
        frame.setSize(500, 160);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

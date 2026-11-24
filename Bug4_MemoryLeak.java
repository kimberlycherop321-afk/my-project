import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Bug4_MemoryLeak {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Fix 4: Clear Removed References");
            JPanel panel = new JPanel();
            List<JButton> createdButtons = new ArrayList<>();
            JButton createButton = new JButton("Create");
            JButton removeButton = new JButton("Remove All");

            createButton.addActionListener(event -> {
                JButton created = new JButton("Button " + (createdButtons.size() + 1));
                createdButtons.add(created);
                panel.add(created);
                panel.revalidate();
                panel.repaint();
            });

            removeButton.addActionListener(event -> {
                panel.removeAll();
                createdButtons.clear();
                panel.revalidate();
                panel.repaint();
            });

            panel.add(createButton);
            panel.add(removeButton);
            frame.add(panel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(350, 160);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}

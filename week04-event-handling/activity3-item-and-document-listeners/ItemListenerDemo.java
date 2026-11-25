import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

public class ItemListenerDemo {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(ItemListenerDemo::createAndShow);
    }

    private static void createAndShow() {
        JFrame frame = new JFrame("ItemListener: Selection Changes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel status = new JLabel("Change a checkbox or radio button.");
        JCheckBox emailUpdates = new JCheckBox("Email updates");
        JCheckBox textAlerts = new JCheckBox("Text alerts");
        JRadioButton morning = new JRadioButton("Morning");
        JRadioButton evening = new JRadioButton("Evening");
        ButtonGroup timeGroup = new ButtonGroup();
        timeGroup.add(morning);
        timeGroup.add(evening);

        ItemListener listener = event -> {
            AbstractButton source = (AbstractButton) event.getItemSelectable();
            String state = event.getStateChange() == ItemEvent.SELECTED
                    ? "selected"
                    : "deselected";
            status.setText(source.getText() + " " + state + ".");
        };
        emailUpdates.addItemListener(listener);
        textAlerts.addItemListener(listener);
        morning.addItemListener(listener);
        evening.addItemListener(listener);

        JPanel preferences = new JPanel(new FlowLayout(FlowLayout.LEADING));
        preferences.setBorder(BorderFactory.createTitledBorder("Independent preferences"));
        preferences.add(emailUpdates);
        preferences.add(textAlerts);

        JPanel times = new JPanel(new FlowLayout(FlowLayout.LEADING));
        times.setBorder(BorderFactory.createTitledBorder("Choose one class time"));
        times.add(morning);
        times.add(evening);

        JPanel options = new JPanel(new BorderLayout());
        options.add(preferences, BorderLayout.NORTH);
        options.add(times, BorderLayout.CENTER);
        options.add(status, BorderLayout.SOUTH);
        options.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        frame.add(options);
        frame.setSize(480, 240);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

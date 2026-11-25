import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class CounterApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(CounterApp::createAndShow);
    }

    private static void createAndShow() {
        JFrame frame = new JFrame("Counter App with Shortcuts");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        long[] count = {0};
        JLabel value = new JLabel("0", SwingConstants.CENTER);
        value.setFont(value.getFont().deriveFont(Font.BOLD, 48f));
        Runnable refresh = () -> value.setText(Long.toString(count[0]));
        Runnable increment = () -> {
            count[0]++;
            refresh.run();
        };
        Runnable decrement = () -> {
            count[0]--;
            refresh.run();
        };
        Runnable reset = () -> {
            count[0] = 0;
            refresh.run();
        };

        JButton decrementButton = new JButton("- Decrement");
        JButton resetButton = new JButton("Reset");
        JButton incrementButton = new JButton("+ Increment");
        decrementButton.addActionListener(event -> decrement.run());
        resetButton.addActionListener(event -> reset.run());
        incrementButton.addActionListener(event -> increment.run());

        JPanel buttons = new JPanel(new FlowLayout());
        buttons.add(decrementButton);
        buttons.add(resetButton);
        buttons.add(incrementButton);

        JLabel shortcuts = new JLabel(
                "Shortcuts: Ctrl+Up increments, Ctrl+Down decrements, Ctrl+R resets.",
                SwingConstants.CENTER);
        JPanel content = new JPanel(new BorderLayout(8, 8));
        content.setBorder(BorderFactory.createEmptyBorder(18, 18, 18, 18));
        content.add(value, BorderLayout.CENTER);
        content.add(buttons, BorderLayout.SOUTH);
        content.add(shortcuts, BorderLayout.NORTH);

        bindShortcut(frame, KeyEvent.VK_UP, "increment", increment);
        bindShortcut(frame, KeyEvent.VK_DOWN, "decrement", decrement);
        bindShortcut(frame, KeyEvent.VK_R, "reset", reset);

        frame.add(content);
        frame.setSize(520, 260);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void bindShortcut(
            JFrame frame,
            int keyCode,
            String actionName,
            Runnable action) {
        KeyStroke keyStroke = KeyStroke.getKeyStroke(keyCode, InputEvent.CTRL_DOWN_MASK);
        frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(keyStroke, actionName);
        ActionMap actions = frame.getRootPane().getActionMap();
        actions.put(actionName, new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent event) {
                action.run();
            }
        });
    }
}

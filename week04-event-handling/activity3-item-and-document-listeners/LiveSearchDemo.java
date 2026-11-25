import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class LiveSearchDemo {
    private static final List<String> ITEMS = Arrays.asList(
            "ActionListener",
            "ButtonGroup",
            "DocumentListener",
            "EventQueue",
            "JButton",
            "JCheckBox",
            "JFrame",
            "ItemListener",
            "KeyListener",
            "MouseListener",
            "SwingUtilities");

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LiveSearchDemo::createAndShow);
    }

    private static void createAndShow() {
        JFrame frame = new JFrame("Live Search with DocumentListener");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextField searchField = new JTextField(24);
        searchField.setToolTipText("Type to filter the listener and Swing component list.");
        DefaultListModel<String> results = new DefaultListModel<>();
        JList<String> resultList = new JList<>(results);
        JLabel resultCount = new JLabel();

        Runnable updateResults = () -> {
            String query = searchField.getText().trim().toLowerCase(Locale.ROOT);
            results.clear();
            for (String item : ITEMS) {
                if (item.toLowerCase(Locale.ROOT).contains(query)) {
                    results.addElement(item);
                }
            }
            resultCount.setText(results.size() + " matching item(s)");
        };
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent event) {
                updateResults.run();
            }

            @Override
            public void removeUpdate(DocumentEvent event) {
                updateResults.run();
            }

            @Override
            public void changedUpdate(DocumentEvent event) {
                updateResults.run();
            }
        });
        updateResults.run();

        JPanel search = new JPanel(new BorderLayout(8, 8));
        search.add(new JLabel("Search:"), BorderLayout.WEST);
        search.add(searchField, BorderLayout.CENTER);
        search.setBorder(BorderFactory.createEmptyBorder(12, 12, 8, 12));

        JPanel content = new JPanel(new BorderLayout(8, 8));
        content.add(search, BorderLayout.NORTH);
        content.add(new JScrollPane(resultList), BorderLayout.CENTER);
        content.add(resultCount, BorderLayout.SOUTH);
        content.setBorder(BorderFactory.createEmptyBorder(0, 12, 12, 12));

        frame.setJMenuBar(createMenuBar(searchField));
        frame.add(content);
        frame.setSize(460, 360);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static javax.swing.JMenuBar createMenuBar(JTextField searchField) {
        javax.swing.JMenuBar menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu searchMenu = new javax.swing.JMenu("Search");
        javax.swing.JMenuItem focusItem = new javax.swing.JMenuItem("Focus search");
        focusItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(KeyEvent.VK_F, KeyEvent.CTRL_DOWN_MASK));
        focusItem.addActionListener(event -> searchField.requestFocusInWindow());
        searchMenu.add(focusItem);
        menuBar.add(searchMenu);
        return menuBar;
    }
}

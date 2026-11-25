import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Arrays;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class ContactListDemo {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(ContactListDemo::createAndShow);
    }

    private static void createAndShow() {
        JFrame frame = new JFrame("JList with a Custom Contact Renderer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        List<Contact> contacts = Arrays.asList(
                new Contact("Amina Hassan", "amina@example.com", "Available"),
                new Contact("Ben Carter", "ben.carter@example.com", "Busy"),
                new Contact("Chloe Martin", "chloe@example.com", "Away"),
                new Contact("Diego Ruiz", "diego.ruiz@example.com", "Available"),
                new Contact("Elena Park", "elena@example.com", "Busy"));
        DefaultListModel<Contact> model = new DefaultListModel<>();
        for (Contact contact : contacts) {
            model.addElement(contact);
        }

        JList<Contact> contactList = new JList<>(model);
        contactList.setCellRenderer(new ContactRenderer());
        contactList.setFixedCellHeight(68);
        contactList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        contactList.addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting()) {
                Contact selected = contactList.getSelectedValue();
                if (selected != null) {
                    frame.setTitle("Contact: " + selected.name);
                }
            }
        });

        JLabel instructions = new JLabel(
                "Each row is painted by a reusable ListCellRenderer.",
                SwingConstants.CENTER);
        instructions.setBorder(BorderFactory.createEmptyBorder(10, 8, 10, 8));
        frame.add(instructions, BorderLayout.NORTH);
        frame.add(new JScrollPane(contactList), BorderLayout.CENTER);
        frame.setSize(520, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static final class Contact {
        private final String name;
        private final String email;
        private final String status;

        private Contact(String name, String email, String status) {
            this.name = name;
            this.email = email;
            this.status = status;
        }
    }

    private static final class ContactRenderer extends JPanel
            implements ListCellRenderer<Contact> {
        private static final long serialVersionUID = 1L;

        private final JLabel name = new JLabel();
        private final JLabel email = new JLabel();
        private final JLabel status = new JLabel("", SwingConstants.CENTER);

        private ContactRenderer() {
            super(new BorderLayout(12, 0));
            setOpaque(true);
            JPanel details = new JPanel(new GridLayout(0, 1, 0, 3));
            details.setOpaque(false);
            details.add(name);
            details.add(email);
            status.setOpaque(true);
            status.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
            add(details, BorderLayout.CENTER);
            add(status, BorderLayout.EAST);
        }

        @Override
        public JPanel getListCellRendererComponent(
                JList<? extends Contact> list,
                Contact contact,
                int index,
                boolean selected,
                boolean focused) {
            Color background = selected ? list.getSelectionBackground() : list.getBackground();
            Color foreground = selected ? list.getSelectionForeground() : list.getForeground();
            setBackground(background);
            name.setText(contact.name);
            name.setFont(name.getFont().deriveFont(Font.BOLD));
            name.setForeground(foreground);
            email.setText(contact.email);
            email.setForeground(foreground);
            status.setText(contact.status);
            status.setForeground(Color.BLACK);
            status.setBackground(statusColor(contact.status));
            if (focused) {
                setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(list.getSelectionBackground()),
                        BorderFactory.createEmptyBorder(7, 9, 7, 9)));
            } else {
                setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
            }
            return this;
        }

        private static Color statusColor(String value) {
            switch (value) {
                case "Available":
                    return new Color(170, 225, 180);
                case "Busy":
                    return new Color(245, 190, 185);
                default:
                    return new Color(230, 220, 165);
            }
        }
    }
}

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class StudentTableDemo {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(StudentTableDemo::createAndShow);
    }

    private static void createAndShow() {
        JFrame frame = new JFrame("JTable with DefaultTableModel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DefaultTableModel model = new DefaultTableModel(
                new Object[] {"ID", "Name", "Email", "Year"}, 0) {
            private static final long serialVersionUID = 1L;

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 3 ? Integer.class : String.class;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0;
            }
        };
        model.addRow(new Object[] {"S1001", "Amina Hassan", "amina@example.com", 2});
        model.addRow(new Object[] {"S1002", "Ben Carter", "ben@example.com", 1});
        model.addRow(new Object[] {"S1003", "Chloe Martin", "chloe@example.com", 3});
        model.addRow(new Object[] {"S1004", "Diego Ruiz", "diego@example.com", 2});

        JTable table = new JTable(model);
        table.setAutoCreateRowSorter(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setFillsViewportHeight(true);

        JLabel status = new JLabel("Edit cells, sort by a column, or change the rows.");
        int[] nextId = {1005};
        JButton addButton = new JButton("Add student");
        addButton.addActionListener(event -> {
            int modelRow = model.getRowCount();
            int id = nextId[0]++;
            model.addRow(new Object[] {
                "S" + id,
                "New Student",
                "student" + id + "@example.com",
                1
            });
            int viewRow = table.convertRowIndexToView(modelRow);
            if (viewRow >= 0) {
                table.setRowSelectionInterval(viewRow, viewRow);
            }
            status.setText("Added student S" + id + ".");
        });

        JButton deleteButton = new JButton("Delete selected");
        deleteButton.addActionListener(event -> {
            int viewRow = table.getSelectedRow();
            if (viewRow < 0) {
                status.setText("Select a student row to delete.");
                return;
            }
            int modelRow = table.convertRowIndexToModel(viewRow);
            String studentId = (String) model.getValueAt(modelRow, 0);
            model.removeRow(modelRow);
            status.setText("Deleted student " + studentId + ".");
        });

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.LEADING));
        actions.add(addButton);
        actions.add(deleteButton);
        actions.add(status);

        JPanel content = new JPanel(new BorderLayout(8, 8));
        content.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        content.add(new JScrollPane(table), BorderLayout.CENTER);
        content.add(actions, BorderLayout.SOUTH);

        frame.add(content);
        frame.setSize(720, 390);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

public class FileExplorerDemo {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(FileExplorerDemo::createAndShow);
    }

    private static void createAndShow() {
        JFrame frame = new JFrame("JTree and JTabbedPane Explorer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Demo Workspace");
        DefaultMutableTreeNode documents = new DefaultMutableTreeNode("Documents");
        documents.add(new DefaultMutableTreeNode("Lecture Notes.txt"));
        documents.add(new DefaultMutableTreeNode("Week 5 Lab.txt"));
        DefaultMutableTreeNode projects = new DefaultMutableTreeNode("Projects");
        projects.add(new DefaultMutableTreeNode("Swing Demos"));
        projects.add(new DefaultMutableTreeNode("Observer Pattern"));
        DefaultMutableTreeNode pictures = new DefaultMutableTreeNode("Pictures");
        pictures.add(new DefaultMutableTreeNode("diagram.png"));
        root.add(documents);
        root.add(projects);
        root.add(pictures);

        JTree tree = new JTree(root);
        JTextArea overview = createTextArea();
        JTextArea details = createTextArea();
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Overview", new JScrollPane(overview));
        tabs.addTab("Details", new JScrollPane(details));

        TreeSelectionListener selectionListener = (TreeSelectionEvent event) -> {
            Object selected = tree.getLastSelectedPathComponent();
            if (selected instanceof DefaultMutableTreeNode) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) selected;
                String name = node.getUserObject().toString();
                overview.setText("Selected: " + name
                        + "\n\nThis explorer uses a sample workspace tree; it does not read local files.");
                details.setText("Name: " + name
                        + "\nType: " + (node.isLeaf() ? "Item" : "Folder")
                        + "\nChildren: " + node.getChildCount()
                        + "\nPath: " + tree.getSelectionPath());
                overview.setCaretPosition(0);
                details.setCaretPosition(0);
            }
        };
        tree.addTreeSelectionListener(selectionListener);
        tree.setSelectionPath(new javax.swing.tree.TreePath(root.getPath()));

        JScrollPane treeScrollPane = new JScrollPane(tree);
        treeScrollPane.setBorder(BorderFactory.createTitledBorder("Folders"));
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treeScrollPane, tabs);
        splitPane.setResizeWeight(0.32);
        splitPane.setDividerLocation(230);

        frame.add(splitPane, BorderLayout.CENTER);
        frame.setSize(760, 460);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static JTextArea createTextArea() {
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
        textArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return textArea;
    }
}

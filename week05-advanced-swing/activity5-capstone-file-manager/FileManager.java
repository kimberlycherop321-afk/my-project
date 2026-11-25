import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.ExpandVetoException;
import javax.swing.tree.TreePath;

public class FileManager {
    private final JFrame frame = new JFrame("Swing File Manager");
    private final JLabel status = new JLabel("Choose a folder to browse.");
    private final DefaultTableModel tableModel = new DefaultTableModel(
            new Object[] {"Name", "Type", "Size (bytes)", "Modified"}, 0) {
        private static final long serialVersionUID = 1L;

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return columnIndex == 2 ? Long.class : String.class;
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private final JTable table = new JTable(tableModel);
    private final ArrayList<File> entries = new ArrayList<>();
    private final DefaultTreeModel treeModel;
    private final javax.swing.JTree directoryTree;
    private File currentDirectory;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FileManager().show());
    }

    private FileManager() {
        File home = new File(System.getProperty("user.home"));
        if (!home.isDirectory()) {
            home = new File(".").getAbsoluteFile();
        }
        currentDirectory = home;

        FileNode root = createNode(home);
        treeModel = new DefaultTreeModel(root);
        directoryTree = new javax.swing.JTree(treeModel);
        directoryTree.setRootVisible(true);
        directoryTree.setShowsRootHandles(true);
        directoryTree.addTreeWillExpandListener(new TreeWillExpandListener() {
            @Override
            public void treeWillExpand(TreeExpansionEvent event) throws ExpandVetoException {
                Object node = event.getPath().getLastPathComponent();
                if (node instanceof FileNode) {
                    loadChildren((FileNode) node);
                }
            }

            @Override
            public void treeWillCollapse(TreeExpansionEvent event) {
            }
        });
        directoryTree.addTreeSelectionListener(event -> {
            Object selected = directoryTree.getLastSelectedPathComponent();
            if (selected instanceof FileNode) {
                navigateTo(((FileNode) selected).directory);
            }
        });

        table.setAutoCreateRowSorter(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setFillsViewportHeight(true);
        table.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting()) {
                File selected = getSelectedFile();
                if (selected != null) {
                    status.setText(selected.getAbsolutePath());
                }
            }
        });
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                if (event.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(event)) {
                    openSelected();
                }
            }
        });
        directoryTree.setSelectionPath(new TreePath(root.getPath()));
    }

    private void show() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setJMenuBar(createMenuBar());

        JToolBar toolBar = createToolBar();
        JScrollPane treePane = new JScrollPane(directoryTree);
        treePane.setPreferredSize(new Dimension(250, 400));
        treePane.setBorder(BorderFactory.createTitledBorder("Folders"));
        JScrollPane tablePane = new JScrollPane(table);
        tablePane.setBorder(BorderFactory.createTitledBorder("Folder contents"));
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treePane, tablePane);
        splitPane.setResizeWeight(0.32);
        splitPane.setDividerLocation(250);

        JPanel content = new JPanel(new BorderLayout(6, 6));
        content.add(toolBar, BorderLayout.NORTH);
        content.add(splitPane, BorderLayout.CENTER);
        content.add(status, BorderLayout.SOUTH);
        content.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));

        frame.add(content);
        frame.setSize(980, 620);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        refreshDirectory();
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        addMenuItem(fileMenu, "Choose Folder...", KeyEvent.VK_O, openChooserAction());
        addMenuItem(fileMenu, "Open Selected", KeyEvent.VK_ENTER, this::openSelected);
        addMenuItem(fileMenu, "Go to Parent Folder", KeyEvent.VK_UP, this::goToParent);
        addMenuItem(fileMenu, "Refresh", KeyEvent.VK_R, this::refreshDirectory);
        fileMenu.addSeparator();
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(event -> frame.dispose());
        fileMenu.add(exitItem);

        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(event -> JOptionPane.showMessageDialog(
                frame,
                "Browse local folders and view file details. This demo does not modify files.",
                "About Swing File Manager",
                JOptionPane.INFORMATION_MESSAGE));
        helpMenu.add(aboutItem);
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        return menuBar;
    }

    private JToolBar createToolBar() {
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        JButton chooseButton = new JButton("Choose Folder");
        chooseButton.addActionListener(event -> openChooserAction().run());
        JButton parentButton = new JButton("Up");
        parentButton.addActionListener(event -> goToParent());
        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(event -> refreshDirectory());
        JButton openButton = new JButton("Open Selected");
        openButton.addActionListener(event -> openSelected());
        toolBar.add(chooseButton);
        toolBar.add(parentButton);
        toolBar.add(refreshButton);
        toolBar.add(openButton);
        return toolBar;
    }

    private Runnable openChooserAction() {
        return () -> {
            JFileChooser chooser = new JFileChooser(currentDirectory);
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.setDialogTitle("Choose a folder to browse");
            if (chooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
                setRootDirectory(chooser.getSelectedFile());
            }
        };
    }

    private void addMenuItem(JMenu menu, String label, int keyCode, Runnable action) {
        JMenuItem item = new JMenuItem(label);
        item.setAccelerator(KeyStroke.getKeyStroke(keyCode, InputEvent.CTRL_DOWN_MASK));
        item.addActionListener(event -> action.run());
        menu.add(item);
    }

    private FileNode createNode(File directory) {
        FileNode node = new FileNode(directory);
        if (directory.isDirectory()) {
            node.add(new DefaultMutableTreeNode("Expand to load...", false));
        }
        return node;
    }

    private void loadChildren(FileNode node) {
        if (node.loaded) {
            return;
        }
        node.loaded = true;
        node.removeAllChildren();

        File[] directories;
        try {
            directories = node.directory.listFiles(
                    child -> child.isDirectory() && !java.nio.file.Files.isSymbolicLink(child.toPath()));
        } catch (SecurityException exception) {
            status.setText("Access denied: " + node.directory.getAbsolutePath());
            treeModel.nodeStructureChanged(node);
            return;
        }
        if (directories == null) {
            status.setText("Could not list folders in " + node.directory.getAbsolutePath());
            treeModel.nodeStructureChanged(node);
            return;
        }

        Arrays.sort(directories, Comparator.comparing(
                File::getName,
                String.CASE_INSENSITIVE_ORDER));
        for (File directory : directories) {
            node.add(createNode(directory));
        }
        treeModel.nodeStructureChanged(node);
    }

    private void setRootDirectory(File directory) {
        if (!directory.isDirectory()) {
            status.setText("That location is not a folder.");
            return;
        }
        currentDirectory = directory;
        FileNode root = createNode(directory);
        treeModel.setRoot(root);
        directoryTree.setSelectionPath(new TreePath(root.getPath()));
        navigateTo(directory);
    }

    private void navigateTo(File directory) {
        currentDirectory = directory;
        frame.setTitle("Swing File Manager - " + directory.getAbsolutePath());
        refreshDirectory();
    }

    private void goToParent() {
        File parent = currentDirectory == null ? null : currentDirectory.getParentFile();
        if (parent != null) {
            setRootDirectory(parent);
        }
    }

    private void refreshDirectory() {
        if (currentDirectory == null || !currentDirectory.isDirectory()) {
            status.setText("Select a folder to browse.");
            return;
        }
        File[] files;
        try {
            files = currentDirectory.listFiles();
        } catch (SecurityException exception) {
            status.setText("Access denied: " + currentDirectory.getAbsolutePath());
            return;
        }
        if (files == null) {
            status.setText("Could not list " + currentDirectory.getAbsolutePath());
            return;
        }

        Arrays.sort(files, (first, second) -> {
            int directoryOrder = Boolean.compare(first.isFile(), second.isFile());
            return directoryOrder == 0
                    ? first.getName().compareToIgnoreCase(second.getName())
                    : directoryOrder;
        });
        entries.clear();
        tableModel.setRowCount(0);
        DateFormat dateFormat = DateFormat.getDateTimeInstance();
        int folderCount = 0;
        for (File file : files) {
            boolean isDirectory = file.isDirectory();
            if (isDirectory) {
                folderCount++;
            }
            entries.add(file);
            Long size = isDirectory ? null : file.length();
            String modified = file.lastModified() == 0
                    ? "Unknown"
                    : dateFormat.format(new Date(file.lastModified()));
            tableModel.addRow(new Object[] {
                file.getName().isEmpty() ? file.getAbsolutePath() : file.getName(),
                fileType(file),
                size,
                modified
            });
        }
        status.setText(files.length + " item(s), " + folderCount + " folder(s) in "
                + currentDirectory.getAbsolutePath());
    }

    private String fileType(File file) {
        if (file.isDirectory()) {
            return "Folder";
        }
        String name = file.getName();
        int extensionStart = name.lastIndexOf('.');
        return extensionStart > 0
                ? name.substring(extensionStart + 1).toUpperCase(java.util.Locale.ROOT) + " file"
                : "File";
    }

    private File getSelectedFile() {
        int viewRow = table.getSelectedRow();
        if (viewRow < 0) {
            return null;
        }
        int modelRow = table.convertRowIndexToModel(viewRow);
        return modelRow < entries.size() ? entries.get(modelRow) : null;
    }

    private void openSelected() {
        File selected = getSelectedFile();
        if (selected == null) {
            status.setText("Select a row in the folder table first.");
        } else if (selected.isDirectory()) {
            setRootDirectory(selected);
        } else {
            showFileDetails(selected);
        }
    }

    private void showFileDetails(File file) {
        String modified = file.lastModified() == 0
                ? "Unknown"
                : DateFormat.getDateTimeInstance().format(new Date(file.lastModified()));
        JOptionPane.showMessageDialog(
                frame,
                "Name: " + file.getName()
                        + "\nType: " + fileType(file)
                        + "\nSize: " + file.length() + " bytes"
                        + "\nModified: " + modified
                        + "\nPath: " + file.getAbsolutePath(),
                "File details",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private static final class FileNode extends DefaultMutableTreeNode {
        private static final long serialVersionUID = 1L;

        private final File directory;
        private boolean loaded;

        private FileNode(File directory) {
            super(directory);
            this.directory = directory;
        }

        @Override
        public String toString() {
            String name = directory.getName();
            return name.isEmpty() ? directory.getAbsolutePath() : name;
        }
    }
}

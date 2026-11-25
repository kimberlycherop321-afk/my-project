import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

public class DrawingApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(DrawingApp::createAndShow);
    }

    private static void createAndShow() {
        JFrame frame = new JFrame("Mouse Drawing App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel status = new JLabel("Click and drag in the canvas to draw.");
        DrawingCanvas canvas = new DrawingCanvas(status);
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(event -> canvas.clear());

        JPanel controls = new JPanel(new BorderLayout());
        controls.add(status, BorderLayout.CENTER);
        controls.add(clearButton, BorderLayout.EAST);
        controls.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 10, 8, 10));

        frame.add(controls, BorderLayout.NORTH);
        frame.add(canvas, BorderLayout.CENTER);
        frame.setSize(640, 440);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static final class DrawingCanvas extends JPanel {
        private static final long serialVersionUID = 1L;

        private final ArrayList<ArrayList<Point>> strokes = new ArrayList<>();
        private final JLabel status;
        private ArrayList<Point> activeStroke;

        private DrawingCanvas(JLabel status) {
            this.status = status;
            setBackground(Color.WHITE);
            setPreferredSize(new Dimension(600, 360));
            setBorder(new LineBorder(new Color(180, 180, 180)));

            MouseAdapter drawingListener = new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent event) {
                    if (SwingUtilities.isLeftMouseButton(event)) {
                        activeStroke = new ArrayList<>();
                        activeStroke.add(event.getPoint());
                        strokes.add(activeStroke);
                        status.setText("Drawing... release the mouse to finish the stroke.");
                        repaint();
                    }
                }

                @Override
                public void mouseDragged(MouseEvent event) {
                    if (activeStroke != null
                            && (event.getModifiersEx() & InputEvent.BUTTON1_DOWN_MASK) != 0) {
                        activeStroke.add(event.getPoint());
                        repaint();
                    }
                }

                @Override
                public void mouseReleased(MouseEvent event) {
                    if (activeStroke != null && SwingUtilities.isLeftMouseButton(event)) {
                        activeStroke = null;
                        status.setText("Stroke finished. Draw another or select Clear.");
                    }
                }
            };
            addMouseListener(drawingListener);
            addMouseMotionListener(drawingListener);
        }

        private void clear() {
            strokes.clear();
            activeStroke = null;
            status.setText("Canvas cleared. Click and drag to draw.");
            repaint();
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            Graphics2D drawing = (Graphics2D) graphics.create();
            try {
                drawing.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                drawing.setColor(new Color(25, 82, 155));
                drawing.setStroke(new BasicStroke(
                        3f,
                        BasicStroke.CAP_ROUND,
                        BasicStroke.JOIN_ROUND));
                for (ArrayList<Point> stroke : strokes) {
                    if (stroke.size() == 1) {
                        Point point = stroke.get(0);
                        drawing.fillOval(point.x - 2, point.y - 2, 4, 4);
                    } else {
                        for (int index = 1; index < stroke.size(); index++) {
                            Point previous = stroke.get(index - 1);
                            Point current = stroke.get(index);
                            drawing.drawLine(previous.x, previous.y, current.x, current.y);
                        }
                    }
                }
            } finally {
                drawing.dispose();
            }
        }
    }
}

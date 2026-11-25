import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class KeyboardMover {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(KeyboardMover::createAndShow);
    }

    private static void createAndShow() {
        JFrame frame = new JFrame("Keyboard Mover");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel status = new JLabel("Use the arrow keys or W A S D to move the square.");
        MoverPanel mover = new MoverPanel(status);
        JPanel content = new JPanel(new BorderLayout(8, 8));
        content.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        content.add(mover, BorderLayout.CENTER);
        content.add(status, BorderLayout.SOUTH);

        frame.add(content);
        frame.setSize(560, 400);
        frame.setLocationRelativeTo(null);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent event) {
                mover.requestFocusInWindow();
            }
        });
        frame.setVisible(true);
    }

    private static final class MoverPanel extends JPanel {
        private static final long serialVersionUID = 1L;
        private static final int SIZE = 36;
        private static final int STEP = 16;

        private final JLabel status;
        private int x = 120;
        private int y = 90;

        private MoverPanel(JLabel status) {
            this.status = status;
            setFocusable(true);
            setBackground(Color.WHITE);
            setPreferredSize(new Dimension(520, 320));
            setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180)));
            addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent event) {
                    moveForKey(event.getKeyCode());
                }
            });
        }

        private void moveForKey(int keyCode) {
            switch (keyCode) {
                case KeyEvent.VK_UP:
                case KeyEvent.VK_W:
                    y -= STEP;
                    break;
                case KeyEvent.VK_DOWN:
                case KeyEvent.VK_S:
                    y += STEP;
                    break;
                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_A:
                    x -= STEP;
                    break;
                case KeyEvent.VK_RIGHT:
                case KeyEvent.VK_D:
                    x += STEP;
                    break;
                default:
                    return;
            }
            x = Math.max(0, Math.min(x, getWidth() - SIZE));
            y = Math.max(0, Math.min(y, getHeight() - SIZE));
            status.setText("Square position: (" + x + ", " + y + ").");
            repaint();
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            graphics.setColor(new Color(45, 115, 190));
            graphics.fillRoundRect(x, y, SIZE, SIZE, 8, 8);
        }
    }
}

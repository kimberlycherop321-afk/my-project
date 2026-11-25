import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class GridLayoutDemo {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(GridLayoutDemo::createAndShow);
    }

    private static void createAndShow() {
        JFrame frame = new JFrame("GridLayout Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel keypad = new JPanel(new GridLayout(3, 3, 8, 8));
        for (int number = 1; number <= 9; number++) {
            keypad.add(new JButton(Integer.toString(number)));
        }

        frame.add(keypad);
        frame.setSize(360, 320);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class EventDrivenCalculator {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(EventDrivenCalculator::createAndShow);
    }

    private static void createAndShow() {
        JFrame frame = new JFrame("Event-Driven Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(5, 2, 8, 8));

        JTextField firstField = new JTextField();
        JTextField secondField = new JTextField();
        JLabel answerLabel = new JLabel("Result:");

        frame.add(new JLabel("Number 1:"));
        frame.add(firstField);
        frame.add(new JLabel("Number 2:"));
        frame.add(secondField);

        for (String operator : new String[]{"+", "-", "*", "/"}) {
            JButton button = new JButton(operator);
            button.addActionListener(event -> calculate(firstField, secondField, answerLabel, operator));
            frame.add(button);
        }

        frame.add(answerLabel);
        frame.pack();
        frame.setSize(320, 220);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void calculate(JTextField firstField, JTextField secondField,
                                  JLabel answerLabel, String operator) {
        try {
            double first = Double.parseDouble(firstField.getText().trim());
            double second = Double.parseDouble(secondField.getText().trim());

            double result;
            switch (operator) {
                case "+":
                    result = first + second;
                    break;
                case "-":
                    result = first - second;
                    break;
                case "*":
                    result = first * second;
                    break;
                case "/":
                    if (second == 0) {
                        answerLabel.setText("Cannot divide by zero.");
                        return;
                    }
                    result = first / second;
                    break;
                default:
                    answerLabel.setText("Invalid operator.");
                    return;
            }

            answerLabel.setText("Result: " + result);
        } catch (NumberFormatException exception) {
            answerLabel.setText("Please enter two valid numbers.");
        }
    }
}

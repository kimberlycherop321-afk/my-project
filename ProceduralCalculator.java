import java.util.Scanner;

public class ProceduralCalculator {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter number 1: ");
        double first = input.nextDouble();

        System.out.print("Enter number 2: ");
        double second = input.nextDouble();

        System.out.print("Enter an operator (+, -, *, /): ");
        String operator = input.next();

        Double result = calculate(first, second, operator);
        if (result == null) {
            System.out.println("Invalid operator.");
        } else if (Double.isInfinite(result)) {
            System.out.println("Cannot divide by zero.");
        } else {
            System.out.println("Result: " + result);
        }

        input.close();
    }

    private static Double calculate(double first, double second, String operator) {
        switch (operator) {
            case "+":
                return first + second;
            case "-":
                return first - second;
            case "*":
                return first * second;
            case "/":
                return second == 0 ? Double.POSITIVE_INFINITY : first / second;
            default:
                return null;
        }
    }
}

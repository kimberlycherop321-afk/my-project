import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class SmartHomePrototype {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(SmartHomePrototype::createAndShow);
    }

    private static void createAndShow() {
        JFrame frame = new JFrame("Smart Home Event Prototype");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(4, 2, 8, 8));

        JLabel lightStatus = new JLabel("Lights: OFF");
        JButton lightButton = new JButton("Toggle lights");
        lightButton.addActionListener(event -> {
            boolean isOff = lightStatus.getText().endsWith("OFF");
            lightStatus.setText(isOff ? "Lights: ON" : "Lights: OFF");
        });

        JLabel thermostatStatus = new JLabel("Thermostat: 22 C");
        JButton thermostatButton = new JButton("Raise temperature");
        final int[] temperature = {22};
        thermostatButton.addActionListener(event -> {
            temperature[0]++;
            thermostatStatus.setText("Thermostat: " + temperature[0] + " C");
        });

        JLabel doorStatus = new JLabel("Door: LOCKED");
        JButton doorButton = new JButton("Toggle door");
        doorButton.addActionListener(event -> {
            boolean isLocked = doorStatus.getText().endsWith("LOCKED");
            doorStatus.setText(isLocked ? "Door: UNLOCKED" : "Door: LOCKED");
        });

        JLabel sensorStatus = new JLabel("Motion sensor: No motion");
        JButton sensorButton = new JButton("Simulate sensor event");
        sensorButton.addActionListener(event -> sensorStatus.setText("Motion sensor: Motion detected"));

        frame.add(lightStatus);
        frame.add(lightButton);
        frame.add(thermostatStatus);
        frame.add(thermostatButton);
        frame.add(doorStatus);
        frame.add(doorButton);
        frame.add(sensorStatus);
        frame.add(sensorButton);

        frame.pack();
        frame.setSize(420, 260);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

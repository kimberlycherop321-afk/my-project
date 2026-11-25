public class ComparisonMain {
    public static void main(String[] args) {
        CustomTemperatureSubject custom = new CustomTemperatureSubject();
        custom.register(temperature ->
                System.out.println("Custom observer: " + temperature));
        custom.setTemperature(18.0);

        PropertyChangeExample bean = new PropertyChangeExample();
        bean.addPropertyChangeListener(event ->
                System.out.printf("PropertyChangeListener: %s changed from %s to %s%n",
                        event.getPropertyName(), event.getOldValue(), event.getNewValue()));
        bean.setScore(10);
        bean.setScore(15);

        System.out.println("JavaFX: use ObservableValue/Property change listeners and bind() "
                + "where JavaFX is available.");
    }
}

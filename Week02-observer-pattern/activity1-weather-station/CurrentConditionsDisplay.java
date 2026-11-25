public class CurrentConditionsDisplay implements WeatherObserver {
    public void update(double temperature, double humidity, double pressure) {
        System.out.printf("Current conditions: %.1f C degrees and %.1f%% humidity%n",
                temperature, humidity);
    }
}

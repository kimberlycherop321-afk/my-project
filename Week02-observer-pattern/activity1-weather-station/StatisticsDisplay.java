public class StatisticsDisplay implements WeatherObserver {
    private double maximum = Double.NEGATIVE_INFINITY;
    private double minimum = Double.POSITIVE_INFINITY;
    private double temperatureSum;
    private int readings;

    public void update(double temperature, double humidity, double pressure) {
        temperatureSum += temperature;
        readings++;
        maximum = Math.max(maximum, temperature);
        minimum = Math.min(minimum, temperature);
        System.out.printf("Temperature stats: avg %.1f C, min %.1f C, max %.1f C%n",
                temperatureSum / readings, minimum, maximum);
    }
}

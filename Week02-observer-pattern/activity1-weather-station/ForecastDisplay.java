public class ForecastDisplay implements WeatherObserver {
    private double lastPressure;
    private double currentPressure = 29.92;

    public void update(double temperature, double humidity, double pressure) {
        lastPressure = currentPressure;
        currentPressure = pressure;
        String forecast;
        if (currentPressure > lastPressure) forecast = "Improving weather on the way!";
        else if (currentPressure == lastPressure) forecast = "More of the same";
        else forecast = "Watch out for cooler, rainy weather";
        System.out.println("Forecast: " + forecast);
    }
}

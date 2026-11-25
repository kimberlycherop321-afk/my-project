public class WeatherStationMain {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        weatherData.registerObserver(new CurrentConditionsDisplay());
        weatherData.registerObserver(new StatisticsDisplay());
        weatherData.registerObserver(new ForecastDisplay());
        weatherData.setMeasurements(25.0, 65.0, 30.4);
        weatherData.setMeasurements(27.5, 70.0, 29.2);
        weatherData.setMeasurements(26.2, 90.0, 29.2);
    }
}

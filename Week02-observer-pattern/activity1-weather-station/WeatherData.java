import java.util.ArrayList;
import java.util.List;

public class WeatherData {
    private final List<WeatherObserver> observers = new ArrayList<>();
    private double temperature;
    private double humidity;
    private double pressure;

    public void registerObserver(WeatherObserver observer) {
        if (observer != null && !observers.contains(observer)) observers.add(observer);
    }

    public void removeObserver(WeatherObserver observer) {
        observers.remove(observer);
    }

    public void setMeasurements(double temperature, double humidity, double pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        notifyObservers();
    }

    private void notifyObservers() {
        for (WeatherObserver observer : observers) {
            observer.update(this.temperature, this.humidity, this.pressure);
        }
    }
}

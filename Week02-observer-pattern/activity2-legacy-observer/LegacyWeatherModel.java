import java.util.Observable;

@SuppressWarnings("deprecation")
public class LegacyWeatherModel extends Observable {
    private double temperature;

    public void setTemperature(double temperature) {
        this.temperature = temperature;
        setChanged();
        notifyObservers();
    }

    public double getTemperature() {
        return temperature;
    }
}

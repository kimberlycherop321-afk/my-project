import java.util.ArrayList;
import java.util.List;
import java.util.function.DoubleConsumer;

public class CustomTemperatureSubject {
    private final List<DoubleConsumer> observers = new ArrayList<>();

    public void register(DoubleConsumer observer) {
        observers.add(observer);
    }

    public void setTemperature(double temperature) {
        observers.forEach(observer -> observer.accept(temperature));
    }
}

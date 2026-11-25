import java.util.ArrayList;
import java.util.List;

public class CpuMonitor {
    private final List<SystemObserver> observers = new ArrayList<>();

    public void register(SystemObserver observer) {
        observers.add(observer);
    }

    public void remove(SystemObserver observer) {
        observers.remove(observer);
    }

    public void setCpuUsage(int cpuUsage) {
        for (SystemObserver observer : observers) observer.update(cpuUsage);
    }
}

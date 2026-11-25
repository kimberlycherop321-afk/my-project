public class LabelDisplay implements SystemObserver {
    public void update(int cpuUsage) {
        System.out.println("LabelDisplay: CPU usage " + cpuUsage + "%");
    }
}

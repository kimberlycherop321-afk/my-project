public class BarChartDisplay implements SystemObserver {
    public void update(int cpuUsage) {
        System.out.println("BarChartDisplay: [" + cpuUsage + "%]");
    }
}

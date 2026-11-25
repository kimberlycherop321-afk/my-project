public class TightlyCoupledSystem {
    public void setCpuUsage(int cpuUsage) {
        System.out.println("LabelDisplay: CPU usage " + cpuUsage + "%");
        System.out.println("BarChartDisplay: [" + cpuUsage + "%]");
        System.out.println("TextLogger: logged CPU usage " + cpuUsage + "%");
    }
}

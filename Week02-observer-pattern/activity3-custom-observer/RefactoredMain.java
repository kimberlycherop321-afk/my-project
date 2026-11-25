public class RefactoredMain {
    public static void main(String[] args) {
        System.out.println("Tightly coupled version:");
        new TightlyCoupledSystem().setCpuUsage(72);
        System.out.println("\nObserver version:");
        CpuMonitor monitor = new CpuMonitor();
        monitor.register(new LabelDisplay());
        monitor.register(new BarChartDisplay());
        monitor.register(new TextLogger());
        monitor.setCpuUsage(72);
    }
}

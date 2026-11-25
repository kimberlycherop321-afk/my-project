public class TextLogger implements SystemObserver {
    public void update(int cpuUsage) {
        System.out.println("TextLogger: logged CPU usage " + cpuUsage + "%");
    }
}

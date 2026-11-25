public class LegacyObserverMain {
    public static void main(String[] args) {
        LegacyWeatherModel model = new LegacyWeatherModel();
        model.addObserver(new LegacyDisplay());
        model.setTemperature(22.5);
        model.setTemperature(24.0);
        System.out.println("\njava.util.Observable and Observer were deprecated in Java 9:");
        System.out.println("- Observable is a class, not an interface.");
        System.out.println("- setChanged() is protected.");
        System.out.println("- Notifications are not type-safe.");
    }
}

import java.util.Observable;
import java.util.Observer;

@SuppressWarnings("deprecation")
public class LegacyDisplay implements Observer {
    public void update(Observable subject, Object argument) {
        LegacyWeatherModel model = (LegacyWeatherModel) subject;
        System.out.println("Legacy observer received temperature: "
                + model.getTemperature() + " C");
    }
}

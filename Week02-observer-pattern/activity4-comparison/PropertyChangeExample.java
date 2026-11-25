import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

@SuppressWarnings("this-escape")
public class PropertyChangeExample {
    private final PropertyChangeSupport changes = new PropertyChangeSupport(this);
    private int score;

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changes.addPropertyChangeListener(listener);
    }

    public void setScore(int score) {
        int oldScore = this.score;
        this.score = score;
        changes.firePropertyChange("score", oldScore, score);
    }
}

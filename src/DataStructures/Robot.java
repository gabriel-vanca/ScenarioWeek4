package DataStructures;

/**
 * Created by Gabriel on 20/02/2017.
 */
public class Robot {
    private Coordinates robotPosition;
    private boolean isAwake;

    public Robot(Coordinates coordinates) {
        SetCoordinates(coordinates);
        this.isAwake = false;
    }

    public Coordinates GetCoordinates() {
        return robotPosition;
    }

    public void SetCoordinates(Coordinates newCoordinates) {
        robotPosition = newCoordinates;
    }

    public void wake() {
        this.isAwake = true;
    }

    public boolean isAwake() {
        return this.isAwake;
    }
}

/**
 * Created by Gabriel on 20/02/2017.
 */
public class Robot {
    private Coordinates robotPosition;

    public Robot(Coordinates coordinates) {
        SetPosition(coordinates);
    }

    public Coordinates GetPosition() {
        return robotPosition;
    }

    public void SetPosition(Coordinates newCoordinates) {
        robotPosition = newCoordinates;
    }
}

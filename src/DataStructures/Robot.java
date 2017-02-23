package DataStructures;

/**
 * Created by Gabriel on 20/02/2017.
 */
public class Robot {
    private Coordinates robotPosition;
    private boolean isAwake = false;

    public Robot(Coordinates coordinates) {
        SetCoordinates(coordinates);
    }

    public Coordinates GetCoordinates() {
        return robotPosition;
    }

    public void SetCoordinates(Coordinates newCoordinates) {
        robotPosition = newCoordinates;
    }

    public boolean getIsAwake(){
        return isAwake;
    }

    public void setIsAwake(boolean isAwake){
        this.isAwake = isAwake;
    }
}

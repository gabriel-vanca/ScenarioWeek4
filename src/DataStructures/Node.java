package DataStructures;

/**
 * Created by Andreas Zinonos on 21/02/17.
 */

public class Node {

    private Coordinates robotPosition;

    public Node(Coordinates coordinates) {
        setCoordinates(coordinates);
    }

    public Coordinates getCoordinates() {
        return robotPosition;
    }

    public void setCoordinates(Coordinates newCoordinates) {
        robotPosition = newCoordinates;
    }

}

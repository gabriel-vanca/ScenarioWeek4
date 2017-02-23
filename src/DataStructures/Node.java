package DataStructures;

import java.util.ArrayList;

/**
 * Created by Gabriel on 21/02/2017.
 */

public class Node {

    private Coordinates coordinates;

    public Obstacle ParentObstacle = null;

    public ArrayList<Robot> robotsAtThisNode;
    public ArrayList<Edge> edgesList;

    public Node(double x, double y, Obstacle parentObstacle)
    {
        robotsAtThisNode = new ArrayList<>();
        edgesList = new ArrayList<>();
        coordinates = new Coordinates(x,y);
        this.ParentObstacle = parentObstacle;
    }

    public Node(double x, double y, Robot currentRobot, Obstacle parentObstacle)
    {
        this(x,y, parentObstacle);
        robotsAtThisNode.add(currentRobot);
    }

    public Coordinates GetCoordinates()
    {
        return coordinates;
    }
}

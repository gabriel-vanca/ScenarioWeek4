package DataStructures;

import com.frappe.main.Path;

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

    public boolean moveRobot(Robot robot, Node node) {
        if (this.robotsAtThisNode.contains(robot)) {
            this.robotsAtThisNode.remove(robot);
            node.addRobot(robot);
            return true;
        } else {
            return false;
        }
    }

    public void moveAnyRobot(Node node, Path path) {
        if (this.robotsAtThisNode.size() > 0) {

            // Add to this Robot's path
            this.robotsAtThisNode.get(0).addPath(path, path.getLength());

            // Set order of the robot.


            node.addRobot(this.robotsAtThisNode.get(0));
            this.robotsAtThisNode.remove(0);
//            System.out.println("Moved robot");
        }
    }

    private void addRobot(Robot robot) {
        this.robotsAtThisNode.add(robot);
    }

    public void wakeRobots() {
        for (Robot robot: this.robotsAtThisNode) {
            robot.wake();
        }
    }

//    public ArrayList<Robot> getRr

  /*  public void AddNeighbouringNode(Node node)
    {
        Edge edge = new Edge(this, node);
        edgesList.add(edge);
    }*/

    public Coordinates GetCoordinates()
    {
        return coordinates;
    }
}

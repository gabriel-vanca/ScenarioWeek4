package DataStructures;

import java.util.ArrayList;

/**
 * Created by Gabriel on 21/02/2017.
 */

public class Node {

    public ArrayList<Robot> robotsAtThisNode;
    public ArrayList<Edge> adjacentNodes;

    public Node()
    {
        robotsAtThisNode = new ArrayList<>();
        adjacentNodes = new ArrayList<>();
    }

    public Node(Robot robot)
    {
        this();
        robotsAtThisNode.add(robot);
    }

    public void AddNeighbouringNode(Node node)
    {
        Edge edge = new Edge(this, node);
        adjacentNodes.add(edge);
    }

}

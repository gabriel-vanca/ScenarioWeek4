import DataStructures.Node;
import DataStructures.Robot;

import java.util.ArrayList;

/**
 * Created by Gabriel on 21/02/2017.
 */
public class Graph{

    public static Graph instance = null;

    public ArrayList<Node> nodesList;

    private Graph()
    {
    }

    public static Graph GetInstance()
    {
        if(instance == null)
            instance = new Graph();
        return instance;
    }

    public void GenerateGraph() {
        nodesList = new ArrayList<>();
        Map map = Map.GetInstance();

        for (Robot robot : map.RobotsList) {
            Node currentNode = new Node(robot);
            nodesList.add(currentNode);
        }

        for (Node node1 : nodesList) {
            for (Node node2 : nodesList) {
                if(node1 != node2)
                    node1.AddNeighbouringNode(node2);
            }
        }
    }

}

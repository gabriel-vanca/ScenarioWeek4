import DataStructures.Coordinates;
import DataStructures.Node;

import java.awt.geom.Line2D;
import java.util.ArrayList;

/**
 * Created by Gabriel on 21/02/2017.
 */
public class Graph {

    public static Graph instance = null;

    public ArrayList<Node> nodesList;

    private Graph() {
        nodesList = new ArrayList<>();
    }

    public static Graph GetInstance() {
        if (instance == null)
            instance = new Graph();
        return instance;
    }

    public void AddNode(Node node) {
        nodesList.add(node);
    }


    public void findLineToRobots(DataStructures.Robot startNode) {
        Map map = Map.GetInstance();


        for (int i = 1; i < nodesList.size(); i++) {
            Boolean isObstructed = false;
            Coordinates startNodeCoordinates = startNode.GetCoordinates();
            Coordinates targetNodeCoordinates = nodesList.get(i).GetCoordinates();

            Line2D line = new Line2D.Double(startNodeCoordinates.x, startNodeCoordinates.y,
                    targetNodeCoordinates.x, targetNodeCoordinates.y);

            for (int j = 0; j < map.obstaclesList.size(); j++) {

                // If line intersects line, find away around obstacle.
                if (line.intersectsLine(map.obstaclesList.get(j))) {
                    isObstructed = true;
                }
            }

            // If line to robot is unobstructed, then create an edge between the two robots.


        }


    }
}

import DataStructures.Map;
import DataStructures.Node;
import DataStructures.Obstacle;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Map map = new Map();

        map.loadMapDataFromLine(1);
        map.createEdge();

        ArrayList<Node> nodesList;
        ArrayList<Obstacle> obstaclesList;

        nodesList = map.nodesList;
        obstaclesList = map.obstaclesList;

    }

}
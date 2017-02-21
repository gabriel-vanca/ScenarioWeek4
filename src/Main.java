import DataStructures.Map;
import DataStructures.Obstacle;
import DataStructures.Robot;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Map map = new Map();

        map.LoadMapDataFromLine(1);
        map.createEdges();

        ArrayList<Robot> RobotsList;
        ArrayList<Obstacle> obstaclesList;

        RobotsList = map.RobotsList;
        obstaclesList = map.obstaclesList;

    }

}

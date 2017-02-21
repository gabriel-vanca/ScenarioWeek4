import DataStructures.Map;
import DataStructures.Node;
import DataStructures.Obstacle;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Map map = new Map();

        map.loadMapDataFromLine(2);
        map.createEdge();

        System.out.println("TEST");

    }

}
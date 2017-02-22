import DataStructures.Node;
import FileIO.InputReader;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        InputReader inputReader = InputReader.GetInstance();
        Map map = Map.GetInstance();
        Graph graph = Graph.GetInstance();
        Pathfinder pathfinder = new Pathfinder();

        //start test purposes
        map.LoadMapDataFromLine(1);
        graph.ConstructGraph();

        Node startNode = null;
        Node targetNode = null;
        int count = 0;
        for (Node node : graph.nodesList) {
            if (node.robotsAtThisNode.isEmpty()) {
                continue;
            }

            if (count == 0) {
                startNode = node;
                count++;
            } else {
                targetNode = node;
                break;
            }


        }

        ArrayList<Node> path = pathfinder.findShortestPathAStar(startNode, targetNode);
        //end test purposes



        int numberOfLines = inputReader.GetNumberOfLine();

        System.out.println("Number of nodes: " + graph.nodesList.size());

        for (int currentLine = 1; currentLine <= numberOfLines; currentLine++) {

            map.LoadMapDataFromLine(currentLine);

        }

    }
}
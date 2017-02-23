package com.frappe.main;

import DataStructures.Coordinates;
import DataStructures.Edge;
import DataStructures.Node;

import java.util.*;
import java.util.Map;

/**
 * Created by kelv on 22/02/17.Your branch is ahead of 'origin/kelvin' by
 */
public class Pathfinder {

    public Path findShortestPathAStar(Node startNode, Node targetNode) {
        // Get initial state of the graph.
        Graph graph = Graph.GetInstance();

        // Set of evaluated nodes
        ArrayList<Node> closedSet = new ArrayList<>();

        // Set of discovered nodes not yet evaluated
        ArrayList<Node> openSet = new ArrayList<>();
        openSet.add(startNode);

//        ArrayList<Node> cameFrom = new ArrayList<>();
        HashMap<Node, Node> cameFrom = new HashMap<>();


        // com.frappe.main.Map containing default value of Inf.
        // Add all nodes into here.
        HashMap<Node, Double> gScore = new HashMap<>();

        for (Node node : graph.nodesList) {
            gScore.put(node, Double.MAX_VALUE);
        }

        // Set value of start to 0
        gScore.replace(startNode, 0.0);


        // The total cost of getting to the targetNode from startNode, passing this node
        HashMap<Node, Double> fScore = new HashMap<>();
        for (Node node : graph.nodesList) {
            fScore.put(node, Double.MAX_VALUE);
        }

        fScore.replace(startNode, heuristicCostEstimate(startNode, targetNode));

        while (!openSet.isEmpty()) {
//            Node current;
            Iterator entries = fScore.entrySet().iterator();
            Node current = null;
            double lowestValue = Double.MAX_VALUE;


            while (entries.hasNext()) {
                Map.Entry<Node, Double> entry = (Map.Entry) entries.next();
                Node node = entry.getKey();
                double value = entry.getValue();

                if (value < lowestValue && openSet.contains(node)) {
                    current = node;
                    lowestValue = value;
                }
            }

            if (current == targetNode) {
                ArrayList<Node> pathList = reconstructPath(cameFrom, current);
                Double length = calculateLengthOfPath(pathList);

                return new Path(pathList, length);
            }

            openSet.remove(current);
            closedSet.add(current);

            for (Edge edge : current.edgesList) {
                Node sourceNode = edge.node1;
                Node neighbour = edge.node2;

                if (closedSet.contains(neighbour)) {
                    continue;
                }
                double tentativeGScore = gScore.get(current) + distanceBetween(current, neighbour);

                if (!openSet.contains(neighbour)) {
                    openSet.add(neighbour);
                } else if (tentativeGScore >= gScore.get(neighbour)) {
                    continue;
                }

                // Save best score.
//                cameFrom
                cameFrom.put(neighbour, current);
                gScore.replace(neighbour, tentativeGScore);
                fScore.replace(neighbour, gScore.get(neighbour) + heuristicCostEstimate(neighbour, targetNode));

            }

        }
        return null;
    }

    public double calculateLengthOfPath(ArrayList<Node> path) {


        double distance = 0;

        for (int i = 1; i < path.size(); i++) {
            Coordinates startNodeCoord = path.get(i - 1).GetCoordinates();
            Coordinates targetNodeCoord = path.get(i).GetCoordinates();
            double xDist = targetNodeCoord.x - startNodeCoord.x;
            double yDist = targetNodeCoord.y - startNodeCoord.y;

            distance += Math.sqrt(xDist * xDist + yDist * yDist);

        }

        return distance;
    }


    //    private void reconstructPath(ArrayList<Node> cameFrom, Node current) {
    private ArrayList<Node> reconstructPath(HashMap<Node, Node> cameFrom, Node current) {
        ArrayList<Node> totalPath = new ArrayList<>();
        totalPath.add(current);

        while (cameFrom.containsKey(current)) {
            current = cameFrom.get(current);
            totalPath.add(current);
        }
        Collections.reverse(totalPath);

        return totalPath;
    }

    private double heuristicCostEstimate(Node startNode, Node targetNode) {
        Coordinates p1 = startNode.GetCoordinates();
        Coordinates p2 = targetNode.GetCoordinates();

        double xDist = Math.abs(p2.x - p1.x);
        double yDist = Math.abs(p2.y - p1.y);

        return xDist + yDist;
//        return 0;
    }


    private double distanceBetween(Node startNode, Node targetNode) {
        Coordinates p1 = startNode.GetCoordinates();
        Coordinates p2 = targetNode.GetCoordinates();

        double xDist = p2.x - p1.x;
        double yDist = p2.y - p1.y;


        return Math.sqrt(xDist * xDist + yDist * yDist);
    }

}

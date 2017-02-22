import DataStructures.Edge;
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

    public void ConstructGraph()
    {
        Map map = Map.GetInstance();

        for (Node currentNode: nodesList)
        {
            for(Node neighbourNode: nodesList)
            {
                if(currentNode.equals(neighbourNode))
                    continue;

                Line2D currentLine = new Line2D.Double(currentNode.GetCoordinates().x,
                        currentNode.GetCoordinates().y,
                        neighbourNode.GetCoordinates().x,
                        neighbourNode.GetCoordinates().y);
                boolean intersects = false;

                for(Line2D obstacle:map.obstaclesList)
                {
                    if(currentLine.intersectsLine(obstacle) &&
                            !obstacle.contains(neighbourNode.GetCoordinates().x,
                                    neighbourNode.GetCoordinates().y))
                    {
                        intersects = true;
                        break;
                    }
                }

                if(intersects)
                    continue;

                Edge newEdge = new Edge(currentNode, neighbourNode);
                currentNode.edgesList.add(newEdge);
            }


        }

    }


//    public void findLineToRobots(Node startNode, Node targetNode) {
//        Map map = Map.GetInstance();
//
//        // Iterate through the list of nodes, drawing a straight line to every Node that we can,
//        // then for the ones we can't find a way around.
////        for (int i = 1; i < nodesList.size(); i++) {
//
//        Boolean isObstructed = false;
//
//        // Get the startNode coords, and the targetNode coords, then create a Line2D between these two points.
//        Coordinates startNodeCoordinates = startNode.GetCoordinates();
////            Coordinates targetNodeCoordinates = nodesList.get(i).GetCoordinates();
//        Coordinates targetNodeCoordinates = targetNode.GetCoordinates();
//
//        Line2D line = new Line2D.Double(startNodeCoordinates.x, startNodeCoordinates.y,
//                targetNodeCoordinates.x, targetNodeCoordinates.y);
//
//        // Check the Line2D against all obstacles.
//        int tempX;
//        int tempY;
//        double distToLine = Double.MAX_VALUE;
//        ArrayList<Line2D> closestLines = new ArrayList<>();
//
//        for (int j = 0; j < map.obstaclesList.size(); j++) {
//
//            Line2D obstacle = map.obstaclesList.get(j);
//
//            // If line intersects line in obstacleList, find away around obstacle.
//            if (line.intersectsLine(obstacle)) {
//
//
//                double distFromStartNode = obstacle.ptLineDist(startNodeCoordinates.x, startNodeCoordinates.y);
//                isObstructed = true;
//
//                // At most, a corner of a Line2D will coincide with the corner of another Line2D. We need
//                // to find the closest two Line2Ds.
//
//                if (distFromStartNode < distToLine) {
//
//                    // Update the closest line distance, then replace the stored Line2D with the new Line2D.
//
//                    distToLine = distFromStartNode;
//
//                    // If no Line2D stored yet, simply add the Line2D.
//                    if (closestLines.size() == 0) {
//                        closestLines.add(obstacle);
//                    } else {
//
//                        // Clear old lines, add new Line.
//                        closestLines.clear();
//                        closestLines.add(obstacle);
//                    }
//
//                } else if (distFromStartNode == distToLine) {
//
//                    // In this case, we have found another Line2D which is the same distance from an
//                    // already stored Line2D.
//
//                    closestLines.add(obstacle);
//
//
//                }
//
//            }
//        }
//
//        // If line to robot is unobstructed, then create an edge between the two robots.
//        if (!isObstructed) {
//            // Add node to list.
//            AddNode(new Node(targetNodeCoordinates.x, targetNodeCoordinates.y));
//        } else {
//            // Now we need to continue finding a path around the obstacle.
//            // We will recurse starting from both ends of a line.
//
//            // Create Node at both ends of line.
//            Node node1 = new Node(closestLines.get(0).getX1(), closestLines.get(0).getY1());
//            Node node2 = new Node(closestLines.get(0).getX2(), closestLines.get(0).getY2());
//
//            findLineToRobots(node1, targetNode);
//            findLineToRobots(node2, targetNode);
//        }
//
//
//    }
}

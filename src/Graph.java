import DataStructures.Edge;
import DataStructures.Node;
import DataStructures.Obstacle;

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

    private Line2D buildLineBetweenNodes(Node node1, Node node2) {
        return new Line2D.Double(node1.GetCoordinates().x,
                node1.GetCoordinates().y,
                node2.GetCoordinates().x,
                node2.GetCoordinates().y);
    }

    private void buildEdgeBetweenNodes(Node node1, Node node2, Line2D line2D)
    {
        Edge newEdge = new Edge(node1, node2, line2D);
        node1.edgesList.add(newEdge);
        newEdge = new Edge(node2, node1, line2D);
        node2.edgesList.add(newEdge);
    }

    public void ConstructGraph() {
        Map map = Map.GetInstance();


        for (int i=0; i<nodesList.size();i++) {
            Node currentNode = nodesList.get(i);
            for (int j = i + 1; j < nodesList.size(); j++) {
                Node alternativeNode = nodesList.get(j);

                if (currentNode.ParentObstacle != null
                        &&
                        currentNode.ParentObstacle == alternativeNode.ParentObstacle)
                    continue;

                Line2D newLine = buildLineBetweenNodes(currentNode, alternativeNode);

                if (!isIntersectingAnyObstacle(newLine)) {
                    buildEdgeBetweenNodes(currentNode, alternativeNode, newLine);
                }
            }
        }


      /*  for (int i = 0; i < map.obstaclesList.size(); i++) {
            Obstacle currentObstacle = map.obstaclesList.get(i);

            for (int j = i + 1; j < map.obstaclesList.size(); j++) {
                Obstacle alternativeObstacle = map.obstaclesList.get(j);

                for (Node currentNode : currentObstacle.verticesList) {
                    for (Node alternativeNode : alternativeObstacle.verticesList) {
                        Line2D newLine = buildLineBetweenNodes(currentNode, alternativeNode);

                        if (!isIntersectingAnyObstacle(newLine)) {
                            buildEdgeBetweenNodes(currentNode, alternativeNode);
                        }
                    }
                }
            }
        }

        for (Node currentRobotNode : nodesList) {
            if (currentRobotNode.robotsAtThisNode.isEmpty())
                continue;

            for (Obstacle currentObstacle : map.obstaclesList) {
                for (Node currentObstacleNode : currentObstacle.verticesList) {
                    Line2D newLine = buildLineBetweenNodes(currentObstacleNode, currentRobotNode);

                    if (!isIntersectingAnyObstacle(newLine)) {
                        buildEdgeBetweenNodes(currentObstacleNode, currentRobotNode);
                    }
                }
            }
        } */
    }

              /*  for (Node neighbourNode : nodesList) {
                    if (currentNode.equals(neighbourNode))
                        continue;

                    Line2D currentLine = new Line2D.Double(currentNode.GetCoordinates().x,
                            currentNode.GetCoordinates().y,
                            neighbourNode.GetCoordinates().x,
                            neighbourNode.GetCoordinates().y);
                    boolean intersects = false;

                    for (Line2D obstacle : map.obstaclesList) {
                        if (currentLine.intersectsLine(obstacle) &&
                                !obstacle.contains(neighbourNode.GetCoordinates().x,
                                        neighbourNode.GetCoordinates().y)) {
                            intersects = true;
                            break;
                        }
                    }

                    if (intersects)
                        continue;

                    Edge newEdge = new Edge(currentNode, neighbourNode);
                    currentNode.edgesList.add(newEdge);
                }

            }
        }
    }*/

    private Boolean isIntersectingAnyObstacle(Line2D currentLine) {
        Map map = Map.GetInstance();

        for (Obstacle currentObstacle : map.obstaclesList) {
            for (Line2D currentObstacleEdge : currentObstacle.edgesList) {
                if (currentLine.getP1().distance(currentObstacleEdge.getP1()) == 0
                        ||
                        currentLine.getP1().distance(currentObstacleEdge.getP2()) == 0
                        ||
                        currentLine.getP2().distance(currentObstacleEdge.getP1()) == 0
                        ||
                        currentLine.getP2().distance(currentObstacleEdge.getP2()) == 0)
                    continue;
                if (currentLine.intersectsLine(currentObstacleEdge))
                    return true;
            }
        }
        return false;
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

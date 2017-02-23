package Solutions;

import DataStructures.Node;
import DataStructures.Robot;
import com.frappe.main.Graph;
import com.frappe.main.Path;
import com.frappe.main.Pathfinder;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

/**
 * Created by kelv on 23/02/17.
 */
public class ExplosivePathing {

    Graph graph;
    ArrayList<Node> nodes;
    Pathfinder pathfinder;


    public ExplosivePathing() {
        this.graph = Graph.GetInstance();
        this.nodes = graph.nodesList;
        this.pathfinder = new Pathfinder();

    }

    private boolean containsSleepingRobot(ArrayList<Robot> robots) {

        for (Robot robot : robots) {
            if (robot.isAwake() == false) {
                return true;
            }
        }
        return false;
    }

    private ArrayList<ArrayList<Node>> sortAscLength(ArrayList<ArrayList<Node>> paths) {
        ArrayList<ArrayList<Node>> newList = new ArrayList<>();
        double shortestLength = Double.MAX_VALUE;
        ArrayList<Node> shortestPath = null;

        while (!paths.isEmpty()) {
            for (ArrayList<Node> path : paths) {

                double thisLength = pathfinder.calculateLengthOfPath(path);

                if (thisLength < shortestLength) {
                    shortestLength = thisLength;
                    shortestPath = path;
                }
            }

            newList.add(shortestPath);
            paths.remove(shortestPath);
        }

        return newList;
    }

    private ArrayList<Robot> buildRobotList() {
        ArrayList<Robot> robots = new ArrayList<>();

        for (Node node : this.nodes) {

            int numberOfRobots = node.robotsAtThisNode.size();

            if (numberOfRobots > 0) {
                for (int i = 0; i < numberOfRobots; i++) {
                    robots.add(node.robotsAtThisNode.get(i));
                }
            }
        }

        return robots;
    }

    public ArrayList<Path> generateSolution() {

        ArrayList<Node> visited = new ArrayList<>();
        ArrayList<Node> nodesWithRobots = new ArrayList<>();
        ArrayList<Node> nodesWithActiveRobots = new ArrayList<>();
        ArrayList<Robot> robotList = new ArrayList<>();
        ArrayList<Robot> sleepingRobotsList = buildRobotList();
        ArrayList<Node> sleepingNodeList = new ArrayList<>();
        ArrayList<Path> allPaths = new ArrayList<>();

        int order = 0;


        // First, populate the array with Nodes with robots.
        for (Node node : this.nodes) {
            if (node.robotsAtThisNode.size() == 1) {
                nodesWithRobots.add(node);
                sleepingNodeList.add(node);
            }
        }

        nodesWithActiveRobots.add(nodesWithRobots.get(0));
        nodesWithActiveRobots.get(0).wakeRobots();

        sleepingNodeList.remove(nodesWithActiveRobots.get(0));


        // While we still have sleeping robots, keep looping to try to wake it.
        int orderCount = 0;
        while (!sleepingNodeList.isEmpty()) {
            ArrayList<Path> calculatedPaths = new ArrayList<>();

            // Get first node with an active robot.
            Node currentNode = nodesWithActiveRobots.get(0);
//            nodesWithActiveRobots.remove(currentNode);

            currentNode.wakeRobots();

            // 1) Pick cloest node to currentNode, call it destNode.
            // 2) From destNode, check distance to every node with active robots, pick the closest and move robot to
            //    destNode.

            // Iterate through all nodes with sleeping robots.
            for (Node node : nodesWithRobots) {
                if (currentNode == node) {
                    continue;
                }
                // If robot is sleeping, find a path to it
                if (node.robotsAtThisNode.size() == 1 && !node.robotsAtThisNode.get(0).isAwake()) {
                    Path pathToNode = pathfinder.findShortestPathAStar(currentNode, node);
                    calculatedPaths.add(pathToNode);
                }

            }


            // Sort list of possible paths for this node
//            calculatedPaths = sortAscLength(calculatedPaths);
            Collections.sort(calculatedPaths, new Comparator<Path>() {
                @Override
                public int compare(Path o1, Path o2) {
                    return o1.getLength().compareTo(o2.getLength());
                }
            });

            Path shortestPath = calculatedPaths.get(0);
            Node destNode = shortestPath.getDestination();

            // Now find path from destNode to all nodes with woken robots
            ArrayList<Path> calculatedPathsToActiveRobotNodes = new ArrayList<>();

            for (Node node : nodesWithActiveRobots) {
                Path pathToNode = pathfinder.findShortestPathAStar(destNode, node);
                calculatedPathsToActiveRobotNodes.add(pathToNode);
            }

            // Arrange in order
            Collections.sort(calculatedPathsToActiveRobotNodes, new Comparator<Path>() {
                @Override
                public int compare(Path o1, Path o2) {
                    return o1.getLength().compareTo(o2.getLength());
                }
            });

            // Move robot from the closest Node with active robot to destNode
            Path shortestPathBackToActiveNode = calculatedPathsToActiveRobotNodes.get(0);


            Node originNode = shortestPathBackToActiveNode.getDestination();
            Collections.reverse(shortestPathBackToActiveNode.getPath());
            // Reverse the shortestPathBackToActiveNode to get the right direction of movement.


            // Check if robot to be moved has been moved before or not.
            if (originNode.robotsAtThisNode.get(0).getOrder() == -1) {
                originNode.robotsAtThisNode.get(0).setOrder(orderCount);
                robotList.add(originNode.robotsAtThisNode.get(0));

                orderCount++;
            }
            originNode.moveAnyRobot(destNode, shortestPathBackToActiveNode);
            if (originNode.robotsAtThisNode.size() == 0) {
                nodesWithActiveRobots.remove(originNode);
            }
            // Remove node from sleeping Node list and wake robots on that node.
            sleepingNodeList.remove(destNode);
            nodesWithActiveRobots.add(destNode);
            destNode.wakeRobots();


        }
        int count = 0;
        while (!robotList.isEmpty()) {
            for (Robot robot : robotList) {
                if (robot.getOrder() == count) {
                    allPaths.add(robot.getPathTraversed());
                    count++;
                    robotList.remove(robot);
                    break;
                }
            }
        }

        return allPaths;
    }

}
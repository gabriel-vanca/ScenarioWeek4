package Solutions;

import DataStructures.Node;
import DataStructures.Robot;
import com.frappe.main.Graph;
import com.frappe.main.Pathfinder;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
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

    public ArrayList<ArrayList<Node>> generateSolution() {

        ArrayList<Node> visited = new ArrayList<>();
        ArrayList<Node> nodesWithRobots = new ArrayList<>();
        ArrayList<Robot> robotList = buildRobotList();
        ArrayList<Robot> sleepingRobotsList = buildRobotList();
        ArrayList<ArrayList<Node>> allPaths = new ArrayList<>();

        int order = 0;


        // First, populate the array of Nodes with robots.

        for (Node node : this.nodes) {
            if (node.robotsAtThisNode.size() == 1) {
                nodesWithRobots.add(node);
            }
        }


        // While we still have sleeping robots, keep looping to try to wake it.
        while (!sleepingRobotsList.isEmpty()) {

            // Get first node with an active robot.
            Node currentNode = nodesWithRobots.get(0);
            nodesWithRobots.remove(currentNode);


            ArrayList<ArrayList<Node>> calculatedPaths = new ArrayList<>();

            // Iterate through all nodes with robots.
            for (Node node : nodesWithRobots) {

                // If robot is sleeping, find a path to it

                ArrayList<Node> pathToNode = pathfinder.findShortestPathAStar(currentNode, node);
                calculatedPaths.add(pathToNode);

            }


            // Sort list of possible paths for this node
            calculatedPaths = sortAscLength(calculatedPaths);


            // Select the shortest path for each active robot
            for (Robot robot : currentNode.robotsAtThisNode) {

                ArrayList<Node> shortestPath;

                // If robot is awake, move it down one of the shortest paths.
                if (!calculatedPaths.isEmpty()) {
                    shortestPath = calculatedPaths.get(0);
                    calculatedPaths.remove(shortestPath);

                    if (robot.isAwake()) {
                        Node destNode = shortestPath.get(shortestPath.size() - 1);

                        // Set robot order and increment
                        if (robot.getOrder() == -1) {
                            robot.setOrder(order);
                            order++;
                        }

                        currentNode.moveRobot(robot, destNode);
                        robot.addPath(shortestPath);
                        destNode.wakeRobots();

                        sleepingRobotsList.removeAll(destNode.robotsAtThisNode);

                    }
                }

            }

        }

        while (!robotList.isEmpty()) {
            int count = 0;

            for (Robot robot: robotList) {
                if (robot.getOrder() == count ) {
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
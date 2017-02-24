package Solutions;

import DataStructures.Node;
import DataStructures.Robot;
import com.frappe.main.Graph;
import com.frappe.main.Pathfinder;

import java.util.ArrayList;

/**
 * Created by Andreas Zinoadnos on 23/02/17.
 */
public class Advanced {

    public Advanced() {

        final double MAX = 999999999;
        Graph graph = Graph.GetInstance();
        ArrayList<Node> nodesWithRobots = new ArrayList<>();
        ArrayList<Node> nodesWithAwakeRobots = new ArrayList<>();
        Pathfinder pathfinder = new Pathfinder();
        StringBuilder sb = new StringBuilder();
        ArrayList<Node> bestPathToFollow = new ArrayList<>();
        double shortestLength = MAX;
        int counter = 1;
        int pendingRobots;

        ArrayList<Node> solutionNodes = new ArrayList<>();

        for (Node n : graph.nodesList) {
            if (!n.robotsAtThisNode.isEmpty()) {
                nodesWithRobots.add(n);
            }
        }

        pendingRobots = nodesWithRobots.size();
        boolean foundNode = false;

        // Wake up first robot
        nodesWithRobots.get(0).robotsAtThisNode.get(0).setIsAwake(true);
        nodesWithAwakeRobots.add(nodesWithRobots.get(0));
        pendingRobots--;

        // Iterate throught the loop of nodes
        while (pendingRobots > 0){
            // For all robots in the nodesWithAwakeRobots list
            for (int i = 0; i < counter; i++){
                for (int j = 0; j < nodesWithAwakeRobots.get(i).robotsAtThisNode.size(); j++){
                    for (int k = 0; k < nodesWithRobots.size(); k++) {
//                        int noOfRobotsAtNewNode = nodesWithRobots.get(k).robotsAtThisNode.size();

                        // If the two node references aren't the same node and the robots at that node are NOT awake
                        if (!nodesWithAwakeRobots.contains(nodesWithRobots.get(k))){// && (noOfRobotsAtNewNode > 0 && nodesWithRobots.get(k).robotsAtThisNode.get(0).getIsAwake() == false)) {
                            foundNode = true;
                            ArrayList<Node> temp = pathfinder.findShortestPathAStar(nodesWithAwakeRobots.get(i), nodesWithRobots.get(k));
                            double newLength = pathfinder.calculateLengthOfPath(temp);
                            // If this is the new shortest path
                            if (newLength < shortestLength) {
                                bestPathToFollow.clear();
                                bestPathToFollow.addAll(temp);
                                shortestLength = newLength;
                            }
                        }
                    }

                    if (foundNode) {

                        // Add the path in the solutions
                        solutionNodes.addAll(bestPathToFollow);
                        // Add to stringbuilder
                        for (Node n : bestPathToFollow) {
                            sb.append("(" + n.GetCoordinates().x + ", " + n.GetCoordinates().y + ")");
                            if (n == bestPathToFollow.get(bestPathToFollow.size()-1)){
                                sb.append("; ");
                            } else {
                                sb.append(", ");
                            }
                        }

                        // Move the robot to the new node and add the new node to nodes with awake robots
                        bestPathToFollow.get(bestPathToFollow.size() - 1).robotsAtThisNode.get(0).setIsAwake(true);
                        bestPathToFollow.get(bestPathToFollow.size() - 1).robotsAtThisNode.add(nodesWithAwakeRobots.get(i).robotsAtThisNode.get(j));
                        nodesWithAwakeRobots.get(i).robotsAtThisNode.remove(j);
                        nodesWithAwakeRobots.add(bestPathToFollow.get(bestPathToFollow.size() - 1));
                        pendingRobots--;
                    }

                    // Reset length of shortest path and clear best path array
                    shortestLength = MAX;
                    bestPathToFollow.clear();
                    foundNode = false;

                    // If the old node is empty, remove it
                    for (int x = 0; x < nodesWithAwakeRobots.size(); x++) {
                        if (nodesWithAwakeRobots.get(x).robotsAtThisNode.size() == 0) {
                            nodesWithAwakeRobots.remove(x);
                            nodesWithRobots.remove(x);
                            x--;
                        }
                    }
                }

            }
            counter = nodesWithAwakeRobots.size();
        }

        // Formatting function
//        for (Node n : solutionNodes) {
//            sb.append("(" + n.GetCoordinates().x + ", " + n.GetCoordinates().y + ") , ");
//        }

        for (int i = 1; i <= 2; i++)
            sb.deleteCharAt(sb.length()-1);
        System.out.println(sb.toString());


    }

}

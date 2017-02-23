package Solutions;

import DataStructures.Node;
import DataStructures.Robot;
import com.frappe.main.Graph;
import com.frappe.main.Pathfinder;

import java.util.ArrayList;

/**
 * Created by Andreas Zinonos on 23/02/17.
 */
public class Advanced {

    public Advanced() {

        Graph graph = Graph.GetInstance();
        ArrayList<Node> nodesWithRobots = new ArrayList<>();
        ArrayList<Robot> awakeRobots = new ArrayList<>();
        Pathfinder pathfinder = new Pathfinder();
        StringBuilder sb = new StringBuilder();

        ArrayList<Node> solutionNodes = new ArrayList<>();

        for (Node n : graph.nodesList) {
            if (!n.robotsAtThisNode.isEmpty()) {
                nodesWithRobots.add(n);
            }
        }

        // Wake up first robot
        nodesWithRobots.get(0).robotsAtThisNode.get(0).setIsAwake(true);
        awakeRobots.add(nodesWithRobots.get(0).robotsAtThisNode.get(0));

        // Iterate throught the loop of nodes
        while (true){
            for (int i = 0; i < awakeRobots.size(); i++){
                ArrayList<Node> temp;
            }
        }

//        for (int i = 0; i < nodesWithRobots.size() - 1; i++) {
//            if (!solutionNodes.isEmpty()) {
//                solutionNodes.remove(solutionNodes.size() - 1);
//            }
//
//            ArrayList<Node> temp = pathfinder.findShortestPathAStar(nodesWithRobots.get(i), nodesWithRobots.get(i + 1));
//
//            for (Node tempV : temp) {
//                solutionNodes.add(tempV);
//            }
//
//        }

        // Formatting function
        for (Node n : solutionNodes) {
            sb.append("(" + n.GetCoordinates().x + ", " + n.GetCoordinates().y + ") , ");
        }

        for (int i = 1; i <= 3; i++)
            sb.deleteCharAt(sb.length()-1);
        System.out.println(sb.toString());


    }

}

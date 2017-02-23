package Solutions;

import DataStructures.Node;
import com.frappe.main.Graph;
import com.frappe.main.Pathfinder;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by bad guy kelv on 22/02/17.
 */
public class Basic {

    public Basic() {

        Graph graph = Graph.GetInstance();
        ArrayList<Node> nodesWithRobots = new ArrayList<>();
        Pathfinder pathfinder = new Pathfinder();
        StringBuilder sb = new StringBuilder();

        ArrayList<Node> solutionNodes = new ArrayList<>();

        for (Node n : graph.nodesList) {
            if (!n.robotsAtThisNode.isEmpty()) {
                nodesWithRobots.add(n);
            }
        }

        for (int i = 0; i < nodesWithRobots.size() - 1; i++) {
            if (!solutionNodes.isEmpty()) {
                solutionNodes.remove(solutionNodes.size() - 1);
            }
            //solutionNodes.addAll

            ArrayList<Node> temp = pathfinder.findShortestPathAStar(nodesWithRobots.get(i), nodesWithRobots.get(i + 1));

            for (Node tempV : temp) {
                solutionNodes.add(tempV);
            }

        }


        for (Node n : solutionNodes) {
            sb.append("(" + n.GetCoordinates().x + ", " + n.GetCoordinates().y + ") , ");
        }

        for (int i = 1; i <= 3; i++)
            sb.deleteCharAt(sb.length()-1<);
        System.out.println(sb.toString());


    }
}

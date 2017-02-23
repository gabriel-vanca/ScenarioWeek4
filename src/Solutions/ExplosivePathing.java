package Solutions;

import DataStructures.Node;
import DataStructures.Robot;
import com.frappe.main.Graph;

import java.util.ArrayList;

/**
 * Created by kelv on 23/02/17.
 */
public class ExplosivePathing {

    Graph graph;
    ArrayList<Node> nodes;

    public ExplosivePathing() {
        this.graph = Graph.GetInstance();
        this.nodes = graph.nodesList;
    }

    public void generateSolution() {
        Node currentNode = this.nodes.get(0);
        ArrayList<Node> visited = new ArrayList<>();

        for (Robot robot: currentNode.robotsAtThisNode) {

        }

    }


}

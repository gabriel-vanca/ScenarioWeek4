package DataStructures;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 * Created by Andreas Zinonos on 20/02/17.
 */
public class Edge {

    public Node node1 = null;
    public Node node2 = null;
    public double weight;

    public Edge(Node node1, Node node2) {
        this.node1 = node1;
        this.node2 = node2;
        calculateWeight();
    }

      public void calculateWeight() {

        if(node1.robotsAtThisNode.isEmpty() || node2.robotsAtThisNode.isEmpty())
            return;

        Robot robot1 = node1.robotsAtThisNode.get(0);
        Robot robot2 = node2.robotsAtThisNode.get(0);

        // Formula to calculate the distance between two vertices
        weight = sqrt(
                pow(robot2.GetCoordinates().x - robot1.GetCoordinates().x, 2)
                        +
                        pow(robot2.GetCoordinates().y - robot1.GetCoordinates().y, 2));
    }
}

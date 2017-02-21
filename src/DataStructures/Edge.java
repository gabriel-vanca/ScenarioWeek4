package DataStructures;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 * Created by Andreas Zinonos on 20/02/17.
 */
public class Edge {

    private Node node1;
    private Node node2;
    private double weight;

    public Edge(Node node1, Node node2) {
        this.node1 = node1;
        this.node2 = node2;

        // Formula to calculate the distance between two vertices
        weight = sqrt(
                pow(node2.getXPos() - node1.getXPos(), 2)
                        +
                        pow(node2.getYPos() - node1.getYPos(), 2));
    }

    public double getWeight() {
        return weight;
    }

}

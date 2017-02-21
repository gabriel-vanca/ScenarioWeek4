package DataStructures;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 * Created by Andreas Zinonos on 20/02/17.
 */
public class Edge {

    private Node node1;
    private Node Node2;
    private double weight;

    public Edge(Node node1, Node Node2) {
        this.node1 = node1;
        this.Node2 = Node2;

        // Formula to calculate the distance between two vertices
        weight = sqrt(
                pow(Node2.getCoordinates().x - node1.getCoordinates().x, 2)
                        +
                        pow(Node2.getCoordinates().y - node1.getCoordinates().y, 2));
    }

    public double getWeight() {
        return weight;
    }

}

package DataStructures;

import java.awt.geom.Line2D;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 * Created by Andreas Zinonos on 20/02/17.
 */
public class Edge extends Line2D.Double {

    public Node node1 = null;
    public Node node2 = null;
    public double weight;

    public Edge(Node node1, Node node2, Line2D line2D) {
        super(line2D.getP1(), line2D.getP2());
        this.node1 = node1;
        this.node2 = node2;
        calculateWeight();
    }

      public void calculateWeight() {

        // Formula to calculate the distance between two vertices
        weight = sqrt(
                pow(node2.GetCoordinates().x - node1.GetCoordinates().x, 2)
                        +
                        pow(node2.GetCoordinates().y - node1.GetCoordinates().y, 2));
    }
}

package DataStructures;

import java.awt.geom.Line2D;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Gabriel on 20/02/2017.
 */
public class Obstacle {
    public ArrayList<Line2D> Lines;

    public Obstacle()
    {
        Lines = new ArrayList<>();
    }

    public ArrayList<Node> getNodes(){

        ArrayList<Node> nodesList = new ArrayList<>();
        boolean isFirstLine = true;

        // Find nodes
        for (Line2D l: Lines){

            double x1 = l.getX1();
            double y1 = l.getY1();
            double x2 = l.getX2();
            double y2 = l.getY2();

            Node node1 = new Node(x1, y1);
            Node node2 = new Node(x2, y2);

            nodesList.add(node2);

            if (isFirstLine) {
                nodesList.add(node1);
                isFirstLine = false;
            }
        }
        return nodesList;
    }
}

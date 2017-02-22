package DataStructures;
import java.awt.geom.Line2D;
import java.util.ArrayList;

/**
 * Created by Lambros on 21/02/2017.
 */
public class Obstacle {

    public ArrayList<Line2D> edgesList = new ArrayList<>();

    public Obstacle(ArrayList<Line2D> edgesList) {
        this.edgesList = edgesList;
    }
}

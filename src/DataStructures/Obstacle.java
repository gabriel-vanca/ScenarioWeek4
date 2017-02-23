package DataStructures;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.util.ArrayList;

/**
 * Created by Lambros on 21/02/2017.
 */
public class Obstacle {

    public ArrayList<Node> verticesList = new ArrayList<>();
    public ArrayList<Line2D> edgesList = new ArrayList<>();
    public Path2D.Double polygonShape = new Path2D.Double();

    public Obstacle(ArrayList<Node> verticesList, ArrayList<Line2D> edgesList) {
        this.verticesList = verticesList;
        this.edgesList = edgesList;
    }

//    public void ConstructShape()
//    {
//        polygonShape.moveTo(verticesList.get(0).GetCoordinates().x, verticesList.get(0).GetCoordinates().y);
//        for (int index=1; index<verticesList.size(); index++) {
//            Node vertex = verticesList.get(index);
//            polygonShape.lineTo(vertex.GetCoordinates().x, vertex.GetCoordinates().y);
//        }
//
//        polygonShape.closePath();
//    }
}

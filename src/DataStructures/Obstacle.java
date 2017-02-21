package DataStructures;

import java.awt.geom.Line2D;
import java.util.ArrayList;

/**
 * Created by Gabriel on 20/02/2017.
 */
public class Obstacle {
    private ArrayList<Line2D> edgesList;

    public Obstacle()
    {
        edgesList = new ArrayList<>();
    }

    public Line2D GetEdge(int index)
    {
        return edgesList.get(index);
    }

    public void AddEdge(Line2D newLine)
    {
        edgesList.add(newLine);
    }

    public void RemoveEdge(int index)
    {
        edgesList.remove(index);
    }

    public int GetNumberOfEdges()
    {
        return edgesList.size();
    }

}

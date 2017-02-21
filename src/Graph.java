import DataStructures.Node;

import java.util.ArrayList;

/**
 * Created by Gabriel on 21/02/2017.
 */
public class Graph{

    public static Graph instance = null;

    public ArrayList<Node> nodesList;

    private Graph()
    {
        nodesList = new ArrayList<>();
    }

    public static Graph GetInstance()
    {
        if(instance == null)
            instance = new Graph();
        return instance;
    }

    public void AddNode(Node node) {
        nodesList.add(node);
    }
}

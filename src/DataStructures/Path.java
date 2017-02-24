package DataStructures;

import java.util.ArrayList;

/**
 * Created by kelv on 23/02/17.
 */
public class Path {

    private ArrayList<Node> path;
    private Double length;

    public Path(ArrayList<Node> path, Double length) {
        this.path = path;
        this.length = length;
    }

    public ArrayList<Node> getPath() {
        return this.path;
    }

    public Double getLength() {
        return this.length;
    }

    public Node getDestination() {
        return this.path.get(this.path.size() - 1);
    }

    public void extendPath(ArrayList<Node> pathList) {
        this.path.addAll(pathList);
    }


}

package DataStructures;

import java.util.ArrayList;

/**
 * Created by Gabriel on 20/02/2017.
 */
public class Robot {
    private Coordinates robotPosition;
    private boolean isAwake;
    private int order;
    private ArrayList<Node> pathTraversed;

    public Robot(Coordinates coordinates) {
        SetCoordinates(coordinates);
        this.isAwake = false;
        this.pathTraversed = new ArrayList<>();
        this.order = -1;
    }

    public Coordinates GetCoordinates() {
        return robotPosition;
    }

    public void SetCoordinates(Coordinates newCoordinates) {
        robotPosition = newCoordinates;
    }

    public void wake() {
        this.isAwake = true;
    }

    public int getOrder() {
        return this.order;
    }

    public boolean isAwake() {
        return this.isAwake;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public void addPath(ArrayList<Node> path) {
        int pathTraversedSize = this.pathTraversed.size();


        if (this.pathTraversed.get(pathTraversedSize - 1) == path.get(0)) {
            path.remove(0);
        }

        this.pathTraversed.addAll(path);
    }

    public ArrayList<Node> getPathTraversed() {
        return this.pathTraversed;
    }
}

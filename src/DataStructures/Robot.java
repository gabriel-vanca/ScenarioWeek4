package DataStructures;

import com.frappe.main.Path;

import java.util.ArrayList;

/**
 * Created by Gabriel on 20/02/2017.
 */
public class Robot {
    private Coordinates robotPosition;
    private boolean isAwake;
    private int order;
    private Path pathTraversed;

    public Robot(Coordinates coordinates) {
        SetCoordinates(coordinates);
        this.isAwake = false;
        this.pathTraversed = null;
//        this.pathTraversed;
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

    public void addPath(Path path, Double length) {
//        int pathTraversedSize = this.pathTraversed.size();
        if (this.pathTraversed == null) {
            this.pathTraversed = new Path(path.getPath(), length);
        }

        if (this.pathTraversed.getDestination() == path.getPath().get(0)) {
            ArrayList<Node> additionalPath = new ArrayList<>(path.getPath());
            additionalPath.remove(0);
            this.pathTraversed.extendPath(additionalPath);
        }

    }

    public Path getPathTraversed() {
        return this.pathTraversed;
    }
}

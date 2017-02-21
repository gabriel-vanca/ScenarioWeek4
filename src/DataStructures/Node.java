package DataStructures;

/**
 * Created by Andreas Zinonos on 21/02/17.
 */

public class Node {

    private double xPos;
    private double yPos;

    public Node(double xPos, double yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public double getXPos() {
        return xPos;
    }

    public void setXPos(double xPos) {
        this.xPos = xPos;
    }

    public double getYPos() {
        return yPos;
    }

    public void setYPos(double yPos) {
        this.yPos = yPos;
    }
}

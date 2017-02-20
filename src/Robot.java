import java.awt.geom.Line2D;

/**
 * Created by Lambros on 20/02/2017.
 */
public class Robot {

    double x;
    double y;
    boolean isAwake;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean isAwake() {
        return isAwake;
    }

    public void setIsAwake(boolean b) {
        this.isAwake = b;
    }
}
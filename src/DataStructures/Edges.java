package DataStructures;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 * Created by Andreas Zinonos on 20/02/17.
 */
public class Edges {

    private Robot robot1;
    private Robot robot2;
    private double weight;

    public Edges(Robot robot1, Robot robot2) {
        this.robot1 = robot1;
        this.robot2 = robot2;

        // Formula to calculate the distance between two vertices
        weight = sqrt(
                pow(robot2.GetCoordinates().x - robot1.GetCoordinates().x, 2)
                        +
                        pow(robot2.GetCoordinates().y - robot1.GetCoordinates().y, 2));
    }

    public double getWeight() {
        return weight;
    }

}

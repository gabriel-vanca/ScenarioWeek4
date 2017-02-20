import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Map map = new Map();
        try {
            map.ReadFile();
            map.LoadMapDataFromLine(1);
            map.createEdges();

            ArrayList<Robot> RobotsList;
            ArrayList<Polygon> PolygonsList;

            RobotsList = map.RobotsList;
            PolygonsList = map.PolygonsList;

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }
    }

}

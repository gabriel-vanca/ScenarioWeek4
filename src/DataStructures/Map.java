package DataStructures;

import FileIO.InputReader;
import UtilityObjects.NumberScanner;

import java.awt.geom.Line2D;
import java.util.ArrayList;

/**
 * Created by Gabriel on 20/02/2017.
 */

public class Map {

    //NOTE: CLASS SHOULD BE A SINGLETON AS WELL
    //NOTE: COMMENT THE CODE
    //NOTE: MOVE ANDREAS' CODE OUT FO THIS CLASS

    public ArrayList<Node> robotNodeList;
    public ArrayList<Node> allNodeList;
    public ArrayList<Edge> edgeList = new ArrayList<>();
    public ArrayList<Obstacle> obstaclesList;


    public void loadMapDataFromLine(int line)  // specify the line
    {
        InputReader inputFile = InputReader.getInstance();
        line--;
        boolean obstacles = inputFile.GetLine(line).contains("#");
        robotNodeList = new ArrayList<>();

        String robotsPositionsString;
        String polygonsDataString = null;

        if (obstacles) {
            String data[] = inputFile.GetLine(line).split("#");
            robotsPositionsString = data[0];
            polygonsDataString = data[1];
            data = null;
        } else {
            robotsPositionsString = inputFile.GetLine(line);
        }

        NumberScanner scannerObj = new NumberScanner(robotsPositionsString);

        scannerObj.getNextDouble();
        while (scannerObj.hasNextDouble()) {
            double x = scannerObj.getNextDouble();
            double y = scannerObj.getNextDouble();
            robotNodeList.add(new Node(x, y));
        }

        if (!obstacles)
            return;

        String[] polygonsDataStringSplit = polygonsDataString.split(";");
        int numberOfPolygons = polygonsDataStringSplit.length;
        obstaclesList = new ArrayList<>();

        for (int i = 0; i < numberOfPolygons; i++) {
            obstaclesList.add(new Obstacle());
            scannerObj = new NumberScanner(polygonsDataStringSplit[i]);
            while (scannerObj.hasNextDouble()) {
                double x = scannerObj.getNextDouble();
                double y = scannerObj.getNextDouble();

                if(!obstaclesList.get(i).Lines.isEmpty()) {
                    Line2D lastLine = obstaclesList.get(i).Lines.get(obstaclesList.get(i).Lines.size() - 1);
                    Line2D newLine = new Line2D.Double(lastLine.getX2(), lastLine.getY2(), x, y);
                    obstaclesList.get(i).Lines.add(newLine);
                }
                else
                {
                    Line2D newLine = new Line2D.Double(x,y,x,y);
                    obstaclesList.get(i).Lines.add(newLine);
                }
            }

            obstaclesList.get(i).Lines.remove(0);

        }
    }

    // Calling function to create Edge with current DataStructures.Robot List
    public void createEdge() {
        for (int i = 0; i < robotNodeList.size(); i++){
            for (int j = i + 1; j < robotNodeList.size(); j++){
                Edge newEdge = new Edge(robotNodeList.get(i), robotNodeList.get(j));
                edgeList.add(newEdge);
            }
        }
    }

    // Create allNodes list
    public void createAllNodesList(){

    }


}

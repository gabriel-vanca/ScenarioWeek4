import java.awt.geom.Line2D;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by Gabriel on 20/02/2017.
 */

public class Map {

    public ArrayList<Robot> RobotsList;
    public ArrayList<Edges> EdgesList = new ArrayList<Edges>();
    public ArrayList<Polygon> PolygonsList;
    private ArrayList<String> fileData;

    void ReadFile() throws IOException
    {
        FileReader file = new FileReader("./Files/robots.mat");
        BufferedReader fileReader = new BufferedReader(file);
        fileData = new ArrayList<>();
        String line;

        while((line = fileReader.readLine()) != null)
        {
            fileData.add(line);
        }
    }

    void LoadMapDataFromLine(int line)  // specify the line
    {
        line--;
        boolean obstacles = fileData.get(line).contains("#");
        RobotsList = new ArrayList<>();

        String robotsPositionsString;
        String polygonsDataString = null;

        if (obstacles) {
            String data[] = fileData.get(line).split("#");
            robotsPositionsString = data[0];
            polygonsDataString = data[1];
            data = null;
        } else {
            robotsPositionsString = fileData.get(line);
        }

        NumberScanner scannerObj = new NumberScanner(robotsPositionsString);

        scannerObj.GetNextDouble();
        while (scannerObj.HasNextDouble()) {
            double x = scannerObj.GetNextDouble();
            double y = scannerObj.GetNextDouble();
            RobotsList.add(new Robot(new Coordinates(x, y)));
        }

        if (!obstacles)
            return;

        String[] polygonsDataStringSplit = polygonsDataString.split(";");
        int numberOfPolygons = polygonsDataStringSplit.length;
        PolygonsList = new ArrayList<>();

        for (int i = 0; i < numberOfPolygons; i++) {
            PolygonsList.add(new Polygon());
            scannerObj = new NumberScanner(polygonsDataStringSplit[i]);
            while (scannerObj.HasNextDouble()) {
                double x = scannerObj.GetNextDouble();
                double y = scannerObj.GetNextDouble();

                if(!PolygonsList.get(i).Lines.isEmpty()) {
                    Line2D lastLine = PolygonsList.get(i).Lines.get(PolygonsList.get(i).Lines.size() - 1);
                    Line2D newLine = new Line2D.Double(lastLine.getX2(), lastLine.getY2(), x, y);
                    PolygonsList.get(i).Lines.add(newLine);
                }
                else
                {
                    Line2D newLine = new Line2D.Double(x,y,x,y);
                    PolygonsList.get(i).Lines.add(newLine);
                }
            }

            PolygonsList.get(i).Lines.remove(0);

        }
    }

    // Calling function to create edges with current Robot List
    void createEdges() {
        for (int i=0; i < RobotsList.size(); i++){
            for (int j = i + 1; j < RobotsList.size(); j++){
                Edges newEdge = new Edges(RobotsList.get(i), RobotsList.get(j));
                EdgesList.add(newEdge);
            }
        }
    }


}

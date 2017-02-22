package com.frappe.main;

import DataStructures.*;
import FileIO.InputReader;
import UtilityObjects.NumberScanner;

import java.awt.geom.Line2D;
import java.util.ArrayList;

/**
 * Created by Gabriel on 20/02/2017.
 */

public class Map {

    private static Map instance = null;

    public ArrayList<Robot> RobotsList;
   // public ArrayList<Edge> EdgesList = new ArrayList<Edge>(); ANOTHER DUBIOUS PIECE OF CODE. WTF IS GOING ON???
    public ArrayList<Obstacle> obstaclesList;

    private Map() {
    }



    public static Map GetInstance() {
        if (instance == null)
            instance = new Map();
        return instance;
    }

    public void LoadMapDataFromLine(int line)  // specify the line
    {
        //We get the com.frappe.main.Graph instance
        Graph graph = Graph.GetInstance();
        graph.CleanGraph();

        //We initialise the arrays
        RobotsList = new ArrayList<>();
        obstaclesList = new ArrayList<>();

        //Lines start from 1 in the file
        line--;

        //We get the content of the file as an ArrayList of Strings
        InputReader inputFile = InputReader.GetInstance();

        //We get the relevant line from the file
        String lineContent = inputFile.GetLine(line);

        //We check if there are any obstacles
        boolean obstacles = lineContent.contains("#");

        //We split the line into the two contents
        String robotsPositionsString;
        String polygonsVerticesString = null;

        if (obstacles) {
            String data[] = lineContent.split("#");
            robotsPositionsString = data[0];
            polygonsVerticesString = data[1];
        } else {
            robotsPositionsString = lineContent;
        }

        //We initialise our intelligently designed NumberScanner
        NumberScanner scannerObj = new NumberScanner(robotsPositionsString);

        //We get the whole list of robots
        scannerObj.GetNextDouble();
        while (scannerObj.HasNextDouble()) {
            double x = scannerObj.GetNextDouble();
            double y = scannerObj.GetNextDouble();
            Robot currentRobot = new Robot(new Coordinates(x, y));
            RobotsList.add(currentRobot);
            Node node = new Node(x,y,currentRobot, null);
            graph.AddNode(node);
        }

        //If there are no obstacles, there is nothing else to read
        if (!obstacles)
            return;

        /*We split the String into an ArrayList of Strings that contain
          the relevant content for each of the polygons.
         */
        String[] polygonsDataStringSplit = polygonsVerticesString.split(";");

        //We parse through each of the relevant polygon Strings
        for (String polygonDataString : polygonsDataStringSplit) {

            ArrayList<Node> verticesList = new ArrayList<>();
            ArrayList<Line2D> edgesList = new ArrayList<>();
            Obstacle newObstacle = new Obstacle(verticesList, edgesList);

            /*We initilise a new NumberScanner for each of the relevant Strings
              and then we get each vertex of the obstacles.
             */
            scannerObj = new NumberScanner(polygonDataString);

            while (scannerObj.HasNextDouble()) {
                double x = scannerObj.GetNextDouble();
                double y = scannerObj.GetNextDouble();

                Node node = new Node(x,y, newObstacle);
                graph.AddNode(node);
                verticesList.add(node);

                Line2D newLine;
                if (!edgesList.isEmpty()) {
                    Line2D lastLine = edgesList.get(edgesList.size() - 1);
                    newLine = new Line2D.Double(lastLine.getX2(), lastLine.getY2(), x, y);

                } else {
                    newLine = new Line2D.Double(x, y, x, y);
                }
                edgesList.add(newLine);
            }

            edgesList.remove(0);
            Line2D finalEdge = new Line2D.Double(
                    edgesList.get(edgesList.size()-1).getX2(),
                    edgesList.get(edgesList.size()-1).getY2(),
                    edgesList.get(0).getX1(),
                    edgesList.get(0).getY1());
            edgesList.add(finalEdge);
            obstaclesList.add(newObstacle);
        }
    }
}

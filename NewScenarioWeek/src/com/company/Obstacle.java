package com.company;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Lambros on 21/02/2017.
 */
public class Obstacle {

    private ArrayList<Coordinate> obstacleNodes = new ArrayList<>();
    private ArrayList<Line2D> obstacleEdges = new ArrayList<>();


    public Obstacle(ArrayList<Coordinate> obstacleNodes) {
        this.obstacleNodes = obstacleNodes;
        populateEdgeList();
    }

    public ArrayList<Coordinate> getObstacleNodes() {
        return obstacleNodes;
    }

    void populateEdgeList() {

        for (int i = 0; i < obstacleNodes.size()-1; i++) {
            double x1 = obstacleNodes.get(i).getX();
            double y1 = obstacleNodes.get(i).getY();

            double x2 = obstacleNodes.get(i+1).getX();
            double y2 = obstacleNodes.get(i+1).getY();

            Line2D line = new Line2D.Double(x1, y1, x2, y2);

            obstacleEdges.add(line);
        }

        // Create the edge from last node to first node to close the shape...
        double x1 = obstacleNodes.get(obstacleNodes.size()-1).getX();
        double y1 = obstacleNodes.get(obstacleNodes.size()-1).getY();
        double x2 = obstacleNodes.get(0).getX();
        double y2 = obstacleNodes.get(0).getY();
        Line2D line = new Line2D.Double(x1, y1, x2, y2);
        obstacleEdges.add(line);

        System.out.println("Created " + obstacleEdges.size() + " edges");
    }

    public ArrayList<Line2D> getObstacleEdges() {
        return obstacleEdges;
    }

}

package com.company;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.stream.Stream;
import java.io.IOException;
import java.nio.file.Paths;

public class Main {

    private static Configuration c;
    private static ArrayList<Line2D> edgesConnectingObstacles = new ArrayList<>();
    private static ArrayList<ArrayList<Line2D>> pathsBetweenRobots = new ArrayList<ArrayList<Line2D>>();

    public static void main(String[] args) {

        c = new Configuration(2);
        createEdgesConnectingObstacles();
        createPathsBetweenRobots();
        System.out.println("");

    }

    public static ArrayList<Line2D> getThisObstacleEdges(){

        ArrayList<Line2D> results = new ArrayList<>();
        ArrayList<Obstacle> obstacles = c.getObstacleArrayList();

        for (Obstacle o : obstacles){
            results.addAll(o.getObstacleEdges());
        }

        return results;
    }

    public static ArrayList<Coordinate> getThisObstacleNodes(Obstacle obstacle){

        return obstacle.getObstacleNodes();
    }

    public static boolean isIntersecting(Line2D newEdge, ArrayList<Line2D> edgeArray){

        for (Line2D edge : edgeArray){

            if (newEdge.getP1().distance(edge.getP1()) != 0 &&
                    newEdge.getP1().distance(edge.getP2()) != 0 &&
                    newEdge.getP2().distance(edge.getP1()) != 0 &&
                    newEdge.getP2().distance(edge.getP2()) != 0)
            {
                if (newEdge.intersectsLine(edge)) {
                    System.out.print("Line (" + newEdge.getX1() + "," + newEdge.getY1() + ") (" + newEdge.getX2() + "," + newEdge.getY2() + ") and ");
                    System.out.println("Line (" + edge.getX1() + "," + edge.getY1() + ") (" + edge.getX2() + "," + edge.getY2() + ") INTERSECT!!!");
                    return true;
                }
            }
        }
        return false;
    }

    public static void createEdgesConnectingObstacles(){
        ArrayList<Line2D> obstacleEdges = getThisObstacleEdges();
        ArrayList<Obstacle> obstacles = c.getObstacleArrayList();

        System.out.println("MY shit is broken");



        for (int i = 0; i < obstacles.size(); i++){
            for (int j = i + 1; j < obstacles.size(); j++){
                // Loop through the nodes in the obstacle
                for (Coordinate cMainObstacle : obstacles.get(i).getObstacleNodes()){
                    for (Coordinate cOtherObstacle : obstacles.get(j).getObstacleNodes()){
                        Line2D newLine = new Line2D.Double(cMainObstacle.getX(), cMainObstacle.getY(), cOtherObstacle.getX(), cOtherObstacle.getY());
                        if (!isIntersecting(newLine, obstacleEdges)){
                            edgesConnectingObstacles.add(newLine);
                        }
                    }
                }
            }
        }

        System.out.println("Accepting " + edgesConnectingObstacles.size() + " edges connecting obstacles.");
    }

    public static void createPathsBetweenRobots(){
        ArrayList<Robot> robots = c.getRobotArrayList();
        ArrayList<Obstacle> obstacles = c.getObstacleArrayList();
        ArrayList<Line2D> obstacleEdges = getThisObstacleEdges();

        for (int i=0; i < robots.size(); i++){
            for (int j=i+1; j < robots.size(); j++){
                Line2D newLine = new Line2D.Double(robots.get(i).getCoordinates().getX(), robots.get(i).getCoordinates().getY(),robots.get(j).getCoordinates().getX(),robots.get(j).getCoordinates().getY());
                // If there's no intersection, there is a direct edge/path to the other robot node in the graph
                if (!isIntersecting(newLine, obstacleEdges)){
                    ArrayList<Line2D> newArray = new ArrayList<>();
                    newArray.add(newLine);
                    pathsBetweenRobots.add(newArray);
                }
                // If it interacts, there are obstacles between so a new path has to be calculated
                else {

                }
            }
        }
    }
}

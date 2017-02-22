package com.company;

//import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;

import java.io.Console;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

/**
 * Created by Lambros on 21/02/2017.
 */
public class Configuration {
    private ArrayList<Robot> robotArrayList = new ArrayList<>();
    private ArrayList<Obstacle> obstacleArrayList = new ArrayList<>();
    private List<String> configurationList = new ArrayList<>();
    private int currentConfig;

    public Configuration(int configurationNumber) {

        // Set the line number we're interested in.
        this.currentConfig = configurationNumber-1;

        System.out.println("\n\nWorking on configuration " + configurationNumber);

        // Read all lines from robots.mat
        readFile();

        // Create robots and add them to ArrayList
        populateRobotList();

        // Create obstacles and add them to ArrayList (if they exist)
        populateObstacleList();

    }

    public ArrayList<Robot> getRobotArrayList() {
        return robotArrayList;
    }

    public ArrayList<Obstacle> getObstacleArrayList() {
        return obstacleArrayList;
    }

    public List<String> getConfigurationList() {
        return configurationList;
    }

    void readFile() {
        String fileName = "./files/robots.mat";

        // Read file into stream, try-with-resources
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            // Convert file stream into List of strings
            configurationList = stream.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void populateRobotList() {
        // Load the correct line from the list of configurations
        String robotCoordinatesStr = configurationList.get(currentConfig);

        // Split the line into the robot part and the obstacle part (if it exists)
        // and only keep the robot part
        robotCoordinatesStr = robotCoordinatesStr.split(":")[1]; // Get rid of the beginning...
        robotCoordinatesStr = robotCoordinatesStr.split("#")[0]; // Get rid of everything after #

        ArrayList<Coordinate> extractedCoordinates = extractCoordinates(robotCoordinatesStr);

        for (Coordinate c:extractedCoordinates) {
            // Create new (sleeping) robot based on coordinates
            Robot r = new Robot(c, false);
            robotArrayList.add(r);
        }

        System.out.println("Created " + robotArrayList.size() + " robots");
    }

    void populateObstacleList() {
        // Load the correct line from the list of configurations
        String obstacleStr = configurationList.get(currentConfig);

        obstacleStr = obstacleStr.split(":")[1]; // Get rid of the beginning...

        // Only try to parse if the configuration actually contains some obstacles
        if(obstacleStr.contains("#")) {
            obstacleStr = obstacleStr.split("#")[1]; // Get rid of robot coordinate part

            // Split string into individual obstacles
            String[] obstacleListStr = obstacleStr.split(";");

            ArrayList<Coordinate> singleObstacleCoordinates;
            for (String s : obstacleListStr) {
                singleObstacleCoordinates = new ArrayList<>();
                singleObstacleCoordinates = extractCoordinates(s);
                Obstacle o = new Obstacle(singleObstacleCoordinates);
                obstacleArrayList.add(o);
            }
            System.out.println("Added " + obstacleArrayList.size() + " obstacles");
        } else {
            System.out.println("Configuration contains no obstacles");
        }
    }

    // NOTE: Make sure you only pass "clean" strings to this function,
    // for example "(3,4), (3,53)", without any ';' or '#'
    ArrayList<Coordinate> extractCoordinates(String s) {

        // Remove all spaces
        s = s.replaceAll("\\s+","");
        // Extract all the numbers from the string into a String[]
        String[] extractedCoordinatesStr = s.split("[(,)]");

        ArrayList<Double> tempCoordinates = new ArrayList<>();
        ArrayList<Coordinate> extractedCoordinates = new ArrayList<>();

        // Remove any empty "" strings from extractedCoordinatesStr
        // and add them to tempCoordinates
        for (int i = 0; i < extractedCoordinatesStr.length; i++) {
            if(!extractedCoordinatesStr[i].isEmpty())
                tempCoordinates.add(Double.parseDouble(extractedCoordinatesStr[i]));
        }

        // Go through pairs of coordinates and create a new Coordinate object
        // which is then added to extractedCoordinates
        for (int i = 0; i < tempCoordinates.size(); i+=2) {
            Coordinate c = new Coordinate(tempCoordinates.get(i), tempCoordinates.get(i+1));
            extractedCoordinates.add(c);
        }

        return extractedCoordinates;
    }




}

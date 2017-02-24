package com.scenarioweek4.main;

import DataStructures.Coordinates;
import DataStructures.Node;
import DataStructures.Path;
import FileIO.InputReader;
import FileIO.OutputWriter;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        InputReader inputReader = InputReader.GetInstance();
        OutputWriter outputWriter = OutputWriter.GetInstance();
        Map map = Map.GetInstance();
        Graph graph = Graph.GetInstance();
        Pathfinder pathfinder = new Pathfinder();

        outputWriter.writeToFile("valravn");
        outputWriter.writeToFile("perb6k7bsq7gm4c3g4befad53u");

        for (int line = 1; line <= inputReader.GetNumberOfLine(); line++) {
            map.LoadMapDataFromLine(line);

            graph.ConstructGraph();

            ArrayList<Node> nodesWithRobots = new ArrayList<>();
            ArrayList<Node> solutionNodes = new ArrayList<>();
            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append(line + ": ");


            ExplosivePathing explosivePathing = new ExplosivePathing();
            ArrayList<Path> superPaths = explosivePathing.generateSolution();
            if (superPaths == null) {
                continue;
            }
            printShit(superPaths, stringBuilder);

            //Display to console and write to file
            System.out.println(stringBuilder);
            outputWriter.writeToFile(stringBuilder.toString());
        }
        outputWriter.CloseWriter();
    }

    private static void printShit(ArrayList<Path> result, StringBuilder stringBuilder) {
        for (int i = 0; i < result.size(); i++) {
            for (int j = 0; j < result.get(i).getPath().size(); j++) {
                Coordinates coord = result.get(i).getPath().get(j).GetCoordinates();
                stringBuilder.append("(" + coord.x + ", " + coord.y + ")");

                if (j != result.get(i).getPath().size() - 1) {
                    stringBuilder.append(", ");
                }
            }
            if(i != result.size() - 1)
            stringBuilder.append(";");
        }
    }
}
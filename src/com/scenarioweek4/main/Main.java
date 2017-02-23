package com.scenarioweek4.main;

import DataStructures.Node;
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
            StringBuilder sb = new StringBuilder();

            sb.append(line + ": ");

            //Look for robot nodes
            for (Node n : graph.nodesList) {
                if (!n.robotsAtThisNode.isEmpty()) {
                    nodesWithRobots.add(n);
                }
            }

            //Get path to every robot
            for (int i = 0; i < nodesWithRobots.size() - 1; i++) {
                if (!solutionNodes.isEmpty()) {
                    solutionNodes.remove(solutionNodes.size() - 1);
                }

                ArrayList<Node> path = pathfinder.findShortestPathAStar(nodesWithRobots.get(i), nodesWithRobots.get(i + 1));

                if (path == null) {
                    continue;
                }

                solutionNodes.addAll(path);
            }

            //Construct response string
            if (!solutionNodes.isEmpty()) {
                for (Node n : solutionNodes) {
                    sb.append("(" + n.GetCoordinates().x + ", " + n.GetCoordinates().y + ") , ");
                }

                for (int i = 1; i <= 3; i++)
                    sb.deleteCharAt(sb.length() - 1);

                //Display to console and write to file
                System.out.println(sb);
                outputWriter.writeToFile(sb.toString());
            }
        }

        outputWriter.CloseWriter();
    }
}
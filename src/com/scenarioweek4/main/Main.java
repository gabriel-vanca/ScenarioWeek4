package com.scenarioweek4.main;

import DataStructures.Node;
import FileIO.InputReader;
import Solutions.Basic;

public class Main {

    public static void main(String[] args) {
        InputReader inputReader = InputReader.GetInstance();
        Map map = Map.GetInstance();
        Graph graph = Graph.GetInstance();
        Pathfinder pathfinder = new Pathfinder();

    //    map.LoadMapDataFromLine(2);
      //  graph.ConstructGraph();

        //start test purposes
        for(int i=1; i<=inputReader.GetNumberOfLine();i++) {
            map.LoadMapDataFromLine(i);
            graph.ConstructGraph();
            System.out.print(i + ": ");
            Basic b = new Basic();
           // b.runBasic();
        }

    }

    private static void printNode(Node node) {
        System.out.println("[" + node.GetCoordinates().x + ", " + node.GetCoordinates().y + "]");
    }

}
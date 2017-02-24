package com.frappe.main;

import DataStructures.Node;
import FileIO.InputReader;
import Solutions.Advanced;
import Solutions.Basic;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        InputReader inputReader = InputReader.GetInstance();
        Map map = Map.GetInstance();
        Graph graph = Graph.GetInstance();
        com.frappe.main.Pathfinder pathfinder = new com.frappe.main.Pathfinder();

        //start test purposes
//        for(int i=1; i<=inputReader.GetNumberOfLine();i++) {
//            map.LoadMapDataFromLine(i);
//            graph.ConstructGraph();
//            System.out.print(i + ": ");
//            Basic b = new Basic();
//           // b.runBasic();
//        }

        // Run one instance only
        int line = 10;
        // Login Details
        System.out.println("valravn");
        System.out.println("perb6k7bsq7gm4c3g4befad53u");

        map.LoadMapDataFromLine(line);
        graph.ConstructGraph();
        System.out.print(line + ": ");
        Advanced a = new Advanced();
//        Basic b = new Basic();
    }

    private static void printNode(Node node) {
        System.out.println("[" + node.GetCoordinates().x + ", " + node.GetCoordinates().y + "]");
    }

}
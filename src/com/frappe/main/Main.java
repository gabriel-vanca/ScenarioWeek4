package com.frappe.main;

import DataStructures.Node;
import FileIO.InputReader;
import Solutions.Basic;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        InputReader inputReader = InputReader.GetInstance();
        Map map = Map.GetInstance();
        Graph graph = Graph.GetInstance();
        com.frappe.main.Pathfinder pathfinder = new com.frappe.main.Pathfinder();

        //start test purposes
        for(int i=1; i<=inputReader.GetNumberOfLine();i++) {
            map.LoadMapDataFromLine(i);
            graph.ConstructGraph();
            System.out.print(i + ": ");
            Basic b = new Basic();
           // b.runBasic();
        }

//        Node startNode = null;
//        Node targetNode = null;
//        int count = 0;
//        for (Node node : graph.nodesList) {
//            if (node.robotsAtThisNode.isEmpty()) {
//                continue;
//            }
//
//
//            if (count == 0) {
//                startNode = node;
//                count++;
//            } else if (node.GetCoordinates().x == 4.6) {
//                targetNode = node;
//                break;
//            }
//
//
//        }


       // ArrayList<Node> path = pathfinder.findShortestPathAStar(startNode, targetNode);
        //end test purposes
////        printNode(startNode);
//        for (int i = 0; i < path.size(); i++) {
//            printNode(path.get(i));
//        }


       // int numberOfLines = inputReader.GetNumberOfLine();

     //   System.out.println("Number of nodes: " + graph.nodesList.size());

       /* for (int currentLine = 1; currentLine <= numberOfLines; currentLine++) {

            map.LoadMapDataFromLine(currentLine);

        }*/



    }

    private static void printNode(Node node) {
        System.out.println("[" + node.GetCoordinates().x + ", " + node.GetCoordinates().y + "]");
    }

}
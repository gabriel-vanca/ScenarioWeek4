import FileIO.InputReader;

public class Main {

    public static void main(String[] args) {
        InputReader inputReader = InputReader.GetInstance();
        Map map = Map.GetInstance();
        Graph graph = Graph.GetInstance();

        //start test purposes
        map.LoadMapDataFromLine(1);
        //end test purposes

        int numberOfLines = inputReader.GetNumberOfLine();

        System.out.println("Number of nodes: " + graph.nodesList.size());

        for (int currentLine = 1; currentLine <= numberOfLines; currentLine++) {

            map.LoadMapDataFromLine(currentLine);

            graph.findLineToRobots(graph.nodesList.get(0), graph.nodesList.get(1));

        }


//        System.out.println("Number of nodes: " + graph.nodesList.size());

    }


    //DUBIOUS CODE???

    // Calling function to create edges with current DataStructures.Robot List
  /*  public void createEdges() {
        for (int i=0; i < RobotsList.size(); i++){
            for (int j = i + 1; j < RobotsList.size(); j++){
                Edge newEdge = new Edge(RobotsList.get(i), RobotsList.get(j));
                EdgesList.add(newEdge);
            }
        }
    }*/

}

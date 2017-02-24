ArrayList<Float> all_coordinates;
ArrayList<Float> solution_coordinates;

ArrayList<Robot> allRobots = new ArrayList<Robot>(); 
ArrayList<Obstacle> allObstacles = new ArrayList<Obstacle>();
ArrayList<ArrayList<SolutionPathLine>> allSolutionPathLines = new ArrayList<ArrayList<SolutionPathLine>>();
ArrayList<SolutionPathLine> allSolutionPathLinesMixed = new ArrayList<SolutionPathLine>(); 

int canvasSize = 650;
int halfCanvasSize = canvasSize/2;
int robotSize = 5;
int counter = 0;

float translationAmountX = halfCanvasSize;
float translationAmountY = halfCanvasSize;

void setup() {
  background(100);
  size(650, 650);
  translate(halfCanvasSize, halfCanvasSize);




  String lines[] = loadStrings("all_solutions1.txt");
  println("there are " + lines.length + " lines");


  // Read the strings for tiguration
  String robot_coords = robot_coords23;
  String obstacle_string = obstacle_coords23;
  String solution_path =  lines[22].split(":")[1];

  // Remove any spaces from both strings
  robot_coords = robot_coords.replaceAll("\\s+", "");  
  obstacle_string = obstacle_string.replaceAll("\\s+", "");
  solution_path = solution_path.replaceAll("\\s+", "");

  String delimSemi = ";";
  String delimOther = "[(,)]";

  // Split "obstacle_coords" String into String[] of separate obstacles
  String[] obstacles = obstacle_string.split(delimSemi);
  // Split "robot_coords" String into String[] of separate robot coordinates
  String[] robots = robot_coords.split(delimOther);
  // Split "solution_path" String into String[] of separate movements
  String[] solution = solution_path.split(delimSemi);

  ArrayList<Float> obstacle_coordinates = new ArrayList<Float>();
  ArrayList<Float> robot_coordinates = new ArrayList<Float>();
  solution_coordinates = new ArrayList<Float>();

  // Read all Robot coordinates
  for (String s : robots) {
    if (!s.isEmpty())
      robot_coordinates.add(Float.parseFloat(s));
  }

  // Read all obstacle coordinates
  obstacle_coordinates = new ArrayList<Float>();
  for (String o : obstacles) {
    String[] tokens = o.split(delimOther);
    // Parse each coordinate into a Float...
    for (String t : tokens) { 
      if (!t.isEmpty()) { 
        obstacle_coordinates.add(Float.parseFloat(t));
      }
    }
  }

  // Concat all node coordinates into one ArrayList to be used for scaling
  all_coordinates = new ArrayList<Float>();
  all_coordinates.addAll(obstacle_coordinates);
  all_coordinates.addAll(robot_coordinates);

  // Create all Obstacle objects
  for (String o : obstacles) {
    obstacle_coordinates = new ArrayList<Float>();
    String[] tokens = o.split(delimOther);
    // Parse each coordinate into a Float...
    for (String t : tokens) { 
      if (!t.isEmpty()) { 
        obstacle_coordinates.add(Float.parseFloat(t));
      }
    }
    Obstacle newObstacle = new Obstacle(obstacle_coordinates);
    allObstacles.add(newObstacle);
  }

  // Create all Robot objects
  for (int i = 0; i < robot_coordinates.size(); i+=2)
  {
    // Create new Robot object
    Robot newRobot = new Robot(robot_coordinates.get(i), robot_coordinates.get(i+1));
    allRobots.add(newRobot);
  }

  // Create all SolutionPathLine objects
  for (String s : solution) {
    String[] solution_moves = s.split(delimOther);
    solution_coordinates = new ArrayList<Float>();

    for (int i = 0; i < solution_moves.length; i++) {
      if (!solution_moves[i].isEmpty()) {
        solution_coordinates.add(Float.parseFloat(solution_moves[i]));
      }
    }

    ArrayList<SolutionPathLine> temp = new ArrayList<SolutionPathLine>();

    for (int i = 0; i < solution_coordinates.size()-3; i+=2) {
      float x = solution_coordinates.get(i);
      float y = solution_coordinates.get(i+1);
      float x2 = solution_coordinates.get(i+2);
      float y2 = solution_coordinates.get(i+3);

      SolutionPathLine l = new SolutionPathLine(x, y, x2, y2);
      temp.add(l);
    }
    allSolutionPathLines.add(temp);
  }

  for (ArrayList<SolutionPathLine> pathSet : allSolutionPathLines) {
    color thisPathColor = color((int)random(100,250), (int)random(100,250), (int)random(100,250));
    for (SolutionPathLine s : pathSet) {
      s.setColor(thisPathColor);
      allSolutionPathLinesMixed.add(s);
    }
  }
}



public void draw() {
  halfCanvasSize = canvasSize/2;
  clear();

  translate(translationAmountX, translationAmountY);
  //PImage bg = loadImage("bg2.jpg");
  //background(bg);
  background(30, 30, 30);

  if (counter < allSolutionPathLinesMixed.size()) {
    allSolutionPathLinesMixed.get(counter).setDrawNow(true);
    counter++;
  }

  //delay(100);

  drawAllObstacles();
  drawAllRobots();
  drawSolutionPaths();
  drawAxis();
  printLegend();
}

public void printLegend() {
  text("Zoom: z/x \nMove: arrow keys \nReset: r", -translationAmountX, -translationAmountY+10);
}

public void drawAllRobots() {
  ellipseMode(CENTER);
  fill(100);
  fill(204, 204, 204);
  for (Robot r : allRobots) {
    float adjustedX = (halfCanvasSize*r.getX())/biggestNumber(all_coordinates);
    float adjustedY = -halfCanvasSize*r.getY()/biggestNumber(all_coordinates);
    ellipse(adjustedX, adjustedY, robotSize, robotSize);
    
    //text("(" + r.getX() + "," + r.getY() + ")", adjustedX, adjustedY );
  }
}

public void drawAllObstacles() {
  for (Obstacle o : allObstacles) {
    beginShape();
    stroke(250, 250, 250, 100);
    fill(204, 204, 204, 50);
    for (int i = 0; i < o.getCoordinates().size(); i+=2)
    {
      float adjustedX = (halfCanvasSize*o.getCoordinates().get(i))/biggestNumber(all_coordinates);
      float adjustedY = -(halfCanvasSize*o.getCoordinates().get(i+1))/biggestNumber(all_coordinates);
      vertex(adjustedX, adjustedY);
      vertex(adjustedX, adjustedY);
      
      //text("(" + o.getCoordinates().get(i) + "," + o.getCoordinates().get(i+1) + ")", adjustedX, adjustedY );
    }
    endShape(CLOSE);
  }
}

public void drawAxis() {
  stroke(0, 0, 0, 100);
  line(0, -halfCanvasSize, 0, halfCanvasSize);
  line(-halfCanvasSize, 0, halfCanvasSize, 0);
}

public void drawSolutionPaths() {

  //stroke(247, 184, 70);
  strokeWeight(1);
  for (ArrayList<SolutionPathLine> pathSet : allSolutionPathLines) {
    //color thisPathColor = color((int)random(250), (int)random(250), (int)random(250));
    //stroke(random(25), random(250), random(250));
    for (int i = 0; i < pathSet.size(); i++) {

      float x = pathSet.get(i).x1;
      float y = pathSet.get(i).y1;
      float x2 = pathSet.get(i).x2;
      float y2 = pathSet.get(i).y2;

      float adjustedX = (halfCanvasSize*x)/biggestNumber(all_coordinates);
      float adjustedY = -(halfCanvasSize*y)/biggestNumber(all_coordinates);
      float adjustedX2 = (halfCanvasSize*x2)/biggestNumber(all_coordinates);
      float adjustedY2 = -(halfCanvasSize*y2)/biggestNumber(all_coordinates);

      if (pathSet.get(i).hasBeenDrawn == false) {
        //pathSet.get(i).setColor(thisPathColor);
        pathSet.get(i).setHasBeenDrawn(true);
      }

      if (pathSet.get(i).drawNow == true) {
        stroke(pathSet.get(i).getColor(), 150);
        line(adjustedX, adjustedY, adjustedX2, adjustedY2);
      }
    }
  }
}

/*  */
public float biggestNumber(ArrayList<Float> coordinates)
{
  float result = 0;
  for (float f : coordinates) {
    if (Math.abs(f) > result)
      result = Math.abs(f);
  }
  return result;
}


// For zooming in and out
void keyPressed() {
  if (key == CODED) {
    if (keyCode == UP) {
      translationAmountY += 50;
    } else if (keyCode == DOWN) {
      translationAmountY -= 50;
    } else if (keyCode == RIGHT) {
      translationAmountX -= 50;
    } else if (keyCode == LEFT) {
      translationAmountX += 50;
    }
  } else {
    if (key == 'z') {
      canvasSize += 100;
    } else if (key == 'x') {
      canvasSize -= 100;
    } else if (key == 'r') {
      canvasSize = 650;
      translationAmountY = canvasSize/2;
      translationAmountX = canvasSize/2;
    }
  }
}
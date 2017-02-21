void setup() {
  
  int canvasSize = 650;
  int halfCanvasSize = canvasSize/2;
  int robotSize = 2;
  size(650, 650);
  translate(halfCanvasSize,halfCanvasSize);
  
  PImage bg;
  bg = loadImage("bg2.jpg");
  background(bg);
  
  // Example from brief:
  String robot_coords = robot_coords29;
  String obstacle_string = obstacle_coords29;
  String solution_path = "";//solution_path0;
  
  // Remove any spaces from both strings
  robot_coords = robot_coords.replaceAll("\\s+","");  
  obstacle_string = obstacle_string.replaceAll("\\s+","");
  solution_path = solution_path.replaceAll("\\s+","");
  
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
  ArrayList<Float> solution_coordinates = new ArrayList<Float>();

   for(String s : robots) {
     if(!s.isEmpty())
      robot_coordinates.add(Float.parseFloat(s));
  }
  
  /* For each obstacle held in String[] obstacles, extract the actual
  coordinates... */
  obstacle_coordinates = new ArrayList<Float>();
  for(String o : obstacles) {
    String[] tokens = o.split(delimOther);
    // Parse each coordinate into a Float...
    for(String t : tokens) { if(!t.isEmpty()) { obstacle_coordinates.add(Float.parseFloat(t)); } }
   }
   
    ArrayList<Float> all_coordinates = new ArrayList<Float>();
    all_coordinates.addAll(obstacle_coordinates);
    all_coordinates.addAll(robot_coordinates);
  
   for(String o : obstacles) {
     obstacle_coordinates = new ArrayList<Float>();
     String[] tokens = o.split(delimOther);
     // Parse each coordinate into a Float...
     for(String t : tokens) { if(!t.isEmpty()) { obstacle_coordinates.add(Float.parseFloat(t)); } }
     
     beginShape(); 
    for(int i = 0; i < obstacle_coordinates.size(); i+=2)
    {
      float adjustedX = (halfCanvasSize*obstacle_coordinates.get(i))/biggestNumber(all_coordinates);
      float adjustedY = -(halfCanvasSize*obstacle_coordinates.get(i+1))/biggestNumber(all_coordinates);
      vertex(adjustedX, adjustedY);
    }
    endShape(CLOSE);
   }
   
    
    ellipseMode(CENTER);
    print("Robot coord number: " + robot_coordinates.size() + "\n");
  
  for(int i = 0; i < robot_coordinates.size(); i+=2)
  {
    fill(100);
    float adjustedX = (halfCanvasSize*robot_coordinates.get(i))/biggestNumber(all_coordinates);
    float adjustedY = -halfCanvasSize*robot_coordinates.get(i+1)/biggestNumber(all_coordinates);
    fill(255, 255, 255);
    print(adjustedX + " " +adjustedY + "\n");
    ellipse(adjustedX, adjustedY,robotSize,robotSize);
  }
  
  for(String s : solution) {
    String[] solution_moves = s.split(delimOther);
    solution_coordinates = new ArrayList<Float>();
    
    for(int i = 0; i < solution_moves.length; i++) {
      if(!solution_moves[i].isEmpty())
        solution_coordinates.add(Float.parseFloat(solution_moves[i]));
    }
    stroke(random(255), random(255), random(255));
    strokeWeight(2);
    for(int i = 0; i < solution_coordinates.size()-3; i+=2) {
 
       float x = solution_coordinates.get(i);
       float y = solution_coordinates.get(i+1);
       float x2 = solution_coordinates.get(i+2);
       float y2 = solution_coordinates.get(i+3);
       
       print(" x: " + x + " y: " + y + " x2: " + x2 + " y2: " +y2 + "\n"); 

       float adjustedX = (halfCanvasSize*x)/biggestNumber(all_coordinates);
       float adjustedY = -(halfCanvasSize*y)/biggestNumber(all_coordinates);
       float adjustedX2 = (halfCanvasSize*x2)/biggestNumber(all_coordinates);
       float adjustedY2 = -(halfCanvasSize*y2)/biggestNumber(all_coordinates);      
       
       line(adjustedX, adjustedY, adjustedX2, adjustedY2);
      

     }
   }
  
}

public float biggestNumber(ArrayList<Float> coordinates)
{
  float result = 0;
  for(float f : coordinates) {
    if(Math.abs(f) > result)
      result = Math.abs(f);
  }
  return result;
}
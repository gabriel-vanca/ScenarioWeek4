void setup() {
  
  int canvasSize = 650;
  int halfCanvasSize = canvasSize/2;
  size(650, 650);
  translate(halfCanvasSize,halfCanvasSize);
  
  PImage bg;
  bg = loadImage("bg2.jpg");
  background(bg);
  
  // Example from brief:
  String robot_coords = robot_coords12;
  String obstacle_coords = obstacle_coords12;
  
  // Remove any spaces from both strings
  robot_coords = robot_coords.replaceAll("\\s+","");  
  obstacle_coords = obstacle_coords.replaceAll("\\s+","");
  
  String delimSemi = ";";
  String delimOther = "[(,)]";
  
  // Split "obstacle_coords" String into String[] of separate obstacles
  String[] obstacles = obstacle_coords.split(delimSemi);
  // Split "robot_coords" String into String[] of separate robot coordinates
  String[] robots = robot_coords.split(delimOther);
  
  ArrayList<Float> coordinates = new ArrayList<Float>();
  ArrayList<Float> robot_coordinates = new ArrayList<Float>();

  /* For each obstacle held in String[] obstacles, extract the actual
  coordinates... */
  for(String o : obstacles) {
    String[] tokens = o.split(delimOther);
    coordinates = new ArrayList<Float>();
    
    // Parse each coordinate into a Float... 
    for(String t : tokens) 
    { if(!t.isEmpty()) { coordinates.add(Float.parseFloat(t)); } }
    
    /* ...then go through coordinates two at a time, and use as x/y coordinates 
    for obstacle shape. */
    beginShape(); 
    for(int i = 0; i < coordinates.size(); i+=2)
    {
      float adjustedX = (halfCanvasSize*coordinates.get(i))/biggestNumber(coordinates);
      float adjustedY = -(halfCanvasSize*coordinates.get(i+1))/biggestNumber(coordinates);
      vertex(adjustedX, adjustedY);
    }
    endShape(CLOSE);
  }  
  
  for(String s : robots) 
  {
    if(!s.isEmpty())
      robot_coordinates.add(Float.parseFloat(s));
    //print(s + "\n");
  }
  
  ellipseMode(CENTER);
  print("Robot coord number: " + robot_coordinates.size());
  for(int i = 0; i < robot_coordinates.size(); i+=2)
  {
    fill(100);
    float adjustedX = (halfCanvasSize*robot_coordinates.get(i))/biggestNumber(robot_coordinates);
    float adjustedY = -halfCanvasSize*robot_coordinates.get(i+1)/biggestNumber(robot_coordinates);
    fill(136, 185, 137);
    ellipse(adjustedX, adjustedY,7,7);
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
public class Obstacle {
  ArrayList<Float> coordinates;
  
  public Obstacle(ArrayList<Float> obstacleCoords) {
    this.coordinates = obstacleCoords;
  }
  
  public ArrayList<Float> getCoordinates() {
    return this.coordinates;
  }
  
}
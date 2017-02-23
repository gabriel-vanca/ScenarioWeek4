public class Robot {
  float x;
  float y;
  boolean isAwake;
  
  public Robot(float x, float y) {
    this.x = x;
    this.y = y;
    isAwake = false;
  }
  
  public void setX(float x) {
    this.x = x;
  }
  
  public void setY(float y) {
    this.y = y;
  }
  
  public float getX() {
    return this.x;
  }
  
  public float getY() {
    return this.y;
  }
  
  public void setIsAwake(boolean b) {
    this.isAwake = b;
  }
  
}
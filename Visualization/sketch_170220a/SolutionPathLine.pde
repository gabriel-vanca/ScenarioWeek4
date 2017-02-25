class SolutionPathLine {
  float x1;
  float y1;
  float x2;
  float y2;
  boolean drawNow;
  boolean hasBeenDrawn;
  color c;

  public SolutionPathLine(float x1, float y1, float x2, float y2) {
    this.x1 = x1;
    this.y1 = y1;
    this.x2 = x2;
    this.y2 = y2;
    drawNow = false;
    hasBeenDrawn = false;
  }

  public void setDrawNow(boolean b) {
    this.drawNow = b;
  }
  
  public void setHasBeenDrawn(boolean b) {
    this.hasBeenDrawn = b;
  }

  public void setColor(color c) {
    this.c = c;
  }
  
  public color getColor() {
    return c;
  }
}
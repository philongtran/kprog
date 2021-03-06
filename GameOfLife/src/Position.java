
/**
 * This class is responsible for the coordinates of the buttons
 * 
 * @author Phi Long Tran <191624>
 * @author Manuel Wessner <191711>
 * @author Steve Nono <191709>
 */
public class Position {

  // Instance variables
  private int positionX;
  private int positionY;

  // Constructor
  public Position(int positionX, int positionY) {
    this.positionX = positionX;
    this.positionY = positionY;
  }

  public Position getPosition() {
    return this;
  }

  public int getPositionX() {
    return positionX;
  }

  public int getPositionY() {
    return positionY;
  }

  @Override
  public String toString() {
    return "" + positionX + "," + positionY;
  }
}

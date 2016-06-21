import java.awt.Color;

public class Player {

  private final Color color;
  private int stones = 20;
  private int greyStones = 6;

  public Player(Color color) {
    this.color = color;
  }

  public Color getColor() {
    return color;
  }

  @Override
  public String toString() {
    return color.toString();
  }

  public boolean setStones() {
    if (stones <= 0) {
      return false;
    }
    stones--;
    return true;
  }

  public int getStones() {
    return stones;
  }

  public int getGreyStones() {
    return greyStones;
  }

  public boolean setGreyStones() {
    if (greyStones <= 0) {
      return false;
    }
    greyStones--;
    return true;
  }
}

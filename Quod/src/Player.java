import java.awt.Color;

public class Player {

  private final Color color;
  private int stones = 2;
  private int greyStones = 6;
  private boolean done = false;
  private final String playerDescription;

  public Player(Color color, String playerDescription) {
    this.color = color;
    this.playerDescription = playerDescription;
  }

  public Color getColor() {
    return color;
  }

  @Override
  public String toString() {
    return getPlayerDescription();
  }

  public boolean setStones() {
    stones--;
    if (stones <= 0) {
      done = true;
      return false;
    }
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

  public void setDone() {
    done = true;
  }

  public boolean isDone() {
    return done;
  }

  public String getPlayerDescription() {
    return playerDescription;
  }
}

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Player {

  private final Color color;
  private int remainingStones;
  private int greyStones;
  private boolean done;
  private final String playerDescription;
  private final List<Position> existingStones;

  public Player(Color color, String playerDescription) {
    this.color = color;
    this.remainingStones = 20;
    this.greyStones = 6;
    this.playerDescription = playerDescription;
    this.existingStones = new ArrayList<>(remainingStones);
  }

  public Color getColor() {
    return color;
  }

  @Override
  public String toString() {
    return getPlayerDescription();
  }

  public boolean reduceRemainingStones() {
    remainingStones--;
    if (remainingStones <= 0) {
      done = true;
      return false;
    }
    return true;
  }

  public int getRemainingStones() {
    return remainingStones;
  }

  public boolean hasGreyStones() {
    return greyStones > 0;
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

  public List<Position> getExistingStones() {
    return existingStones;
  }
}

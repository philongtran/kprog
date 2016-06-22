import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class QuodPlayer {

  private final Color color;
  private int remainingStones;
  private int greyStones;
  private final String playerDescription;
  private final List<Position> existingStones;
  private boolean hasNoStones;

  public QuodPlayer(Color color, String playerDescription) {
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

  public void reduceRemainingStones() {
    remainingStones--;
    if (remainingStones <= 0) {
      hasNoStones = true;
    }
  }

  public int getRemainingStones() {
    return remainingStones;
  }

  public boolean hasGreyStones() {
    return greyStones > 0;
  }

  public void reduceGreyStones() {
    if (hasGreyStones()) {
      greyStones--;
    }
  }

  public int getGreyStones() {
    return greyStones;
  }

  public String getPlayerDescription() {
    return playerDescription;
  }

  public List<Position> getExistingStones() {
    return existingStones;
  }

  public boolean hasUsedAllStones() {
    return hasNoStones;
  }
}

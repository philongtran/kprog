import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * This class is a representation of the player
 * 
 * @author Phi Long Tran <191624>
 * @author Manuel Wessner <191711>
 * @author Steve Nono <191709>
 */

public class QuodPlayer extends Observable {

  // instance variables
  private final Color color;
  private int remainingStones;
  private int greyStones;
  private final String playerDescription;
  private final List<Position> existingStones;
  private boolean hasNoStones;

  /**
   * constructor set player color and it stone pieces
   * 
   * @param color
   * @param playerDescription
   */
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

  /**
   * method for reducing the player stones rest of methods are self explaining
   */
  public void reduceRemainingStones() {
    remainingStones--;
    if (remainingStones <= 0) {
      hasNoStones = true;
    }
    setChangedAndNotifyObservers();
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
      setChangedAndNotifyObservers();
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

  private void setChangedAndNotifyObservers() {
    setChanged();
    notifyObservers();
  }
}

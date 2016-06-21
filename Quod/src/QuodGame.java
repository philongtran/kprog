import java.awt.Color;
import java.util.List;
import java.util.Observable;

public class QuodGame extends Observable {


  private final QuodBoard board;
  private QuodPlayer player1;
  private QuodPlayer player2;
  private QuodPlayer currentPlayer;
  private boolean isRunning = true;
  private boolean useGreyStones = false;

  QuodGame() {
    board = new QuodBoard(this);
    player1 = new QuodPlayer(Color.blue, "Player One");
    player2 = new QuodPlayer(Color.red, "Player Two");
    currentPlayer = player1;
  }

  public QuodBoard getBoard() {
    return board;
  }

  public void setBoard(Position stonePosition, QuodPlayer player) {
    player.getExistingStones().add(stonePosition);
    positionCheckForPlayer(player.getExistingStones());
    // board.setBoard(x, y, p);
    setChanged();
    notifyObservers();
  }

  private void positionCheckForPlayer(List<Position> playerStones) {
    for (int lineIndex = 0; lineIndex < playerStones.size(); lineIndex++) {
      for (int verticalIndex = 0; verticalIndex < playerStones.size(); verticalIndex++) {
        if (lineIndex != verticalIndex) {
          Position lineStone = playerStones.get(lineIndex);
          Position verticalStone = playerStones.get(verticalIndex);
          Position lineStoneToFind = new Position(
              verticalStone.getPositionX() - verticalStone.getPositionY()
                  + lineStone.getPositionY(),
              verticalStone.getPositionY() + verticalStone.getPositionX()
                  - lineStone.getPositionX());
          Position verticalStoneToFind = new Position(
              lineStone.getPositionX() - verticalStone.getPositionY() + lineStone.getPositionY(),
              lineStone.getPositionY() + verticalStone.getPositionX() - lineStone.getPositionX());

          boolean lineStoneFound = playerStones.contains(lineStoneToFind);
          boolean verticalStoneFound = playerStones.contains(verticalStoneToFind);
          if (lineStoneFound && verticalStoneFound) {
            System.out.println(getPlayer() + " won");
            isRunning = false;
            return;
          }
        }
      }
    }
  }


  public QuodPlayer getPlayer() {
    return currentPlayer;
  }

  public void switchPlayer() {
    if (currentPlayer.equals(player1)) {
      currentPlayer = player2;
    } else {
      currentPlayer = player1;
    }
  }

  public boolean isRunning() {
    return isRunning;
  }

  public void setUseGreyStones(boolean useGreyStones) {
    this.useGreyStones = useGreyStones;
  }

  public boolean getUseGreyStones() {
    return this.useGreyStones;
  }
}

import java.awt.Color;
import java.util.List;
import java.util.Observable;


public class QuodGame extends Observable {

  private final QuodBoard board;
  private final QuodPlayer player1;
  private final QuodPlayer player2;
  private QuodPlayer currentPlayer;
  private QuodResult result;

  QuodGame() {
    board = new QuodBoard(this);
    player1 = new QuodPlayer(Color.blue, "Player One (blue)");
    player2 = new QuodPlayer(Color.red, "Player Two (red)");
    currentPlayer = player1;
    result = QuodResult.ONGOING;
  }

  public QuodBoard getBoard() {
    return board;
  }

  public void setBoard(Position stonePosition, QuodPlayer player) {
    player.getExistingStones().add(stonePosition);
    positionCheckForPlayer(player.getExistingStones());
    existWinnerOnEnd();
    areAllStonesUsed();
    setChanged();
    notifyObservers();
  }

  private void existWinnerOnEnd() {
    if (areAllStonesUsed()) {
      boolean draw = player1.getGreyStones() == player2.getGreyStones();
      boolean playerOneWon = player1.getGreyStones() > player2.getGreyStones();
      if (!draw) {
        setResult(QuodResult.DRAW);
      } else if (playerOneWon) {
        setResult(QuodResult.WIN);
        switchToWinningPlayer(player1);
      } else {
        setResult(QuodResult.WIN);
        switchToWinningPlayer(player2);
      }
    }
  }

  private void switchToWinningPlayer(QuodPlayer winningPlayer) {
    if (!currentPlayer.equals(winningPlayer)) {
      switchPlayer();
    }
  }

  private boolean areAllStonesUsed() {
    return isRunning() && player1.hasUsedAllStones() && player2.hasUsedAllStones();
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
            setResult(QuodResult.WIN);
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
    return getResult().equals(QuodResult.ONGOING);
  }

  public QuodResult getResult() {
    return result;
  }

  public void setResult(QuodResult result) {
    this.result = result;
  }

}

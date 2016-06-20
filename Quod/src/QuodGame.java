import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class QuodGame extends Observable {


  private final Board board;
  private List<Position> player1Stones;
  private List<Position> player2Stones;
  private Player player1;
  private Player player2;
  private Player currentPlayer;
  private boolean isRunning = true;

  QuodGame() {
    board = new Board();
    player1Stones = new ArrayList<>();
    player2Stones = new ArrayList<>();
    player1 = new Player(Color.blue);
    player2 = new Player(Color.red);
    currentPlayer = player1;
  }

  public Board getBoard() {
    return board;
  }

  public void setBoard(int x, int y, Player p) {
    Position position = new Position(x, y);
    if (p.equals(player1)) {
      player1Stones.add(position);
      positionCheckForPlayer(player1Stones);
    } else if (p.equals(player2)) {
      player2Stones.add(position);
      positionCheckForPlayer(player2Stones);
    }
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

          // System.out.println("ToFind-Line: " + lineStoneToFind);
          // System.out.println("ToFind-Vertical: " + verticalStoneToFind);
          boolean lineStoneFound = hasStone(lineStoneToFind, playerStones);
          boolean verticalStoneFound = hasStone(verticalStoneToFind, playerStones);
          if (lineStoneFound && verticalStoneFound) {
            System.out.println(getPlayer() + " won");
            isRunning = false;
            return;
          }
        }
      }
    }
  }

  private boolean hasStone(Position stoneToFind, List<Position> playerStones) {
    for (Position stone : playerStones) {
      if (stone.equals(stoneToFind)) {
        return true;
      }
    }
    return false;
  }

  public Player getPlayer() {
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
}

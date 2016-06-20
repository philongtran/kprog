import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class QuodGame extends Observable {


  private final Board board;
  private List<Position> player1Stones;
  private List<Position> player2Stones;

  QuodGame() {
    board = new Board();
    player1Stones = new ArrayList<>();
    player2Stones = new ArrayList<>();
  }

  public Board getBoard() {
    return board;
  }

  public void setBoard(int x, int y, int playerID) {
    Position position = new Position(x, y);
    if (playerID == 1) {
      player1Stones.add(position);
    } else if (playerID == 2) {
      player2Stones.add(position);
    }
    board.setBoard(x, y, playerID);
    positionCheck(playerID, position);
    setChanged();
    notifyObservers();
  }

  public void positionCheck(int playerID, Position currentStone) {
    for (int lineIndex = 0; lineIndex < player1Stones.size(); lineIndex++) {
      for (int verticalIndex = 0; verticalIndex < player1Stones.size(); verticalIndex++) {
        if (lineIndex != verticalIndex) {
          Position lineStone = player1Stones.get(lineIndex);
          Position verticalStone = player1Stones.get(verticalIndex);
          Position lineStoneToFind = new Position(
              verticalStone.getPositionX() - verticalStone.getPositionY()
                  + lineStone.getPositionY(),
              verticalStone.getPositionY() + verticalStone.getPositionX()
                  - lineStone.getPositionX());
          Position verticalStoneToFind = new Position(
              lineStone.getPositionX() - verticalStone.getPositionY() + lineStone.getPositionY(),
              lineStone.getPositionY() + verticalStone.getPositionX() - lineStone.getPositionX());

          System.out.println("ToFind-Line: " + lineStoneToFind);
          System.out.println("ToFind-Vertical: " + verticalStoneToFind);
          boolean lineStoneFound = hasStone(lineStoneToFind);
          boolean verticalStoneFound = hasStone(verticalStoneToFind);
          if (lineStoneFound && verticalStoneFound) {
            System.out.println("won");
          }
        }
      }
    }
  }

  private boolean hasStone(Position stoneToFind) {
    for (Position stone : player1Stones) {
      if (stone.equals(stoneToFind)) {
        return true;
      }
    }
    return false;
  }
}

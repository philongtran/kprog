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
    int firstStoneIndex = player1Stones.indexOf(currentStone);
    // Position firstStone = player1Stones.get(firstStoneIndex);
    // Position secondStone = player1Stones.get(firstStoneIndex + 1);
    for (int lineIndex = 0; lineIndex < player1Stones.size(); lineIndex++) {
      for (int verticalIndex = 0; verticalIndex < player1Stones.size(); verticalIndex++) {
        if (lineIndex != verticalIndex) {
          Position lineStone = player1Stones.get(lineIndex);
          Position verticalStone = player1Stones.get(verticalIndex);
          int newX = lineStone.getPositionX() - verticalStone.getPositionX();
          int newY = lineStone.getPositionY() - verticalStone.getPositionY();
          int rotatedX = newY;
          int rotatedY = -newX;
          // var c=field(bx-by+ay,by+bx-ax)
          Position lineStoneToFind = new Position(
              verticalStone.getPositionX() - verticalStone.getPositionY()
                  + lineStone.getPositionY(),
              verticalStone.getPositionY() + verticalStone.getPositionX()
                  - lineStone.getPositionX());
          // var d=field(ax-by+ay,ay+bx-ax)
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


  // int newX, newY;
  // int rotatedX, rotatedY;
  // if (playerID == 1) {
  // for (int j = 0; j < player1Stones.size() - 1; j++) {
  // for (int i = j; i < player1Stones.size() - 1; i++) {
  // Position firstStone = player1Stones.get(i);
  // Position secondStone = player1Stones.get(i + 1);
  // if (firstStone != null && secondStone != null) {
  // newX = firstStone.getPositionX() - secondStone.getPositionX();
  // newY = firstStone.getPositionY() - secondStone.getPositionY();
  // rotatedX = newY;
  // rotatedY = -newX;
  // // for (int k = j; k < player1Stones.length; k++) {
  // // Position stonePosition = player1Stones[k];
  // // if (stonePosition != null) {
  //
  //
  // if (player1Stones.size() >= 4) {
  // Position positionToFind = new Position(firstStone.getPositionX() + rotatedX,
  // firstStone.getPositionY() + rotatedY);
  // Position positionToFind2 = new Position(secondStone.getPositionX() + rotatedX,
  // secondStone.getPositionY() + rotatedY);
  // System.out.println("Check 1:" + positionToFind);
  // System.out.println("Check 2:" + positionToFind2);
  // for (Position stone : player1Stones) {
  // if (stone != null && stone.equals(positionToFind)) {
  // System.out.println("true to find1: " + positionToFind + " found on " + stone);
  // }
  // if (stone != null && stone.equals(positionToFind2)) {
  // System.out.println("true to find2: " + positionToFind2 + " found on " + stone);
  // }
  // // }
  // // }
  // }
  // }
  // }
  // }
  // }
  // }
  // }
}

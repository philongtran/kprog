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
    if (playerID == 1) {
      player1Stones.add(new Position(x, y));
    } else if (playerID == 2) {
      player2Stones.add(new Position(x, y));
    }
    board.setBoard(x, y, playerID);
    positionCheck(playerID);
    setChanged();
    notifyObservers();
  }

  public void positionCheck(int playerID) {
    int newX, newY;
    int rotatedX, rotatedY;
    if (playerID == 1) {
      for (int j = 0; j < player1Stones.size() - 1; j++) {
        for (int i = j; i < player1Stones.size() - 1; i++) {
          Position firstStone = player1Stones.get(i);
          Position secondStone = player1Stones.get(i + 1);
          if (firstStone != null && secondStone != null) {
            newX = firstStone.getPositionX() - secondStone.getPositionX();
            newY = firstStone.getPositionY() - secondStone.getPositionY();
            rotatedX = newY;
            rotatedY = -newX;
            // for (int k = j; k < player1Stones.length; k++) {
            // Position stonePosition = player1Stones[k];
            // if (stonePosition != null) {


            if (player1Stones.size() >= 4) {
              Position positionToFind = new Position(firstStone.getPositionX() + rotatedX,
                  firstStone.getPositionY() + rotatedY);
              Position positionToFind2 = new Position(secondStone.getPositionX() + rotatedX,
                  secondStone.getPositionY() + rotatedY);
              System.out.println("Check 1:" + positionToFind);
              System.out.println("Check 2:" + positionToFind2);
              for (Position stone : player1Stones) {
                if (stone != null && stone.equals(positionToFind)) {
                  System.out.println("true to find1: " + positionToFind + " found on " + stone);
                }
                if (stone != null && stone.equals(positionToFind2)) {
                  System.out.println("true to find2: " + positionToFind2 + " found on " + stone);
                }
                // }
                // }
              }
            }
          }
        }
      }
    }
  }
}

import java.util.Observable;

public class QuodGame extends Observable {


  private final Board board;
  private Position[] player1Stones;
  private Position[] player2Stones;
  private int player1Index, player2Index;

  QuodGame() {
    board = new Board();
    player1Stones = new Position[20];
    player2Stones = new Position[20];
  }

  public Board getBoard() {
    return board;
  }

  public void setBoard(int x, int y, int playerID) {
    if (playerID == 1) {
      player1Stones[player1Index] = new Position(x, y);
      player1Index++;
    } else if (playerID == 2) {
      player2Stones[player2Index] = new Position(x, y);
      player2Index++;
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
      for (int j = 0; j < player1Stones.length - 1; j++) {
        for (int i = j; i < player1Stones.length - 1; i++) {
          Position firstStone = player1Stones[i];
          Position secondStone = player1Stones[i + 1];
          if (firstStone != null && secondStone != null) {
            newX = firstStone.getPositionX() - secondStone.getPositionX();
            newY = firstStone.getPositionY() - secondStone.getPositionY();
            rotatedX = newY;
            rotatedY = -newX;
            System.out.println(rotatedX + "," + rotatedY);
            for (int k = j; k < player1Stones.length; k++) {
              Position position = player1Stones[k];
              if (position != null && position.getPositionX() == rotatedX
                  && position.getPositionY() == rotatedY) {
                // System.out.println("true");
              }
            }
          }
        }
      }
    } else if (playerID == 2 && player2Stones.length > 3) {

    }
  }

}

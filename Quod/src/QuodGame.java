import java.util.Observable;

public class QuodGame extends Observable {


  private static QuodGame _game;
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

  public static QuodGame getInstance() {
    if (_game == null) {
      _game = new QuodGame();
    }
    return _game;
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
      int j = 0;
      while (j < player1Stones.length - 1) {
        for (int i = 0; i < player1Stones.length; i++) {
          newX = player1Stones[j].getPositionX() - player1Stones[i + 1].getPositionX();
          newY = player1Stones[j].getPositionY() - player1Stones[i + 1].getPositionY();
          rotatedX = newY;
          rotatedY = -newX;
          System.out.println(rotatedX + "," + rotatedY);
          for (int k = j; k < player1Stones.length; k++) {
            if (player1Stones[k].getPositionX() == rotatedX
                && player1Stones[k].getPositionY() == rotatedY) {
              System.out.println("true");
            }
          }
        }
        if (j < player1Stones.length) {
          j++;
        }
      }
    } else if (playerID == 2 && player2Stones.length > 3) {

    }
  }

}

import java.util.Observable;

public class QuodGame extends Observable {


  private static QuodGame _game;
  private final Board board;
  private Position[] player1Stones;
  private Position[] player2Stones;

  private QuodGame() {
    board = new Board();
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
      player1Stones[player1Stones.length] = new Position(x, y);
    } else if (playerID == 2) {
      player2Stones[player2Stones.length] = new Position(x, y);
    }
    board.setBoard(x, y, playerID);
    positionCheck(playerID);
    setChanged();
    notifyObservers();
  }

  public void positionCheck(int playerID) {
    int newX, newY;
    if (playerID == 1 && player1Stones.length > 3) {
      for (int i = 0; i < player1Stones.length; i++) {
        player1Stones[i].getPositionX();
        player1Stones[i].getPositionY();
      }
    } else if (playerID == 2 && player2Stones.length > 3) {

    }
  }

}

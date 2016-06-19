import java.util.Observable;

public class QuodGame extends Observable {


  private static QuodGame _game;
  private final Board board;

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
}

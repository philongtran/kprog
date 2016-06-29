/**
 * This class is responsible for the board
 * 
 * @author Phi Long Tran <191624>
 * @author Manuel Wessner <191711>
 * @author Steve Nono <191709>
 */
public class QuodBoard {

  private static final int DEFAULTBOARDSIZE = 11;
  private final int size;

  private QuodCell[][] board = new QuodCell[DEFAULTBOARDSIZE][DEFAULTBOARDSIZE];

  public QuodBoard(QuodGame quodGame) {
    this.size = DEFAULTBOARDSIZE;
    // initialize board
    for (int y = 0; y < DEFAULTBOARDSIZE; y++) {
      for (int x = 0; x < DEFAULTBOARDSIZE; x++) {
        board[y][x] = new QuodCell(Position.of(x, y), quodGame);
      }
    }
  }

  public int getSize() {
    return size;
  }

  public QuodCell getCell(Position coordinate) {
    return board[coordinate.getPositionY()][coordinate.getPositionX()];
  }
}

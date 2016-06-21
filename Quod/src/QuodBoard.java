
public class QuodBoard {

  private static final int DEFAULTBOARDSIZE = 11;
  private final int size;

  private QuodCell[][] board = new QuodCell[DEFAULTBOARDSIZE][DEFAULTBOARDSIZE];

  public QuodBoard(QuodGame quodGame) {
    this.size = DEFAULTBOARDSIZE;
    // initialize board
    for (int y = 0; y < DEFAULTBOARDSIZE; y++) {
      for (int x = 0; x < DEFAULTBOARDSIZE; x++) {
        board[y][x] = new QuodCell("" + x + "," + y, quodGame);
      }
    }
  }

  public int getSize() {
    return size;
  }

  public void setBoard(Position coordinate, QuodCellContent content) {
    getCell(coordinate).setContent(content);
  }

  public QuodCell getCell(Position coordinate) {
    return board[coordinate.getPositionY()][coordinate.getPositionX()];
  }
}

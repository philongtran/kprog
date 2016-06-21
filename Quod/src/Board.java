
public class Board {

  private static final int DEFAULTBOARDSIZE = 11;
  private final int size;

  private Cell[][] board = new Cell[DEFAULTBOARDSIZE][DEFAULTBOARDSIZE];

  public Board(QuodGame quodGame) {
    this.size = DEFAULTBOARDSIZE;
    // initialize board
    for (int y = 0; y < DEFAULTBOARDSIZE; y++) {
      for (int x = 0; x < DEFAULTBOARDSIZE; x++) {
        board[y][x] = new Cell("" + x + "," + y, quodGame);
      }
    }
  }

  public int getSize() {
    return size;
  }

  public void setBoard(Position coordinate, CellContent content) {
    getCell(coordinate).setContent(content);
  }

  public Cell getCell(Position coordinate) {
    return board[coordinate.getPositionY()][coordinate.getPositionX()];
  }
}

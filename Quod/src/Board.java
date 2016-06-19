
public class Board {

  private static final int DEFAULTBOARDSIZE = 11;
  private final int size;

  private int[][] board = new int[DEFAULTBOARDSIZE][DEFAULTBOARDSIZE];

  public Board() {
    this(DEFAULTBOARDSIZE);
  }

  private Board(int size) {
    this.size = size;
  }

  public int getSize() {
    return size;
  }

  public void setBoard(int x, int y, int playerID) {
    board[y][x] = playerID;
  }

  public int[][] getBoard() {
    return board;
  }

  public int getInfo(int x, int y) {
    return board[y][x];
  }
}

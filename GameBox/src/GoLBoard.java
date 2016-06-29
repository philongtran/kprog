import java.io.Serializable;

/**
 * this class represents the board of the game
 * 
 * @author Phi Long Tran <191624>
 * @author Manuel Wessner <191711>
 * @author Steve Nono <191709>
 */
public class GoLBoard implements Serializable {

  private static final long serialVersionUID = -8580171586180858490L;
  // instace variables
  private boolean[][] board;
  private int sizeX;
  private int sizeY;

  // constructor
  public GoLBoard(int sizeX, int sizeY) {
    this.sizeX = sizeX;
    this.sizeY = sizeY;
    board = new boolean[sizeY][sizeX];
    reset();
  }

  // make all cells dead
  public void reset() {
    for (int y = 0; y < sizeY; y++) {
      for (int x = 0; x < sizeX; x++) {
        board[y][x] = false;
      }
    }

  }

  // board size
  public int getSizeX() {
    return sizeX;
  }

  public int getSizeY() {
    return sizeY;
  }

  // set and get cell status
  public void setStatus(int x, int y, boolean status) {
    board[y][x] = status;
  }

  public boolean getStatus(int x, int y) {
    return board[y][x];
  }

  /**
   * Copies the current state of the board in a new instance
   * 
   * @return a copy of the current board
   */
  public GoLBoard copy() {
    GoLBoard copiedBoard = new GoLBoard(getSizeX(), getSizeY());
    for (int y = 0; y < getSizeY(); y++) {
      for (int x = 0; x < getSizeX(); x++) {
        copiedBoard.setStatus(x, y, getStatus(x, y));
      }
    }
    return copiedBoard;
  }

}

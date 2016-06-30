import java.util.ArrayList;
import java.util.List;

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
  private final List<Position> freeCellPositions;

  public QuodBoard(QuodGame quodGame) {
    this.size = DEFAULTBOARDSIZE;
    this.freeCellPositions = new ArrayList<>();
    // initialize board
    for (int y = 0; y < DEFAULTBOARDSIZE; y++) {
      for (int x = 0; x < DEFAULTBOARDSIZE; x++) {
        Position cellPosition = Position.of(x, y);
        board[y][x] = new QuodCell(cellPosition, quodGame);
        getFreeCellPositions().add(cellPosition);
      }
    }
  }

  public int getSize() {
    return size;
  }

  public QuodCell getCell(Position coordinate) {
    if (!isValidPosition(coordinate)) {
      return null;
    }
    return board[coordinate.getPositionY()][coordinate.getPositionX()];
  }

  public boolean isValidPosition(Position coordinate) {
    int y = coordinate.getPositionY();
    int x = coordinate.getPositionX();
    if (y >= size || x >= size || y < 0 || x < 0 || !QuodCellBorders.get().contains(coordinate)) {
      return false;
    }
    return true;
  }

  public List<Position> getFreeCellPositions() {
    return freeCellPositions;
  }

}

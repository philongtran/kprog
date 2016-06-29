
import java.util.Observable;
import java.util.concurrent.TimeUnit;

import javax.swing.Timer;

/**
 * This class represents the game
 * 
 * @author Phi Long Tran <191624>
 * @author Manuel Wessner <191711>
 * @author Steve Nono <191709>
 */
public class GoLGame extends Observable {

  // instance variables
  private GoLBoard board;
  private Timer timer;
  private boolean start;
  private int delay = 500; // milliseconds
  public static int GOLWINDOWNUMBER = 1;
  private boolean draw = false;

  private final static int ONESECOND = (int) TimeUnit.SECONDS.toMillis(1);

  // constructor
  public GoLGame(int sizeX, int sizeY) {
    board = new GoLBoard(sizeX, sizeY);
    timer = new Timer(ONESECOND, taskPerformer -> {
      run();
    });
  }

  // calculates the living cells around the current cell
  private void run() {
    GoLBoard temporaryBoard = board.copy();
    for (int y = 0; y < board.getSizeY(); y++) {
      for (int x = 0; x < board.getSizeX(); x++) {
        int livingCells = 0;
        for (Position border : GoLBorders.of(board, x, y).getPositions()) {
          if (board.getStatus(border.getPositionX(), border.getPositionY())) {
            livingCells++;
          }
        }

        if (!board.getStatus(x, y)) {
          if (livingCells == 3) {
            temporaryBoard.setStatus(x, y, true);
          }
        } else {
          if (livingCells <= 1 || livingCells > 3) {
            temporaryBoard.setStatus(x, y, false);
          }
        }
      }
    }

    board = temporaryBoard.copy();
    setChanged();
    notifyObservers();
  }

  // getter and setter for some variables

  public void setBoard(GoLBoard board) {
    this.board = board;
    setChanged();
    notifyObservers();
  }

  public GoLBoard getBoard() {
    return board;
  }

  public int getSizeX() {
    return board.getSizeX();
  }

  public int getSizeY() {
    return board.getSizeY();
  }

  public int getSizeXRotated() {
    return board.getSizeY();
  }

  public int getSizeYRotated() {
    return board.getSizeX();
  }

  public void setStatus(int x, int y, boolean status) {
    board.setStatus(x, y, status);
    setChanged();
    notifyObservers();
  }

  public boolean getStatus(int x, int y) {
    return board.getStatus(x, y);
  }

  // draw mode
  public void toggleDrawingMode() {
    draw = !draw;
    setChanged();
    notifyObservers();
  }

  public boolean isDrawingMode() {
    return draw;
  }

  // game speed modes
  public void startPause() {
    if (start) {
      start = false;
      timer.stop();
    } else {
      start = true;
      timer.start();
    }
    setChanged();
    notifyObservers();
  }

  public void slower() {
    delay += 100;
    timer.setDelay(delay);
    setChanged();
    notifyObservers();
  }

  public void faster() {
    if (delay >= 200) {
      delay -= 100;
      timer.setDelay(delay);
      setChanged();
      notifyObservers();
    }
  }

  public void resetDelay() {
    timer.setDelay(500);
    setChanged();
    notifyObservers();
  }

  // add figures
  public void addBlinker() {
    board.reset();
    board.setStatus(1, 2, true);
    board.setStatus(2, 2, true);
    board.setStatus(3, 2, true);
    setChanged();
    notifyObservers();
  }

  public void addGlider() {
    board.reset();
    board.setStatus(1, 3, true);
    board.setStatus(2, 3, true);
    board.setStatus(3, 3, true);
    board.setStatus(3, 2, true);
    board.setStatus(2, 1, true);
    setChanged();
    notifyObservers();
  }

  public void addGliderCannon() {
    board.reset();
    // square
    board.setStatus(1, 6, true);
    board.setStatus(2, 6, true);
    board.setStatus(1, 7, true);
    board.setStatus(2, 7, true);

    // head
    board.setStatus(16, 5, true);
    board.setStatus(14, 4, true);
    board.setStatus(13, 4, true);
    board.setStatus(12, 5, true);
    board.setStatus(11, 6, true);
    board.setStatus(11, 7, true);
    board.setStatus(11, 8, true);
    board.setStatus(12, 9, true);
    board.setStatus(13, 10, true);
    board.setStatus(14, 10, true);
    board.setStatus(15, 7, true); // eye
    board.setStatus(16, 9, true);
    board.setStatus(17, 6, true);
    board.setStatus(17, 7, true);
    board.setStatus(17, 8, true);
    board.setStatus(18, 7, true); // nose
    board.setStatus(21, 4, true);
    board.setStatus(21, 5, true);
    board.setStatus(21, 6, true);
    board.setStatus(22, 4, true);
    board.setStatus(22, 5, true);
    board.setStatus(22, 6, true);
    board.setStatus(23, 3, true);
    board.setStatus(23, 7, true);
    board.setStatus(25, 2, true);
    board.setStatus(25, 3, true);
    board.setStatus(25, 7, true);
    board.setStatus(25, 8, true);

    // cannon ball
    board.setStatus(35, 4, true);
    board.setStatus(35, 5, true);
    board.setStatus(36, 4, true);
    board.setStatus(36, 5, true);

    setChanged();
    notifyObservers();

  }
}

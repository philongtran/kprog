/**
 * This application simulates the game of life from conway.
 * 
 * 
 * 
 * @author Phi Long Tran <191624>
 * @author Manuel Wessner <191711>
 * @author Steve Nono <191709>
 *
 */
public class GameOfLife {

  public static int GOLWINDOWNUMBER = 1;

  /**
   * entry point of the program
   * 
   * @param args
   */

  public static void main(String[] args) {
    MainWindow mainWindow = new MainWindow();
    mainWindow.addChild(new GameSelectChildWindow(mainWindow), 0, 0);
    Konsole.run(mainWindow, 1024, 768);
  }

}

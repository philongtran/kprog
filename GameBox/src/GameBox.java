/**
 * This class is the main class
 * 
 * @author Phi Long Tran <191624>
 * @author Manuel Wessner <191711>
 * @author Steve Nono <191709>
 */
public class GameBox {

  public static void main(String[] args) {
    MainWindow mainWindow = new MainWindow();
    mainWindow.addChild(new GameSelectChildWindow(mainWindow), 10, 10);
    Konsole.run(mainWindow, 1280, 1024);
  }
}

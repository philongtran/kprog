
public class Quod {

  public static void main(String[] args) {
    MainWindow mainWindow = new MainWindow();
    mainWindow.addChild(new QuodFrame(new Game()), 10, 10);
    Konsole.run(mainWindow, 1024, 768);
  }
}

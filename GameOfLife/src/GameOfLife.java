
public class GameOfLife {

	public static int GOLWINDOWNUMBER = 1;

	public static void main(String[] args) {
		MainWindow mainWindow = new MainWindow();
		mainWindow.addChild(new GameSelectChildWindow(mainWindow), 0, 0);
		Konsole.run(mainWindow, 1024, 768);
	}

}

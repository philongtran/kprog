
public class GameOfLife {

	public static void main(String[] args) {
		// new Game(5, 5);
		MainWindow mainWindow = new MainWindow();
		mainWindow.addChild(new GameSelectChildWindow(mainWindow), 0, 0);
		Konsole.run(mainWindow, 1024, 768);
	}

}

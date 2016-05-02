
public class GameOfLife {

	public static void main(String[] args) {
		// new Game(5, 5);
		Menu menu = new Menu();
		menu.addChild(new GameSelect(menu), 0, 0);
		Konsole.run(menu, 1024, 768);
	}

}

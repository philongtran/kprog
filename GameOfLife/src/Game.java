import java.util.Observable;

public class Game extends Observable {

	Board board;

	public Game() {
		board = new Board(15, 10);
		setChanged();
		notifyObservers();
		new Display(board);
	}

	public void run() {
		for (int y = 0; y < board.getSizeY(); y++) {
			for (int x = 0; x < board.getSizeX(); x++) {

			}
		}
	}
}
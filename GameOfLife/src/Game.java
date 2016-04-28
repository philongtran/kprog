import java.util.Observable;

public class Game extends Observable {

	Board board;
	Position lu, u, ru, l, r, ld, d, rd, actualPosition;

	public Game() {
		board = new Board(15, 10);
		run();
		setChanged();
		notifyObservers();
		new Display(board);
	}

	private void run() {
		for (int y = 0; y < board.getSizeY(); y++) {
			for (int x = 0; x < board.getSizeX(); x++) {
				getBorderPosition(x, y);
			}
		}
	}

	private void getBorderPosition(int x, int y) {
		int newXLeft, newYUp, newXRight, newYDown;

		if (x - 1 < 0) {
			newXLeft = board.getSizeX() - 1;
		} else {
			newXLeft = x - 1;
		}
		if (y - 1 < 0) {
			newYUp = board.getSizeY() - 1;
		} else {
			newYUp = y - 1;
		}

		if (x + 1 >= board.getSizeX()) {
			newXRight = 0;
		} else {
			newXRight = x + 1;
		}
		if (y + 1 >= board.getSizeY()) {
			newYDown = 0;
		} else {
			newYDown = y + 1;
		}

		lu = new Position(newXLeft, newYUp);
		u = new Position(x, newYUp);
		ru = new Position(newXRight, newYUp);
		l = new Position(newXLeft, y);
		r = new Position(newXRight, y);
		ld = new Position(newXLeft, newYDown);
		d = new Position(x, newYDown);
		rd = new Position(newXRight, newYDown);
		/*
		 * System.out.println(lu); System.out.println(u);
		 * System.out.println(ru); System.out.println(l); System.out.println(r);
		 * System.out.println(ld); System.out.println(d);
		 * System.out.println(rd);
		 */
	}
}
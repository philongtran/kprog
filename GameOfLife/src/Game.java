import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.concurrent.TimeUnit;

import javax.swing.Timer;

public class Game extends Observable {

	private Board board;
	private Board temporaryBoard;
	private Position lu, u, ru, l, r, ld, d, rd;
	private Position[] actualBorders = new Position[8];
	private int livingCells;
	private final static int ONESECOND = (int) TimeUnit.SECONDS.toMillis(1);

	public Game(int sizeX, int sizeY) {
		board = new Board(sizeX, sizeY);
		temporaryBoard = new Board(sizeX, sizeY);
		board.setStatus(1, 2, true);
		board.setStatus(2, 2, true);
		board.setStatus(3, 2, true);
		Display display = new Display(board);

		ActionListener taskPerformer = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				run();
			}
		};
		new Timer(ONESECOND, taskPerformer).start();
		this.addObserver(display);
	}

	private void run() {
		for (int y = 0; y < board.getSizeY(); y++) {
			for (int x = 0; x < board.getSizeX(); x++) {
				temporaryBoard.setStatus(x, y, board.getStatus(x, y));
			}
		}

		for (int y = 0; y < board.getSizeY(); y++) {
			for (int x = 0; x < board.getSizeX(); x++) {
				getBorderPosition(x, y);
				for (int i = 0; i < 8; i++) {
					if (board.getStatus(actualBorders[i].getPositionX(), actualBorders[i].getPositionY())) {
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
				livingCells = 0;
			}
		}
		for (int y = 0; y < board.getSizeY(); y++) {
			for (int x = 0; x < board.getSizeX(); x++) {
				board.setStatus(x, y, temporaryBoard.getStatus(x, y));
			}
		}
		setChanged();
		notifyObservers();
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

		actualBorders[0] = lu;
		actualBorders[1] = u;
		actualBorders[2] = ru;
		actualBorders[3] = l;
		actualBorders[4] = r;
		actualBorders[5] = ld;
		actualBorders[6] = d;
		actualBorders[7] = rd;
	}
}
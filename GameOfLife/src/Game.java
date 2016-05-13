import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.Timer;

public class Game extends Observable {

	private Board board;
	private Board temporaryBoard;
	private Position lu, u, ru, l, r, ld, d, rd;
	private Position[] actualBorders = new Position[8];
	// private int livingCells;
	GoLChildWindow golCildWindow;
	Timer timer;
	boolean start = true;
	// timer which sends an action every 1sec.
	int delay = 500; // milliseconds

	MainWindow mydesk;// Referenz auf Hauptfenster

	public Game(MainWindow dft, int sizeX, int sizeY) {
		mydesk = dft;
		// GoLMenu golMenu = new GoLMenu(mydesk, this);
		// mydesk.addChild(golMenu, 0, 0);
		board = new Board(sizeX, sizeY);
		temporaryBoard = new Board(sizeX, sizeY);
		// Display display = new Display(board);

		ActionListener taskPerformer = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				run();
			}
		};
		timer = new Timer(delay, taskPerformer);
		timer.start();

		// this.addObserver(display);
	}

	private void run() {
		int livingCells = 0;
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

	public int getSizeX() {
		return board.getSizeX();
	}

	public int getSizeY() {
		return board.getSizeY();
	}

	public void setStatus(int x, int y, boolean status) {
		board.setStatus(x, y, status);
		setChanged();
		notifyObservers();
	}

	public boolean getStatus(int x, int y) {
		return board.getStatus(x, y);
	}

	public void getObserver(GoLChildWindow golMenu) {
		this.golCildWindow = golMenu;
		this.addObserver(golMenu);
	}

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

	public void addBlinker() {
		board.resetBoard();
		board.setStatus(1, 2, true);
		board.setStatus(2, 2, true);
		board.setStatus(3, 2, true);
		setChanged();
		notifyObservers();
	}

	public void addGlider() {
		board.resetBoard();
		board.setStatus(1, 3, true);
		board.setStatus(2, 3, true);
		board.setStatus(3, 3, true);
		board.setStatus(3, 2, true);
		board.setStatus(2, 1, true);
		setChanged();
		notifyObservers();
	}
}
import java.util.Observable;
import java.util.concurrent.TimeUnit;

import javax.swing.Timer;

public class Game extends Observable {

	private Board board;
	private Board temporaryBoard;
	private Position leftUpperCorner, upperCorner, rightUpperCorner, leftCorner, rightCorner, leftBottomCorner,
			bottomCorner, rightBottomCorner;
	private Position[] actualBorders = new Position[8];
	// private int livingCells;
	public GoLChildWindow golCildWindow;
	private Timer timer;
	private boolean start = true;
	// timer which sends an action every 1sec.
	private int delay = 500; // milliseconds
	public int golWindowNumber = GameOfLife.GOLWINDOWNUMBER;
	private boolean running = true;

	MainWindow mydesk;// Referenz auf Hauptfenster
	private final static int ONESECOND = (int) TimeUnit.SECONDS.toMillis(1);

	public Game(MainWindow dft, int sizeX, int sizeY) {
		mydesk = dft;
		// GoLMenu golMenu = new GoLMenu(mydesk, this);
		// mydesk.addChild(golMenu, 0, 0);
		board = new Board(sizeX, sizeY);
		temporaryBoard = new Board(sizeX, sizeY);
		// Display display = new Display(board);
		// this.addObserver(display);
		timer = new Timer(ONESECOND, taskPerformer -> {
			run();
		});
		timer.start();
	}

	private void run() {
		cloneBoard();

		int livingCells = 0;
		for (int y = 0; y < board.getSizeY(); y++) {
			for (int x = 0; x < board.getSizeX(); x++) {
				calculateBorders(x, y);
				for (int i = 0; i < actualBorders.length; i++) {
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

	private void cloneBoard() {
		for (int y = 0; y < board.getSizeY(); y++) {
			for (int x = 0; x < board.getSizeX(); x++) {
				temporaryBoard.setStatus(x, y, board.getStatus(x, y));
			}
		}
	}

	private void calculateBorders(int x, int y) {
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

		leftUpperCorner = new Position(newXLeft, newYUp);
		upperCorner = new Position(x, newYUp);
		rightUpperCorner = new Position(newXRight, newYUp);
		leftCorner = new Position(newXLeft, y);
		rightCorner = new Position(newXRight, y);
		leftBottomCorner = new Position(newXLeft, newYDown);
		bottomCorner = new Position(x, newYDown);
		rightBottomCorner = new Position(newXRight, newYDown);

		actualBorders[0] = leftUpperCorner;
		actualBorders[1] = upperCorner;
		actualBorders[2] = rightUpperCorner;
		actualBorders[3] = leftCorner;
		actualBorders[4] = rightCorner;
		actualBorders[5] = leftBottomCorner;
		actualBorders[6] = bottomCorner;
		actualBorders[7] = rightBottomCorner;
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

	public void getObserverLeft(GoLViewLeft golMenu) {
		// this.golCildWindow = golMenu;
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
		running = !running;
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

	public boolean isRunning() {
		return running;
	}
}
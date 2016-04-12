import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MAX extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton[] buttons = new JButton[64];

	private static final String HELP_FORMAT = "%-12s | %-12s | %-12s | %-12s |\n%-12s | %-12s | %-12s | %-12s |";
	private final int SCORE_LIMIT = 105;
	private final int PLAYER_COUNT;
	private final int BOARD_SIZE_X;
	private final int BOARD_SIZE_Y;
	private Player[] player;
	private Board board;
	private Board clonedBoard;
	private PlayerPosition playerPosition;
	private Display display;
	private boolean restart;

	public MAX(int boardSizeX, int boardSizeY, int playerCount) {
		this.BOARD_SIZE_X = boardSizeX;
		this.BOARD_SIZE_Y = boardSizeY;
		this.PLAYER_COUNT = playerCount;
		this.player = new Player[playerCount];
		this.restart = false;

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800, 800);
		setLayout(new GridLayout(8, 8));
		for (int i = 0; i < 64; i++) {
			buttons[i] = new JButton("" + i);
			getContentPane().add(buttons[i]);
		}
		setTitle("MAXGUI");
		setVisible(true);

		try {
			initializeGame();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void initializeGame() throws CloneNotSupportedException {
		// set starting positions
		playerPosition = new PlayerPosition(BOARD_SIZE_X, BOARD_SIZE_Y, PLAYER_COUNT);
		int[][] position = playerPosition.getPosition();
		// create players with starting location and color
		for (int i = 0; i < PLAYER_COUNT; i++) {
			player[i] = new Player(position[i][0], position[i][1], (i + 1) * (-1));
		}
		try {
			// restart game logic
			if (restart) {
				restart = false;
				board = clonedBoard.clone();
			} else {
				// initialize the board and its size
				board = new Board(BOARD_SIZE_X, BOARD_SIZE_Y);
				clonedBoard = board.clone();
			}
		} catch (CloneNotSupportedException e) {
			IO.writeln("error in cloning");
		}
		// plants the players on the board
		for (int i = 0; i < player.length; i++) {
			board.setPlayer(player[i].getX(), player[i].getY(), player[i].getColor());
		}
		// displays score and board
		this.display = new Display(player, board, buttons);
		display.draw(-1, checkScore());
	}

	private boolean checkScore() {
		boolean scoreReached = false;
		for (int i = 0; i < player.length; i++) {
			if (player[i].getScore() >= SCORE_LIMIT) {
				scoreReached = true;
			}
		}
		return scoreReached;
	}

	public void run() throws Exception {
		initializeGame();

		// runs while players are below score
		while (!checkScore()) {

			for (int i = 0; i < player.length; i++) {
				// checks if score limit is reached
				if (checkScore()) {
					break;
				}
				// reads keyboard input to move the active player
				Action action = Action.of(IO.promptAndRead("i: ").toLowerCase().substring(0, 1));
				// temporary variable to hold current player
				Player currentPlayer = player[i];
				// cases which are allowed
				switch (action) {
				case UP:
					if (canMoveInDirection(currentPlayer, action)) {
						move(currentPlayer, action);
					} else {
						i = playerRetry(i);
					}
					break;
				case DOWN:
					if (canMoveInDirection(currentPlayer, action)) {
						move(currentPlayer, action);
					} else {
						i = playerRetry(i);
					}
					break;
				case LEFT:
					if (canMoveInDirection(currentPlayer, action)) {
						move(currentPlayer, action);
					} else {
						i = playerRetry(i);
					}
					break;
				case RIGHT:
					if (canMoveInDirection(currentPlayer, action)) {
						move(currentPlayer, action);
					} else {
						i = playerRetry(i);
					}
					break;
				case HELP:
					String helpText = String.format(HELP_FORMAT, "Up: w", "Down: s", "Left: a", "Right: d",
							"Restart: r", "New game: n", "Quit: q", "Help: h");
					IO.writeln(helpText);
					IO.promptAndRead("Press any key to continue.");
					i = playerRetry(i);
					break;
				case RESTART:
					restart = true;
					initializeGame();
					i = -1;
					break;
				case NEW:
					initializeGame();
					i = -1;
					break;
				case QUIT:
					return;
				default:
					i = playerRetry(i);
					break;
				}
				// displays score and board
				display.draw(i, checkScore());
			}
		}
		restart = IO.promptAndRead("again? Type Y for yes or N for no").toLowerCase().substring(0, 1).equals("y");
		if (restart) {
			run();
		}
		return;
	}

	private boolean canMoveInDirection(Player player, Action direction) {
		return isOutOfBounds(player, direction) && isColliding(player, direction);
	}

	private boolean isOutOfBounds(Player player, Action direction) {
		switch (direction) {
		case LEFT:
			return player.getX() > 0;
		case RIGHT:
			return player.getX() + 1 < board.getSizeX();
		case DOWN:
			return player.getY() + 1 < board.getSizeY();
		case UP:
			return player.getY() > 0;
		default:
			return false;
		}
	}

	private int playerRetry(int playerID) {
		if (playerID == 0) {
			return player.length - 1;
		} else {
			return --playerID;
		}
	}

	private boolean isColliding(Player player, Action direction) {
		int x = player.getX();
		int y = player.getY();
		switch (direction) {
		case LEFT:
			return board.getValue(x - 1, y) >= 0;
		case RIGHT:
			return board.getValue(x + 1, y) >= 0;
		case DOWN:
			return board.getValue(x, y + 1) >= 0;
		case UP:
			return board.getValue(x, y - 1) >= 0;
		default:
			return false;
		}
	}

	private void move(Player currentPlayer, Action direction) {
		removePlayerFromPreviousPosition(currentPlayer);
		switch (direction) {
		case DOWN:
			currentPlayer.moveDown();
			break;
		case LEFT:
			currentPlayer.moveLeft();
			break;
		case RIGHT:
			currentPlayer.moveRight();
			break;
		case UP:
			currentPlayer.moveUp();
			break;
		default:
			break;
		}
		addScoreAndSetNewPosition(currentPlayer);
	}

	private void removePlayerFromPreviousPosition(Player player) {
		board.setPlayer(player.getX(), player.getY(), 0);
	}

	private void addScoreAndSetNewPosition(Player currentPlayer) {
		// add the value of the board to the score
		int x = currentPlayer.getX();
		int y = currentPlayer.getY();

		currentPlayer.addScore(board.getValue(x, y));
		// marks the field to be player owned
		board.setPlayer(x, y, currentPlayer.getColor());
	}

}

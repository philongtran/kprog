import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This class represents the game.
 * 
 * @author Phi Long Tran <191624>
 * @author Manuel Wessner <191711>
 * @author Steve Nono <191709>
 *
 */

public class Game extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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

	private JButton[] buttons;
	private JButton[] buttons2;

	/**
	 * The constructor of the game.
	 * 
	 * @param boardSizeX
	 *            - X size of the board
	 * @param boardSizeY
	 *            - Y size of the board
	 * @param playerCount
	 *            - Amount of players
	 */
	public Game(int boardSizeX, int boardSizeY, int playerCount) {

		this.BOARD_SIZE_X = boardSizeX;
		this.BOARD_SIZE_Y = boardSizeY;
		this.PLAYER_COUNT = playerCount;
		this.player = new Player[playerCount];
		this.restart = false;

		buttons = new JButton[64];
		buttons2 = new JButton[4];
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800, 800);
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(8, 8));
		for (int i = 0; i < 64; i++) {
			buttons[i] = new JButton("" + i);
			panel1.add(buttons[i]);
		}
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(1, 4));
		for (int i = 0; i < 4; i++) {
			buttons2[i] = new JButton("" + i);
			panel2.add(buttons2[i]);
		}

		setLayout(new GridLayout(2, 1));
		add(panel1);
		add(panel2);
		setTitle("MAXGUI");
		setVisible(true);

		try {
			initializeGame();
			// run();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Method for running the game logic. It contains the movement and score-
	 * and board display.
	 * 
	 * @throws Exception
	 */
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

	/**
	 * Initializes the game with its default values.
	 * 
	 * @throws CloneNotSupportedException
	 */
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

	/**
	 * Gets the old position of the player and sets the value inside the board
	 * to 0.
	 * 
	 * @param player
	 *            - Which player position should be reset to 0
	 */
	private void removePlayerFromPreviousPosition(Player player) {
		board.setPlayer(player.getX(), player.getY(), 0);
	}

	/**
	 * Checking if player tries to move out of bounds.
	 * 
	 * @param player
	 *            - Which player should be tested
	 * @param direction
	 *            - Which direction does the player want to go
	 * @return - Returns boolean true if move is legitimate
	 */
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

	/**
	 * Checking player movement for collision with other players.
	 * 
	 * @param player
	 *            - Which player should be tested
	 * @param direction
	 *            - Which direction does the player want to go
	 * @return - Returns boolean true if move is legitimate
	 */
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

	/**
	 * Checks for out of bounds and player collision problems.
	 * 
	 * @param player
	 *            - Which player should be tested
	 * @param direction
	 *            - Which direction does the player want to go
	 * @return - Returns boolean true if move is legitimate
	 */
	private boolean canMoveInDirection(Player player, Action direction) {
		return isOutOfBounds(player, direction) && isColliding(player, direction);
	}

	/**
	 * Returns to the same player if move was illegal.
	 * 
	 * @param playerID
	 *            - ID of the wrongly assumed player.
	 * @return - Returns the old player ID
	 */
	private int playerRetry(int playerID) {
		if (playerID == 0) {
			return player.length - 1;
		} else {
			return --playerID;
		}
	}

	/**
	 * Makes the calculation of the score and sets the players new position.
	 * 
	 * @param currentPlayer
	 *            - player whos turn it is
	 */
	private void addScoreAndSetNewPosition(Player currentPlayer) {
		// add the value of the board to the score
		int x = currentPlayer.getX();
		int y = currentPlayer.getY();

		currentPlayer.addScore(board.getValue(x, y));
		// marks the field to be player owned
		board.setPlayer(x, y, currentPlayer.getColor());
	}

	/**
	 * Checks if the score limit is reached.
	 * 
	 * @return - Boolean true if score limit is reached
	 */
	private boolean checkScore() {
		boolean scoreReached = false;
		for (int i = 0; i < player.length; i++) {
			if (player[i].getScore() >= SCORE_LIMIT) {
				scoreReached = true;
			}
		}
		return scoreReached;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}

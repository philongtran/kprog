/**
 * This class represents the game.
 * 
 * @author Phi Long Tran <191624>
 * @author Manuel Wessner <191711>
 * @author Steve Nono <191709>
 *
 */

public class Game {

	/**
	 * Instance variables
	 */
	private final int SCORE_LIMIT = 105;
	public final int PLAYER_COUNT;
	private final int BOARD_SIZE_X;
	private final int BOARD_SIZE_Y;
	private Player[] player;
	private Board board;
	private Board clonedBoard;
	private PlayerPosition playerPosition;
	private Display display;
	private boolean restart;

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

		try {
			initializeGame();
			// run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Bind player movement to enum
	 * 
	 * @param currentPlayer
	 * @param direction
	 */
	public void move(Player currentPlayer, Action direction) {
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
		this.display = new Display(player, board);
		display.setGame(this);
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
	public boolean canMoveInDirection(Player player, Action direction) {
		return isOutOfBounds(player, direction) && isColliding(player, direction);
	}

	/**
	 * Returns to the same player if move was illegal.
	 * 
	 * @param playerID
	 *            - ID of the wrongly assumed player.
	 * @return - Returns the old player ID
	 */
	public int playerRetry(int playerID) {
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
	public boolean checkScore() {
		boolean scoreReached = false;
		for (int i = 0; i < player.length; i++) {
			if (player[i].getScore() >= SCORE_LIMIT) {
				scoreReached = true;
			}
		}
		return scoreReached;
	}
}

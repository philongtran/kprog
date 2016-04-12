import java.util.Random;

/**
 * This class represents the board of the game.
 * 
 * @author Phi Long Tran <191624>
 * @author Manuel Wessner <191711>
 * @author Steve Nono <191709>
 *
 */

public class Board implements Cloneable {
	private int minimumNumber; // minimum number which can appear on the board
	private int maximumNumber; // maximum number which can appear on the board
	private final int SIZE_X; // size of the x side board
	private final int SIZE_Y; // size of the y side board
	private int[][] board; // board itself
	private Random random; // random number generator
	private Board clone; // clone for the board

	/**
	 * The Constructor creates a board of the size x,y and initializes it.
	 * 
	 * @param sizeX
	 *            - Sets the size of the x part
	 * @param sizeY
	 *            - Sets the size of the y part
	 * 
	 */
	public Board(int sizeX, int sizeY) {
		this.minimumNumber = 1;
		this.maximumNumber = 9;
		this.SIZE_X = sizeX;
		this.SIZE_Y = sizeY;
		this.board = new int[sizeY][sizeX];
		this.random = new Random();
		this.initialize();
	}

	/**
	 * Initializes the fields of the board with random numbers.
	 */
	public void initialize() {
		for (int y = 0; y < SIZE_Y; y++) {
			for (int x = 0; x < SIZE_X; x++) {
				board[y][x] = random.nextInt((maximumNumber - minimumNumber) + 1) + minimumNumber;
			}
		}
	}

	/**
	 * Assigns a value to the x,y coordinate.
	 * 
	 * @param x
	 *            - Selects the x part of the board
	 * @param y
	 *            - Selects the y part of the board
	 * @param value
	 *            - Enter the value in the x,y coordinate
	 * 
	 */
	public void setPlayer(int x, int y, int value) {
		this.board[y][x] = value;
	}

	/**
	 * Getting a specific value in the x,y coordinate.
	 * 
	 * @param x
	 *            - X coordinate of the board
	 * @param y
	 *            - Y coordinate of the board
	 * @return - Returns the value in the x,y coordinate
	 * 
	 */
	public int getValue(int x, int y) {
		return this.board[y][x];
	}

	/**
	 * Returns the x size of the board.
	 * 
	 * @return - Returns the x size of the board
	 * 
	 */
	public int getSizeX() {
		return this.SIZE_X;
	}

	/**
	 * Returns the y size of the board.
	 * 
	 * @return - Returns the y size of the board
	 * 
	 */
	public int getSizeY() {
		return this.SIZE_Y;
	}

	/**
	 * Returns the cloned board.
	 * 
	 * @return - Returns the cloned board
	 * 
	 */
	@Override
	public Board clone() throws CloneNotSupportedException {
		try {
			clone = (Board) super.clone();
			clone.board = new int[SIZE_Y][SIZE_X];
			for (int y = 0; y < SIZE_Y; y++) {
				for (int x = 0; x < SIZE_X; x++) {
					clone.setPlayer(x, y, this.getValue(x, y));
				}
			}
		} catch (CloneNotSupportedException e) {
			IO.writeln("error in cloning");
		}
		return clone;
	}
}

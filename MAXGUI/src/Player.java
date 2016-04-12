/**
 * This class represents the player of the game.
 * 
 * @author Phi Long Tran <191624>
 * @author Manuel Wessner <191711>
 * @author Steve Nono <191709>
 *
 */

public class Player {

	private final int COLOR; // color of the player (-1 = black; -2 = white)
	private int x; // x coordinate of the player
	private int y; // y coordinate of the player
	private int score; // score of the player

	/**
	 * The constructor of the class to initialize the player.
	 * 
	 * @param x
	 *            - X coordinate of the player
	 * @param y
	 *            - Y coordinate of the player
	 * @param color
	 *            - Color of the player (negative number e.g. -1 = black; -2 =
	 *            white)
	 * 
	 */
	public Player(int x, int y, int color) {
		this.COLOR = color;
		this.x = x;
		this.y = y;
		this.score = 0;
	}

	/**
	 * Moves the player up one field.
	 */
	public void moveUp() {
		y--;
	}

	/**
	 * Moves the player down one field.
	 */
	public void moveDown() {
		y++;
	}

	/**
	 * Moves the player left one field.
	 */
	public void moveLeft() {
		x--;
	}

	/**
	 * Moves the player right one field.
	 */
	public void moveRight() {
		x++;
	}

	/**
	 * Returns the x coordinate of the player.
	 * 
	 * @return - Returns the x coordinate of the player
	 */
	public int getX() {
		return x;
	}

	/**
	 * Returns the y coordinate of the player.
	 * 
	 * @return - Returns the y coordinate of the player
	 */
	public int getY() {
		return y;
	}

	/**
	 * Returns the color of the player.
	 * 
	 * @return - Returns the color of the player
	 */
	public int getColor() {
		return COLOR;
	}

	/**
	 * Add the value of the score to the players score.
	 * 
	 * @param score
	 *            - Add the value of score to the players score
	 * 
	 */
	public void addScore(int score) {
		this.score += score;
	}

	/**
	 * Returns the score of the player.
	 * 
	 * @return - Returns the score of the player
	 * 
	 */
	public int getScore() {
		return score;
	}

}

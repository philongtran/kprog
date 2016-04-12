/**
 * Calculation of the starting position of the players.
 *
 * @author Manuel Wessner <191711>
 * @author Phi Long Tran <191624>
 * @author Steve Nono <191709>
 */

public class PlayerPosition {

	private int[][] position;
	private int p1x;
	private int p1y;
	private int p2x;
	private int p2y;
	private int p3x;
	private int p3y;
	private int p4x;
	private int p4y;
	private int boardDimensionX;
	private int boardDimensionY;

	/**
	 * Calculates the starting positions of the players.
	 * 
	 * @param playerCount
	 *            - Amount of players
	 * @param boardDimensionX
	 *            - X size of the board
	 * @param boardDimensionY
	 *            - Y size of the board
	 */
	public PlayerPosition(int boardDimensionX, int boardDimensionY, int playerCount) {
		this.boardDimensionX = boardDimensionX;
		this.boardDimensionY = boardDimensionY;

		switch (playerCount) {
		case 1:
			onePlayer();
			position = new int[][] { { p1x, p1y } };
			break;
		case 2:
			twoPlayers();
			position = new int[][] { { p1x, p1y }, { p2x, p2y } };
			break;
		case 3:
			threePlayers();
			position = new int[][] { { p1x, p1y }, { p2x, p2y }, { p3x, p3y } };
			break;
		case 4:
			fourPlayers();
			position = new int[][] { { p1x, p1y }, { p2x, p2y }, { p3x, p3y }, { p4x, p4y } };
			break;
		default:
			IO.writeln("error in creating player positions");
			break;
		}
	}

	/**
	 * Returns the starting positions.
	 * 
	 * @return - Returns the starting positions as a 2d array
	 */
	public int[][] getPosition() {
		return position;
	}

	/**
	 * Calculates starting position for one player.
	 */
	private void onePlayer() {
		p1x = boardDimensionX / 2 - 1;
		p1y = boardDimensionY / 2;
	}

	/**
	 * Calculates starting position for two players.
	 */
	private void twoPlayers() {
		onePlayer();
		p2x = boardDimensionX / 2;
		p2y = boardDimensionY / 2 - 1;
	}

	/**
	 * Calculates starting position for three players.
	 */
	private void threePlayers() {
		twoPlayers();
		p3x = boardDimensionX / 2 - 1;
		p3y = boardDimensionX / 2 - 1;
	}

	/**
	 * Calculates starting position for four players.
	 */
	private void fourPlayers() {
		threePlayers();
		p4x = boardDimensionX / 2;
		p4y = boardDimensionX / 2;
	}

}

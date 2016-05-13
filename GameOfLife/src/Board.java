
public class Board {

	private boolean[][] board;
	private int sizeX;
	private int sizeY;

	public Board(int sizeX, int sizeY) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		board = new boolean[sizeY][sizeX];
		resetBoard();
	}

	public void resetBoard() {
		for (int y = 0; y < sizeY; y++) {
			for (int x = 0; x < sizeX; x++) {
				board[y][x] = false;
				// System.out.print(board[y][x] + ", ");
			}
			// System.out.println();
		}

	}

	public int getSizeX() {
		return sizeX;
	}

	public int getSizeY() {
		return sizeY;
	}

	public void setStatus(int x, int y, boolean status) {
		board[y][x] = status;
	}

	public boolean getStatus(int x, int y) {
		return board[y][x];
	}

}

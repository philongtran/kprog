
public class Borders {

	private final Position leftUpperCorner, upperCorner, rightUpperCorner, leftCorner, rightCorner, leftBottomCorner,
			bottomCorner, rightBottomCorner;

	Borders(Position leftUpperCorner, Position upperCorner, Position rightUpperCorner, Position leftCorner,
			Position rightCorner, Position leftBottomCorner, Position bottomCorner, Position rightBottomCorner) {
		this.leftUpperCorner = leftUpperCorner;
		this.upperCorner = upperCorner;
		this.rightUpperCorner = rightUpperCorner;
		this.leftCorner = leftCorner;
		this.rightCorner = rightCorner;
		this.leftBottomCorner = leftBottomCorner;
		this.bottomCorner = bottomCorner;
		this.rightBottomCorner = rightBottomCorner;
	}

	/**
	 * Based on board and cell position a new instance borders will be created
	 * 
	 * @param board
	 * @param x
	 *            - x position of cell, which the borders should be calculated
	 * @param y
	 *            - y position of cell, which the borders should be calculated
	 * @return new instance of Borders
	 */
	public static Borders of(Board board, int x, int y) {
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

		Position leftUpperCorner = new Position(newXLeft, newYUp);
		Position upperCorner = new Position(x, newYUp);
		Position rightUpperCorner = new Position(newXRight, newYUp);
		Position leftCorner = new Position(newXLeft, y);
		Position rightCorner = new Position(newXRight, y);
		Position leftBottomCorner = new Position(newXLeft, newYDown);
		Position bottomCorner = new Position(x, newYDown);
		Position rightBottomCorner = new Position(newXRight, newYDown);
		Borders borders = new Borders(leftUpperCorner, upperCorner, rightUpperCorner, leftCorner, rightCorner,
				leftBottomCorner, bottomCorner, rightBottomCorner);
		return borders;
	}

	public Position getLeftUpperCorner() {
		return leftUpperCorner;
	}

	public Position getUpperCorner() {
		return upperCorner;
	}

	public Position getRightUpperCorner() {
		return rightUpperCorner;
	}

	public Position getLeftCorner() {
		return leftCorner;
	}

	public Position getRightCorner() {
		return rightCorner;
	}

	public Position getLeftBottomCorner() {
		return leftBottomCorner;
	}

	public Position getBottomCorner() {
		return bottomCorner;
	}

	public Position getRightBottomCorner() {
		return rightBottomCorner;
	}

}

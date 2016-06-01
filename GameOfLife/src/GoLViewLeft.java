import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;

class GoLViewLeft extends GoLChildWindow implements Observer {
	private static final long serialVersionUID = 1L;
	private JButton[][] buttons;

	public GoLViewLeft(MainWindow dft, Game game, int offset) {
		super(dft, game);
		setLeftOffset(offset + 1);
		createButtons();
	}

	@Override
	protected void createCells() {
		// do nothing - createButtons should be called later
	}

	private void createButtons() {
		Game game = getGame();
		buttons = new JButton[game.getSizeX()][game.getSizeY()];
		for (int y = 0; y < game.getSizeY(); y++) {
			for (int x = 0; x < game.getSizeX(); x++) {
				int xPosition = x + getLeftOffset();
				JButton button = new JButton(xPosition + "," + y);
				buttons[x][y] = button;
				add(button);

				// ignore clicks outside of game
				if (xPosition < getGame().getSizeX()) {
					addButtonActions(button);
				}
			}
		}
		setButtonBackgroundColor();
	}

	@Override
	public void update(Observable o, Object arg) {
		setButtonBackgroundColor();
	}

	private void setButtonBackgroundColor() {
		for (int y = 0; y < getGame().getSizeY(); y++) {
			for (int x = 0; x < getGame().getSizeX(); x++) {
				int currentIndex = x - getLeftOffset();
				int xIndex = getButtonIndex(currentIndex);
				JButton button = buttons[xIndex][y];
				if (currentIndex >= 0 && getGame().getStatus(x, y)) {
					button.setBackground(getAliveColor());
					button.setForeground(getAliveColor());
				} else {
					button.setBackground(getDeadColor());
					button.setForeground(getDeadColor());
				}
			}
		}
	}

	private int getButtonIndex(int currentIndex) {
		if (currentIndex < 0) {
			return 0;
		}
		return currentIndex;
	}

	@Override
	protected JButton[][] getButtons() {
		return buttons;
	}

}
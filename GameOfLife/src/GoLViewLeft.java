import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;

class GoLViewLeft extends GoLChildWindow implements Observer {
	private static final long serialVersionUID = 1L;
	private final int offset;
	private JButton[][] buttons;

	public GoLViewLeft(MainWindow dft, Game game, int offset) {
		super(dft, game);
		this.offset = offset + 1;
		createButtons();
	}

	@Override
	protected int getLeftOffset() {
		return offset;
	}

	@Override
	protected void createCells() {
		// do nothing
	}

	private void createButtons() {
		Game game = getGame();
		buttons = new JButton[game.getSizeX()][game.getSizeY()];
		for (int y = 0; y < game.getSizeY(); y++) {
			for (int x = 0; x < game.getSizeX(); x++) {
				JButton button = new JButton(x + "," + y);
				buttons[x][y] = button;
				add(button);
				button.addActionListener(cellButtonClickListenerEvent -> {
					onCellButtonClick(cellButtonClickListenerEvent);
				});
			}
		}
		setButtonBackgroundColor();
	}

	@Override
	public void update(Observable o, Object arg) {
		setButtonBackgroundColor();
	}

	private void setButtonBackgroundColor() {
		setAllRed();

		for (int y = 0; y < getGame().getSizeY(); y++) {
			for (int x = 0; x < getGame().getSizeX(); x++) {
				int currentIndex = x - getLeftOffset();
				int xIndex = getButtonIndex(currentIndex);
				if (currentIndex >= 0 && getGame().getStatus(x, y)) {
					buttons[xIndex][y].setBackground(Color.GREEN);
					buttons[xIndex][y].setForeground(Color.GREEN);
				}
			}
		}
	}

	private void setAllRed() {
		for (int y = 0; y < getGame().getSizeY(); y++) {
			for (int x = 0; x < getGame().getSizeX(); x++) {
				buttons[x][y].setBackground(Color.RED);
			}
		}
	}

	private int getButtonIndex(int currentIndex) {
		if (currentIndex < 0) {
			return 0;
		}
		return currentIndex;
	}

}
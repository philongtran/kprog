import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

class GoLChildWindow extends JInternalFrame implements Observer {

	private static final long serialVersionUID = 1L;
	private JButton[][] buttons;
	private Game game;
	private int leftOffset;

	MainWindow mydesk;// Referenz auf Hauptfenster

	public GoLChildWindow(MainWindow dft, Game game) { // Konstruktor
		super("Game of Life " + game.golWindowNumber, true, true);// vergroesserbar,
																	// schliessbar
		// setBackground(col[nr % col.length]);// Start-Farbe
		mydesk = dft;// Hauptfenster merken
		// cp.setLayout(new FlowLayout());// FlowLayout
		setIconifiable(true);
		setMaximizable(true);
		setClosable(true);// weitere Parameter setzen
		this.game = game;
		createMenu();
		createFrame();
		createCells();
	} // end Konstruktor

	private void createMenu() {
		JMenu[] menus = { new JMenu("Modus"), new JMenu("Geschwindigkeit"), new JMenu("Fenster"),
				new JMenu("Figuren") };
		JMenuItem[] menuItems = { MenuAction.START_STOP.asMenuItem(), MenuAction.EXIT.asMenuItem(),
				MenuAction.FASTER.asMenuItem(), MenuAction.SLOWER.asMenuItem(), MenuAction.RESET.asMenuItem(),
				MenuAction.LEFTVIEW.asMenuItem(), MenuAction.RIGHTVIEW.asMenuItem(),
				MenuAction.VIEWUPSIDEDOWN.asMenuItem(), MenuAction.BLINKER.asMenuItem(), MenuAction.GLIDER.asMenuItem(),
				MenuAction.GLIDERCANNON.asMenuItem() };

		for (int i = 0; i < menuItems.length; i++) {
			menus[(i < 2) ? 0 : (i < 5) ? 1 : (i < 8) ? 2 : 3].add(menuItems[i]);
			menuItems[i].addActionListener(menuItemClickEvent -> {
				onMenuItemClick(menuItemClickEvent);
			});
		}

		JMenuBar mb = new JMenuBar();
		for (int i = 0; i < menus.length; i++) {
			mb.add(menus[i]);
		}
		setJMenuBar(mb);
	}

	private void onMenuItemClick(ActionEvent e) {
		JMenuItem item = (JMenuItem) e.getSource();
		switch (MenuAction.of(item.getActionCommand())) {
		case BLINKER:
			game.addBlinker();
			break;
		case EXIT:
			System.exit(0);
			break;
		case FASTER:
			game.faster();
			break;
		case GLIDER:
			game.addGlider();
			break;
		case GLIDERCANNON:
			game.addGliderCannon();
			break;
		case LEFTVIEW:
			GoLViewLeft golChildWindow = new GoLViewLeft(mydesk, game, getLeftOffset());
			mydesk.addChildGoL(golChildWindow, GameSelectChildWindow.xpos, GameSelectChildWindow.ypos, 800, 600);
			game.getObserverLeft(golChildWindow);
			GameSelectChildWindow.xpos += 20;
			GameSelectChildWindow.ypos += 20;
			break;
		case RESET:
			game.resetDelay();
			break;
		case RIGHTVIEW:
			break;
		case SLOWER:
			game.slower();
			break;
		case START_STOP:
			game.startPause();
			break;
		case VIEWUPSIDEDOWN:
			break;
		default:
			break;
		}
	}

	private void createFrame() {
		Container cp = getContentPane();// Fenster-Container
		cp.setSize(game.getSizeX() * 60, game.getSizeY() * 60);
		cp.setLayout(new GridLayout(game.getSizeY(), game.getSizeX()));
	}

	protected void createCells() {
		createButtons();
		setButtonBackgroundColor();
	}

	private void createButtons() {
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
	}

	protected void onCellButtonClick(ActionEvent e) {
		boolean x = true;
		String coordinates = e.getActionCommand();
		String spositionX = "";
		String spositionY = "";

		int positionX;
		int positionY;

		for (int i = 0; i < coordinates.length(); i++) {
			if (coordinates.substring(i, i + 1).equals(",")) {
				x = false;
				i++;
			}
			if (x) {
				spositionX = spositionX + (coordinates.substring(i, i + 1));
			} else {
				spositionY = spositionY + (coordinates.substring(i, i + 1));
			}
		}
		positionX = Integer.parseInt(spositionX);
		positionY = Integer.parseInt(spositionY);
		game.setStatus(positionX, positionY, !game.getStatus(positionX, positionY));

		JButton button = (JButton) e.getSource();
		setButtonColorBasedOnGame(button, positionX, positionY);
	}

	@Override
	public void update(Observable o, Object arg) {
		setButtonBackgroundColor();
	}

	private void setButtonBackgroundColor() {
		for (int y = 0; y < game.getSizeY(); y++) {
			for (int x = 0; x < game.getSizeX(); x++) {
				setButtonColorBasedOnGame(buttons[x][y], x, y);
			}
		}
	}

	protected void setLeftOffset(int leftOffset) {
		this.leftOffset = leftOffset;
	}

	protected int getLeftOffset() {
		return this.leftOffset;
	}

	protected Game getGame() {
		return game;
	}

	protected JButton[][] getButtons() {
		return buttons;
	}

	private void setButtonColorBasedOnGame(JButton button, int x, int y) {
		Color colorToSet = game.getStatus(x, y) ? Color.GREEN : Color.RED;
		button.setBackground(colorToSet);
		button.setForeground(colorToSet);
	}

} // end class ChildFrame
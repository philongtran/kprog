import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.EventObject;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

class GoLChildWindow extends JInternalFrame implements Observer {

	private static final long serialVersionUID = 1L;
	private JButton[][] buttons;
	private Game game;
	private int leftOffset, rightOffset;
	private MainWindow mydesk;// Referenz auf Hauptfenster
	private boolean rotated = false;
	private JPopupMenu popupMenu;
	private Color aliveColor;
	private Color deadColor;

	public GoLChildWindow(MainWindow dft, Game game, boolean rotated) {
		super("Game of Life " + game.golWindowNumber, true, true);
		// setBackground(col[nr % col.length]);// Start-Farbe
		mydesk = dft;// Hauptfenster merken

		this.rotated = rotated;
		// cp.setLayout(new FlowLayout());// FlowLayout
		setIconifiable(true);
		setMaximizable(true);
		setClosable(true);// weitere Parameter setzen
		this.game = game;
		createMenu();
		createPopupMenu();
		if (!rotated) {
			createFrame();
		} else {
			createFrameRotated();
		}
		createCells();
	}

	private void createPopupMenu() {
		popupMenu = new JPopupMenu("Color");
		JMenuItem colorMenuItem = new JMenuItem("Change Color");
		colorMenuItem.addActionListener(listener -> {
			createColorChooser(listener);
		});
		popupMenu.add(colorMenuItem);
	}

	private void createColorChooser(ActionEvent e) {
		JInternalFrame internalFrame = new JInternalFrame("ColorChooser");
		JColorChooser colorChooser = new JColorChooser(Color.blue);
		internalFrame.setClosable(true);
		colorChooser.getSelectionModel().addChangeListener(listener -> {
			onColorChoose(colorChooser);
		});
		JButton okButton = new JButton("OK");
		internalFrame.add(okButton);
		internalFrame.add(colorChooser);
		mydesk.addChild(internalFrame, GameSelectChildWindow.xpos + 20, GameSelectChildWindow.ypos + 20);
		internalFrame.setSize(400, 400);
	}

	private void onColorChoose(JColorChooser colorChooser) {
		aliveColor = colorChooser.getColor();
		Color invertedColor = new Color(255 - aliveColor.getRed(), 255 - aliveColor.getGreen(),
				255 - aliveColor.getBlue());
		deadColor = invertedColor;
		JButton[][] existingButtons = getButtons();
		for (int y = 0; y < game.getSizeY(); y++) {
			for (int x = 0; x < game.getSizeX(); x++) {
				JButton cell = existingButtons[x][y];
				if (game.getStatus(x, y)) {
					cell.setBackground(aliveColor);
				} else {
					cell.setBackground(invertedColor);
				}
			}
		}

	}

	private void createMenu() {
		JMenu[] menus = { new JMenu("Modus"), new JMenu("Geschwindigkeit"), new JMenu("Fenster"),
				new JMenu("Figuren") };
		JMenuItem[] menuItems = { MenuAction.START_STOP.asMenuItem(), MenuAction.DRAW.asMenuItem(),
				MenuAction.EXIT.asMenuItem(), MenuAction.FASTER.asMenuItem(), MenuAction.SLOWER.asMenuItem(),
				MenuAction.RESET.asMenuItem(), MenuAction.LEFTVIEW.asMenuItem(), MenuAction.RIGHTVIEW.asMenuItem(),
				MenuAction.ROTATELEFT.asMenuItem(), MenuAction.MAINVIEW.asMenuItem(),
				MenuAction.VIEWUPSIDEDOWN.asMenuItem(), MenuAction.BLINKER.asMenuItem(), MenuAction.GLIDER.asMenuItem(),
				MenuAction.GLIDERCANNON.asMenuItem() };

		for (int i = 0; i < menuItems.length; i++) {
			menus[(i < 3) ? 0 : (i < 6) ? 1 : (i < 11) ? 2 : 3].add(menuItems[i]);
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
			game.addObserver(golChildWindow);
			break;
		case RESET:
			game.resetDelay();
			break;
		case RIGHTVIEW:
			GoLViewRight viewRight = new GoLViewRight(mydesk, game, getRightOffset());
			mydesk.addChildGoL(viewRight, GameSelectChildWindow.xpos, GameSelectChildWindow.ypos, 800, 600);
			game.addObserver(viewRight);
			break;
		case ROTATELEFT:
			GoLChildWindow rotateLeft = new GoLChildWindow(mydesk, game, true);
			mydesk.addChildGoL(rotateLeft, GameSelectChildWindow.xpos, GameSelectChildWindow.ypos, 800, 600);
			game.addObserver(rotateLeft);
			break;
		case MAINVIEW:
			GoLChildWindow mainView = new GoLChildWindow(mydesk, game, false);
			mydesk.addChildGoL(mainView, GameSelectChildWindow.xpos, GameSelectChildWindow.ypos, 800, 600);
			game.addObserver(mainView);
			break;
		case SLOWER:
			game.slower();
			break;
		case START_STOP:
			game.startPause();
			break;
		case VIEWUPSIDEDOWN:
			break;
		case DRAW:
			game.setDraw();
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

	private void createFrameRotated() {
		Container cp = getContentPane();// Fenster-Container
		cp.setSize(game.getSizeXRotated() * 60, game.getSizeYRotated() * 60);
		cp.setLayout(new GridLayout(game.getSizeYRotated(), game.getSizeXRotated()));
	}

	protected void createCells() {
		if (!rotated) {
			createButtons();
			setButtonBackgroundColor();
		} else {
			createButtonsRotated();
			setButtonBackgroundColorRotated();
		}
	}

	private void createButtons() {
		buttons = new JButton[game.getSizeX()][game.getSizeY()];
		for (int y = 0; y < game.getSizeY(); y++) {
			for (int x = 0; x < game.getSizeX(); x++) {
				createButtonOnPosition(x, y);
			}
		}
	}

	private void createButtonOnPosition(int x, int y) {
		JButton button = new JButton(x + "," + y);
		buttons[x][y] = button;
		add(button);
		button.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseMoved(MouseEvent e) {
				if (game.getDraw()) {
					onCellButtonAction(e);
				}
			};
		});
		button.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				showPopupOnRightClick(e);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				showPopupOnRightClick(e);
			}

			private void showPopupOnRightClick(MouseEvent e) {
				if (e.isPopupTrigger()) {
					popupMenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		button.addActionListener(cellButtonClickListenerEvent -> {
			onCellButtonAction(cellButtonClickListenerEvent);
		});
	}

	private void createButtonsRotated() {
		buttons = new JButton[game.getSizeXRotated()][game.getSizeYRotated()];
		for (int y = 0; y < game.getSizeYRotated(); y++) {
			for (int x = 0; x < game.getSizeXRotated(); x++) {
				createButtonOnPosition(x, y);
			}
		}
	}

	protected void onCellButtonAction(EventObject e) {
		Object source = e.getSource();
		if (source instanceof JButton) {
			JButton jbutton = JButton.class.cast(source);
			onButtonAction(jbutton);
		}
	}

	private void onButtonAction(JButton source) {
		boolean x = true;
		String spositionX = "";
		String spositionY = "";

		int positionX;
		int positionY;

		String coordinates = source.getActionCommand();
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
		if (!rotated) {
			game.setStatus(positionX, positionY, !game.getStatus(positionX, positionY));
		} else {
			game.setStatusRotated(positionX, positionY, !game.getStatusRotated(positionX, positionY));
		}

		setButtonColorBasedOnGame(source, positionX, positionY);
	}

	@Override
	public void update(Observable o, Object arg) {
		game.boardRotateLeft();
		if (!rotated) {
			setButtonBackgroundColor();
		} else {
			setButtonBackgroundColorRotated();
		}
	}

	private void setButtonBackgroundColor() {
		for (int y = 0; y < game.getSizeY(); y++) {
			for (int x = 0; x < game.getSizeX(); x++) {
				setButtonColorBasedOnGame(buttons[x][y], x, y);
			}
		}
	}

	private void setButtonBackgroundColorRotated() {
		for (int y = 0; y < game.getSizeYRotated(); y++) {
			for (int x = 0; x < game.getSizeXRotated(); x++) {
				setButtonColorBasedOnGameRotated(buttons[x][y], x, y);
			}
		}
	}

	protected int getLeftOffset() {
		return this.leftOffset;
	}

	protected void setLeftOffset(int offset) {
		leftOffset = offset;
	}

	protected int getRightOffset() {
		return this.rightOffset;
	}

	protected void setRightOffset(int offset) {
		rightOffset = offset;
	}

	protected Game getGame() {
		return game;
	}

	protected JButton[][] getButtons() {
		return buttons;
	}

	private void setButtonColorBasedOnGame(JButton button, int x, int y) {
		Color colorToSet;
		if (aliveColor != null && deadColor != null) {
			colorToSet = game.getStatus(x, y) ? aliveColor : deadColor;
		} else {
			colorToSet = game.getStatus(x, y) ? Color.GREEN : Color.RED;
		}
		button.setBackground(colorToSet);
		button.setForeground(colorToSet);
	}

	private void setButtonColorBasedOnGameRotated(JButton button, int x, int y) {
		Color colorToSet = game.getStatusRotated(x, y) ? Color.GREEN : Color.RED;
		button.setBackground(colorToSet);
		button.setForeground(colorToSet);
	}
}
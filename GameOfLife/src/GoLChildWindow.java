import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
		ActionListener al = new ActionListener() {// AL als anonyme Klasse
			@Override
			public void actionPerformed(ActionEvent e) {// ... fuer alle
														// MenuItems
				JMenuItem item = (JMenuItem) e.getSource();
				if (item.getActionCommand().equals("Start/Stop")) {
					game.startPause();
				} else if (item.getActionCommand().equals("Exit")) {
					System.exit(0);
				} else if (item.getActionCommand().equals("Schneller")) {
					game.faster();
				} else if (item.getActionCommand().equals("Langsamer")) {
					game.slower();
				} else if (item.getActionCommand().equals("Reset")) {
					game.resetDelay();
				} else if (item.getActionCommand().equals("Blinker")) {
					game.addBlinker();
				} else if (item.getActionCommand().equals("Gleiter")) {
					game.addGlider();
				} else if (item.getActionCommand().equals("Sicht nach Links")) {
					GoLViewLeft golChildWindow = new GoLViewLeft(mydesk, game, getLeftOffset());
					mydesk.addChildGoL(golChildWindow, GameSelectChildWindow.xpos, GameSelectChildWindow.ypos, 800,
							600);
					game.getObserverLeft(golChildWindow);
					GameSelectChildWindow.xpos += 20;
					GameSelectChildWindow.ypos += 20;
				}
			}
		};
		/*
		 * JMenu menus = new JMenu("Modus"); JMenuItem items = new
		 * JMenuItem("Start/Stop"); JMenuItem items2 = new JMenuItem("Exit");
		 * items.addActionListener(al); items2.addActionListener(al); JMenuBar
		 * mb = new JMenuBar(); menus.add(items); menus.add(items2);
		 * mb.add(menus);
		 */
		JMenu[] menus = { new JMenu("Modus"), new JMenu("Geschwindigkeit"), new JMenu("Fenster"),
				new JMenu("Figuren") };
		JMenuItem[] menuItems = { new JMenuItem("Start/Stop"), new JMenuItem("Exit"), new JMenuItem("Schneller"),
				new JMenuItem("Langsamer"), new JMenuItem("Reset"), new JMenuItem("Sicht nach Links"),
				new JMenuItem("Sicht nach Rechts"), new JMenuItem("Sicht upside down"), new JMenuItem("Blinker"),
				new JMenuItem("Gleiter") };
		for (int i = 0; i < menuItems.length; i++) {
			menus[(i < 2) ? 0 : (i < 5) ? 1 : (i < 8) ? 2 : 3].add(menuItems[i]);
			menuItems[i].addActionListener(al);
		}
		JMenuBar mb = new JMenuBar();
		for (int i = 0; i < menus.length; i++) {
			mb.add(menus[i]);
		}
		setJMenuBar(mb);
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
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

class GoLChildWindow extends JInternalFrame implements Observer { // Klasse fuer
	// Kindfenster
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton[] buttons;
	private Game game;
	private int buttonsIndex = 0;

	MainWindow mydesk;// Referenz auf Hauptfenster

	public GoLChildWindow(MainWindow dft, Game game) { // Konstruktor
		super("Game of Life", true, true);// vergroesserbar, schliessbar
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
				} else if (item.getActionCommand().equals("Blinker")) {
					game.addBlinker();
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
				new JMenuItem("Langsamer"), new JMenuItem("View"), new JMenuItem("View 2"), new JMenuItem("View 3"),
				new JMenuItem("Blinker") };
		for (int i = 0; i < menuItems.length; i++) {
			menus[(i < 2) ? 0 : (i < 4) ? 1 : (i < 7) ? 2 : 3].add(menuItems[i]);
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

	private void createCells() {
		buttons = new JButton[game.getSizeX() * game.getSizeY()];
		for (int y = 0; y < game.getSizeY(); y++) {
			for (int x = 0; x < game.getSizeX(); x++) {
				JButton button = new JButton(x + "," + y);
				buttons[buttonsIndex] = button;
				add(button);
				button.addActionListener(al);
				buttonsIndex++;
			}
		}

		buttonsIndex = 0;
		// board.setStatus(5, 5, false);

		for (int y = 0; y < game.getSizeY(); y++) {
			for (int x = 0; x < game.getSizeX(); x++) {
				if (game.getStatus(x, y)) {
					buttons[buttonsIndex].setBackground(Color.GREEN);
					buttons[buttonsIndex].setForeground(Color.GREEN);
				} else {
					buttons[buttonsIndex].setBackground(Color.RED);
					buttons[buttonsIndex].setForeground(Color.RED);
				}
				buttonsIndex++;
			}
		}
		buttonsIndex = 0;
	}

	private ActionListener al = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
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
			if (game.getStatus(positionX, positionY)) {
				button.setBackground(Color.GREEN);
				button.setForeground(Color.GREEN);
			} else {
				button.setBackground(Color.RED);
				button.setForeground(Color.RED);
			}
		}
	};

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		for (int y = 0; y < game.getSizeY(); y++) {
			for (int x = 0; x < game.getSizeX(); x++) {
				if (game.getStatus(x, y)) {
					buttons[buttonsIndex].setBackground(Color.GREEN);
					buttons[buttonsIndex].setForeground(Color.GREEN);
				} else {
					buttons[buttonsIndex].setBackground(Color.RED);
					buttons[buttonsIndex].setForeground(Color.RED);
				}
				buttonsIndex++;
			}
		}
		buttonsIndex = 0;
	}

} // end class ChildFrame
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Display extends JFrame implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JFrame frame;
	private JButton[] buttons;
	private Board board;
	private int buttonsIndex = 0;

	public Display(Board board) {
		this.board = board;
		createUI();
		frame.setVisible(true);
	}

	private void createUI() {
		createFrame();
		createMenu();
		createCells();
	}

	private void createFrame() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(board.getSizeX() * 60, board.getSizeY() * 60);
		frame.setLayout(new GridLayout(board.getSizeY(), board.getSizeX()));
	}

	private void createMenu() {
		JMenu menus = new JMenu("Modus");
		JMenuItem items = new JMenuItem("Bearbeiten");
		JMenuBar mb = new JMenuBar();
		menus.add(items);
		mb.add(menus);
		frame.setJMenuBar(mb);
	}

	private void createCells() {
		buttons = new JButton[board.getSizeX() * board.getSizeY()];
		for (int y = 0; y < board.getSizeY(); y++) {
			for (int x = 0; x < board.getSizeX(); x++) {
				JButton button = new JButton(x + "," + y);
				buttons[buttonsIndex] = button;
				frame.add(button);
				button.addActionListener(al);
				buttonsIndex++;
			}
		}

		buttonsIndex = 0;
		// board.setStatus(5, 5, false);

		for (int y = 0; y < board.getSizeY(); y++) {
			for (int x = 0; x < board.getSizeX(); x++) {
				if (board.getStatus(x, y)) {
					buttons[buttonsIndex].setBackground(Color.GREEN);
				} else {
					buttons[buttonsIndex].setBackground(Color.RED);
				}
				buttonsIndex++;
			}
		}
		buttonsIndex = 0;
	}

	ActionListener al = new ActionListener() {

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
			board.setStatus(positionX, positionY, !board.getStatus(positionX, positionY));

			JButton button = (JButton) e.getSource();
			if (board.getStatus(positionX, positionY)) {
				button.setBackground(Color.GREEN);
			} else {
				button.setBackground(Color.RED);
			}
		}
	};

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		for (int y = 0; y < board.getSizeY(); y++) {
			for (int x = 0; x < board.getSizeX(); x++) {
				if (board.getStatus(x, y)) {
					buttons[buttonsIndex].setBackground(Color.GREEN);
				} else {
					buttons[buttonsIndex].setBackground(Color.RED);
				}
				buttonsIndex++;
			}
		}
		buttonsIndex = 0;

	}

}

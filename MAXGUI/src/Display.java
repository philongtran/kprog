import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This class prints all to the screen.
 *
 * @author Manuel Wessner <191711>
 * @author Phi Long Tran <191624>
 * @author Steve Nono <191709>
 */

public class Display extends JFrame implements ActionListener {

	/**
	 * Instance variables
	 */
	private static final long serialVersionUID = 1L;
	private Player[] player;
	private Board board;
	private Game game;

	// JFrame
	private JButton[] buttons;
	private JButton[] buttons2;
	private JButton buttons3;
	private String movement;
	private int i = 0;

	/**
	 * The constructor of the class.
	 * 
	 * @param player
	 *            - The array of players
	 * @param board
	 *            - The game board
	 */
	public Display(Player[] player, Board board) {
		this.player = player;
		this.board = board;
		getGame();

		// set up the board with JFrame
		buttons = new JButton[board.getSizeX() * board.getSizeY()];
		buttons2 = new JButton[4];
		setSize(board.getSizeX() * 50, board.getSizeY() * 100);
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(board.getSizeX(), board.getSizeY()));
		for (int i = 0; i < board.getSizeX() * board.getSizeY(); i++) {
			buttons[i] = new JButton("" + i);
			panel1.add(buttons[i]);
		}
		JPanel panel2 = new JPanel();
		panel2.setLayout(new BorderLayout());
		for (int i = 0; i < 4; i++) {
			buttons2[i] = new JButton("" + i);
			panel2.add(buttons2[i]);
			buttons2[i].addActionListener(this);
		}
		panel2.add(buttons2[0], BorderLayout.NORTH);
		panel2.add(buttons2[1], BorderLayout.WEST);
		panel2.add(buttons2[2], BorderLayout.SOUTH);
		panel2.add(buttons2[3], BorderLayout.EAST);
		buttons2[0].setText("W");
		buttons2[1].setText("A");
		buttons2[2].setText("S");
		buttons2[3].setText("D");

		buttons3 = new JButton("Score");

		panel2.add(buttons3, BorderLayout.CENTER);
		setLayout(new GridLayout(2, 1));
		add(panel1);
		add(panel2);
		setTitle("MAXGUI");
		setVisible(true);
	}

	/**
	 * Displays the score board and the play board to the screen.
	 * 
	 * @param playerID
	 *            - The ID of the player
	 * @param checkScore
	 *            - Boolean if score limit is reached
	 */
	public void draw(int playerID, boolean checkScore) {
		showScore(playerID, checkScore);
		showBoard(board);
	}

	/**
	 * Displays the score board to the screen.
	 * 
	 * @param playerID
	 *            - The ID of the player
	 * @param checkScore
	 *            - Boolean if score limit is reached
	 */
	private void showScore(int playerID, boolean checkScore) {
		String score = "";
		for (int i = 0; i < player.length; i++) {
			score += "Score " + returnLetter(player[i].getColor()) + ": " + player[i].getScore() + " ";
		}
		if (checkScore) {
			score += "| " + returnLetter(player[playerID].getColor()) + " wins";
		} else {
			if (playerID == player.length - 1) {
				score += "| " + returnLetter(player[0].getColor()) + " to move";
			} else {
				score += "| " + returnLetter(player[playerID + 1].getColor()) + " to move";
			}
		}
		buttons3.setText(score);
	}

	/**
	 * Displays the formated board on the screen. It shows its values and spots
	 * the players and then assigns them with a predefined letter or generic
	 * name.
	 * 
	 * @param board
	 *            - The game board
	 */
	private void showBoard(Board board) {
		int i = 0;
		for (int y = 0; y < board.getSizeY(); y++) {
			for (int x = 0; x < board.getSizeX(); x++, i++) {
				if (board.getValue(x, y) < 0) {
					buttons[i].setText(returnLetter(board.getValue(x, y)));
				} else {
					buttons[i].setText("" + board.getValue(x, y));
				}

			}
			// IO.writeln("");
		}
	}

	/**
	 * Assigning letters from the colors to the players.
	 * 
	 * @param color
	 *            - Color of the player
	 * @return - Letter of the player if available, otherwise generic name
	 */
	private String returnLetter(int color) {
		switch (color) {
		case -1:
			return "W";
		case -2:
			return "B";
		case -3:
			return "G";
		case -4:
			return "L";
		default:
			return "P" + (color * (-1));
		}
	}

	/**
	 * Event listener for running the game logic. It contains the movement and
	 * score- and board display.
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		movement = e.getActionCommand();
		// checks if score limit is reached
		if (game.checkScore()) {
			return;
		}
		// reads keyboard input to move the active player
		Action action = Action.of(movement.toLowerCase().substring(0, 1));
		// temporary variable to hold current player
		Player currentPlayer = player[i];
		// cases which are allowed
		switch (action) {
		case UP:
			if (game.canMoveInDirection(currentPlayer, action)) {
				game.move(currentPlayer, action);
			} else {
				i = game.playerRetry(i);
			}
			break;
		case DOWN:
			if (game.canMoveInDirection(currentPlayer, action)) {
				game.move(currentPlayer, action);
			} else {
				i = game.playerRetry(i);
			}
			break;
		case LEFT:
			if (game.canMoveInDirection(currentPlayer, action)) {
				game.move(currentPlayer, action);
			} else {
				i = game.playerRetry(i);
			}
			break;
		case RIGHT:
			if (game.canMoveInDirection(currentPlayer, action)) {
				game.move(currentPlayer, action);
			} else {
				i = game.playerRetry(i);
			}
			break;
		default:
			i = game.playerRetry(i);
			break;
		}
		// displays score and board
		draw(i, game.checkScore());
		i++;
		if (i >= game.PLAYER_COUNT) {
			i = 0;
		}

	}

	/**
	 * getter and setter for needed game methods
	 * 
	 * @param game
	 */
	public void setGame(Game game) {
		this.game = game;
	}

	private Game getGame() {
		return game;
	}

}

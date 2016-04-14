import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * This class prints all to the screen.
 *
 * @author Manuel Wessner <191711>
 * @author Phi Long Tran <191624>
 * @author Steve Nono <191709>
 */

public class Display extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Player[] player;
	private Board board;
	private JButton[] buttons;
	private JButton buttons3;

	/**
	 * The constructor of the class.
	 * 
	 * @param player
	 *            - The array of players
	 * @param board
	 *            - The game board
	 */
	public Display(Player[] player, Board board, JButton[] buttons, JButton buttons3) {
		this.player = player;
		this.board = board;
		this.buttons = buttons;
		this.buttons3 = buttons3;

		/*
		 * buttons = new JButton[board.getSizeX() * board.getSizeY()];
		 * setDefaultCloseOperation(EXIT_ON_CLOSE); setSize(board.getSizeX() *
		 * 100, board.getSizeY() * 100); setLayout(new
		 * GridLayout(board.getSizeX(), board.getSizeX())); for (int i = 0; i <
		 * board.getSizeX() * board.getSizeY(); i++) { buttons[i] = new
		 * JButton("" + i); getContentPane().add(buttons[i]); }
		 * setTitle("MAXGUI"); setVisible(true);
		 */

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
		// IO.writeln(score);
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
					// IO.write(returnLetter(board.getValue(x, y)) + " ");
					buttons[i].setText(returnLetter(board.getValue(x, y)));
				} else {
					// IO.write(board.getValue(x, y) + " ");
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

}

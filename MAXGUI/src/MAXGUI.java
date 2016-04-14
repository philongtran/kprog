import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * This class is the starting point of the game creation.
 * 
 * @author Phi Long Tran <191624>
 * @author Manuel Wessner <191711>
 * @author Steve Nono <191709>
 *
 */

public class MAXGUI extends JFrame implements ActionListener {

	/**
	 * Instance variables
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 */
	public MAXGUI() {
		JButton start = new JButton("New Game");
		start.addActionListener(this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(280, 80);
		setLayout(new FlowLayout());
		setTitle("Menu");
		add(start);
		setVisible(true);
	}

	/**
	 * Main method of the game.
	 * 
	 * @param args
	 *            - Arguments from the command line
	 */
	public static void main(String[] args) {
		new MAXGUI();
	}

	/**
	 * Create new game window
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		new Game(8, 8, 2);
	}

}

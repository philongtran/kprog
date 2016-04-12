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
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	 * @throws Exception
	 */
	public static void main(String[] args) {
		new MAXGUI();
		// new Game(8, 8, 2);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		JFrame game = new Game(8, 8, 2);
	}

}

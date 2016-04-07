import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 * This application is an extended version of the "Schloss" from last week. The
 * buttons are located at the border and starting to rotate clockwise. Each time
 * the user inputs a wrong number the direction of the rotation changes. Wrong >
 * buttons get colored red and then starts from the beginning. Right > buttons
 * get colored green and if the full combination is inputed correctly the window
 * closes.
 * 
 * 
 * @author Phi Long Tran <191624>
 * @author Steve Nono <191709>
 *
 */

public class DrehSchlossV2 extends JFrame implements ActionListener {

	// Instance variables

	private static final long serialVersionUID = 1L;

	static int universalTimer = 1000;
	int timer;
	int index = 0;
	boolean isProcessing = false;
	JButton[] buttons = new JButton[10];
	Panel panel;
	Panel panel2;
	boolean switching = false;
	int[] code2 = { 2, 3, 0, 3, 6, 0 }; // secret code
	int[] code = { 0, 1, 2, 3 };
	int i = 0;
	// Color col = Color.blue;

	/**
	 * 
	 * Constructor
	 * 
	 */
	public DrehSchlossV2(int timer) {
		// creating panels and buttons
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.timer = timer;
		panel = new Panel();
		panel2 = new Panel();
		for (int i = 0; i < 10; i++) { // register 10 buttons
			buttons[i] = new JButton("" + i); // and add them
			// buttons[i].addActionListener(this); // register to the action
			// listener
			buttons[i].addMouseMotionListener(mml);
			buttons[i].addMouseListener(ml);
		}

		setSize(480, 480);
		setLayout(new GridLayout(4, 3));
		setTitle("Drehschloss");
		setVisible(true);

		// layout of the buttons to a ring
		add(buttons[1]);
		add(buttons[0]);
		add(buttons[9]);
		add(buttons[2]);
		add(panel);
		add(buttons[8]);
		add(buttons[3]);
		add(panel2);
		add(buttons[7]);
		add(buttons[4]);
		add(buttons[5]);
		add(buttons[6]);

		// timer which sends an action every 1sec.
		int delay = timer; // milliseconds
		ActionListener taskPerformer = new ActionListener() {
			// make the ring rotate clockwise or counterclockwise
			@Override
			public void actionPerformed(ActionEvent evt) {
				if (switching) {
					counterClockwise();
				} else {
					clockwise();
				}
			}
		};
		new Timer(delay, taskPerformer).start();
	}

	/**
	 * 
	 * main method creating the frame and set the parameters
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new DrehSchlossV2(universalTimer);
	}

	/**
	 * 
	 * let the ring rotate clockwise by changing the label from (old value +1)
	 * 
	 */
	public void clockwise() {
		for (int i = 0; i < 10; i++) {
			int old = Integer.parseInt(buttons[i].getActionCommand());
			old += 1;
			if (old < 10) {
				buttons[i].setText("" + old);
			} else { // if label would be set to number "10" it will set it to
						// "0"
				buttons[i].setText("0");
			}
		}
	}

	/**
	 * 
	 * let the ring rotate counterclockwise by changing the label from (old
	 * value -1)
	 * 
	 */
	public void counterClockwise() {
		for (int i = 0; i < 10; i++) {
			int old = Integer.parseInt(buttons[i].getActionCommand());
			if (old > 0) {
				old -= 1;
				buttons[i].setText("" + old);
			} else { // if label would be set to number "-1" it will set it to
						// "9"
				buttons[i].setText("9");
			}
		}
	}

	/**
	 * 
	 * setting the background of the buttons to either red (wrong input) or
	 * green (correct input)
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		/*
		 * if (Integer.parseInt(e.getActionCommand()) == code[i]) { i++; for
		 * (int i = 0; i < 10; i++) { // for all buttons
		 * buttons[i].setBackground(Color.green); } //
		 * SwingUtilities.updateComponentTreeUI(jframe); if (i >= code.length) {
		 * // System.exit(0); // all numbers inputed correctly > close // window
		 * dispose(); } } else { switching = !switching; // wrong input > switch
		 * rotation i = 0; for (int i = 0; i < 10; i++) { // for all buttons
		 * buttons[i].setBackground(Color.red); } universalTimer = (int)
		 * (universalTimer * 0.66); new DrehSchloss(universalTimer); }
		 */

	}

	MouseListener ml = new MouseListener() {

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			isProcessing = false;
			System.out.println("release");
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub

		}
	};

	MouseMotionListener mml = new MouseMotionListener() {

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseDragged(MouseEvent e) {
			if (isProcessing) {
				return;
			} else {
				isProcessing = true;
				Object source = e.getSource();
				JButton jbutton = JButton.class.cast(source);
				System.out.println(jbutton.getText());
				if (Integer.parseInt(jbutton.getText()) == code[i]) {
					i++;
					for (int i = 0; i < 10; i++) { // for all buttons
						buttons[i].setBackground(Color.green);
					}
					// SwingUtilities.updateComponentTreeUI(jframe);
					if (i >= code.length) {
						// System.exit(0); // all numbers inputed correctly >
						// close
						// window
						dispose();
					}
				} else {
					switching = !switching; // wrong input > switch rotation
					i = 0;
					for (int i = 0; i < 10; i++) { // for all buttons
						buttons[i].setBackground(Color.red);
					}
					universalTimer = (int) (universalTimer * 0.66);
					new DrehSchlossV2(universalTimer);
				}
				// isProcessing = false;
			}
		}
	};

}

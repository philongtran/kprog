import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.Timer;

public class DrehSchloss extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static DrehSchloss jframe;
	int index = 0;
	JButton[] buttons = new JButton[10];
	Panel panel;
	Panel panel2;
	boolean switching = false;
	int[] code = { 2, 3, 0, 3, 6, 0 };
	int i = 0;
	Color col = Color.blue;

	public DrehSchloss() {
		// TODO Auto-generated constructor stub
		panel = new Panel();
		panel2 = new Panel();
		for (int i = 0; i < 10; i++) { // 10 Knöpfe registrieren
			buttons[i] = new JButton("" + i); // und einfügen
			buttons[i].addActionListener(this);
		}

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

		int delay = 1000; // milliseconds
		ActionListener taskPerformer = new ActionListener() {
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

	public static void main(String[] args) {
		jframe = new DrehSchloss();
		jframe.setSize(480, 480);
		jframe.setLayout(new GridLayout(4, 3));
		jframe.setTitle("Drehschloss");
		jframe.setVisible(true);
	}

	public void clockwise() {
		for (int i = 0; i < 10; i++) {
			int old = Integer.parseInt(buttons[i].getActionCommand());
			old += 1;
			if (old < 10) {
				buttons[i].setText("" + old);
			} else {
				buttons[i].setText("0");
			}
		}
	}

	public void counterClockwise() {
		for (int i = 0; i < 10; i++) {
			int old = Integer.parseInt(buttons[i].getActionCommand());
			if (old > 0) {
				old -= 1;
				buttons[i].setText("" + old);
			} else {
				buttons[i].setText("9");
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (Integer.parseInt(e.getActionCommand()) == code[i]) {
			i++;
			for (int i = 0; i < 10; i++) {// Alle enthaltenen Komponenten
				//
				buttons[i].setBackground(Color.green);
			}
			// SwingUtilities.updateComponentTreeUI(jframe);
			if (i >= code.length) {
				System.exit(0);
			}
		} else {
			switching = !switching;
			i = 0;
			for (int i = 0; i < 10; i++) {// Alle enthaltenen Komponenten
				//
				buttons[i].setBackground(Color.red);
			}
			// SwingUtilities.updateComponentTreeUI(jframe);
		}

	}

}

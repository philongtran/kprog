import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Schloss extends Frame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Button zero = new Button("0");
	Button one = new Button("1");
	Button two = new Button("2");
	Button three = new Button("3");
	Button four = new Button("4");
	Button five = new Button("5");
	Button six = new Button("6");
	Button seven = new Button("7");
	Button eight = new Button("8");
	Button nine = new Button("9");

	int[] code = { 2, 3, 0, 3, 6, 0 };
	int i = 0;

	public Schloss() {
		setLayout(new FlowLayout());
		add(zero);
		add(one);
		add(two);
		add(three);
		add(four);
		add(five);
		add(six);
		add(seven);
		add(eight);
		add(nine);

		zero.addActionListener(this);
		one.addActionListener(this);
		two.addActionListener(this);
		three.addActionListener(this);
		four.addActionListener(this);
		five.addActionListener(this);
		six.addActionListener(this);
		seven.addActionListener(this);
		eight.addActionListener(this);
		nine.addActionListener(this);
	}

	public static void main(String[] args) {

		Schloss frame = new Schloss();
		frame.setSize(640, 480);
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (Integer.parseInt(e.getActionCommand()) == code[i]) {
			i++;
			setBackground(Color.green);
			if (i >= code.length) {
				System.exit(0);
			}
		} else {
			i = 0;
			setBackground(Color.red);
		}

	}

}

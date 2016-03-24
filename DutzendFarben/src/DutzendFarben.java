import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DutzendFarben extends Frame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Button black = new Button("black");
	Button blue = new Button("blue");
	Button cyan = new Button("cyan");
	Button gray = new Button("gray");
	Button green = new Button("green");
	Button lightGray = new Button("lightGray");
	Button magenta = new Button("magenta");
	Button orange = new Button("orange");
	Button pink = new Button("pink");
	Button red = new Button("red");
	Button white = new Button("white");
	Button yellow = new Button("yellow");

	public DutzendFarben() {
		setLayout(new FlowLayout());
		add(black);
		add(blue);
		add(cyan);
		add(gray);
		add(green);
		add(lightGray);
		add(magenta);
		add(orange);
		add(pink);
		add(red);
		add(white);
		add(yellow);

		black.addActionListener(this);
		blue.addActionListener(this);
		cyan.addActionListener(this);
		gray.addActionListener(this);
		green.addActionListener(this);
		lightGray.addActionListener(this);
		magenta.addActionListener(this);
		orange.addActionListener(this);
		pink.addActionListener(this);
		red.addActionListener(this);
		white.addActionListener(this);
		yellow.addActionListener(this);
	}

	public static void main(String[] args) {
		DutzendFarben frame = new DutzendFarben();
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
	public void actionPerformed(ActionEvent event) {

		String s = event.getActionCommand();
		switch (s) {
		case "black":
			setBackground(Color.black);
			break;
		case "blue":
			setBackground(Color.blue);
			break;
		case "cyan":
			setBackground(Color.cyan);
			break;
		case "gray":
			setBackground(Color.gray);
			break;
		case "green":
			setBackground(Color.green);
			break;
		case "lightGray":
			setBackground(Color.lightGray);
			break;
		case "magenta":
			setBackground(Color.magenta);
			break;
		case "orange":
			setBackground(Color.orange);
			break;
		case "pink":
			setBackground(Color.pink);
			break;
		case "red":
			setBackground(Color.red);
			break;
		case "white":
			setBackground(Color.white);
			break;
		case "yellow":
			setBackground(Color.yellow);
			break;
		}

	}

}

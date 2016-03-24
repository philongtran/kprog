import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Farbclones extends Frame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Button change = new Button("change");
	Button newWindow = new Button("new");
	Color[] colors = { Color.black, Color.blue, Color.cyan, Color.gray, Color.green, Color.lightGray, Color.magenta,
			Color.orange, Color.pink, Color.red, Color.white, Color.yellow };
	int col = 0;

	public Farbclones(int color) {
		col = color;
		add(change);
		add(newWindow);
		setLayout(new FlowLayout());
		change.addActionListener(this);
		newWindow.addActionListener(this);
		setBackground(colors[col]);
	}

	public static void main(String[] args) {
		Farbclones frame = new Farbclones(0);
		frame.setSize(640, 480);
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	public void create() {
		Farbclones frame = new Farbclones(col);
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
		if (e.getActionCommand().equals("change")) {
			col = (col + 1) % colors.length;
			setBackground(colors[col]);
			repaint();
		} else {
			create();
		}
	}
}

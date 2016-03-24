import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Regenbogen extends Frame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Button rainbow = new Button("Rainbow");

	public Regenbogen() {
		setLayout(new FlowLayout());
		add(rainbow);
		rainbow.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		RegenbogenWindow frame = new RegenbogenWindow();
		frame.setSize(640, 480);
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	public static void main(String[] args) {
		Regenbogen frame = new Regenbogen();
		frame.setSize(640, 480);
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
}

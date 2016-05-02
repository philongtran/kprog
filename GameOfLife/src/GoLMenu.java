import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;

class GoLMenu extends JInternalFrame { // Klasse fuer Kindfenster
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Menu mydesk;// Referenz auf Hauptfenster

	public GoLMenu(Menu dft) { // Konstruktor
		super("Select Game", false, false);// vergroesserbar, schliessbar
		// setBackground(col[nr % col.length]);// Start-Farbe
		mydesk = dft;// Hauptfenster merken
		Container cp = getContentPane();// Fenster-Container
		cp.setLayout(new FlowLayout());// FlowLayout
		JButton jb = new JButton("Start GoL");// Knopf fuer Farbe
		cp.add(jb);// einfuegen
		jb.addActionListener(new ActionListener() {// AL fuer Farbknopf
			@Override
			public void actionPerformed(ActionEvent e) {
				new Game(5, 5);
			}
		});
		setIconifiable(true);
		setMaximizable(false);
		setClosable(false);// weitere Parameter setzen
	} // end Konstruktor
} // end class ChildFrame
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;

class GameSelect extends JInternalFrame { // Klasse fuer Kindfenster
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static int nr = -1, xpos = 30, ypos = 30;// statische Variablen
	static final Color[] col = { Color.red, Color.green };// verfuegbare Farben
															// ...
	Menu mydesk;// Referenz auf Hauptfenster

	public GameSelect(Menu dft) { // Konstruktor
		super("Select Game", false, false);// vergroesserbar, schliessbar
		// setBackground(col[nr % col.length]);// Start-Farbe
		mydesk = dft;// Hauptfenster merken
		Container cp = getContentPane();// Fenster-Container
		cp.setLayout(new FlowLayout());// FlowLayout
		JButton jb = new JButton("Game of Life");// Knopf fuer Farbe
		cp.add(jb);// einfuegen
		jb.addActionListener(new ActionListener() {// AL fuer Farbknopf
			@Override
			public void actionPerformed(ActionEvent e) {
				Game game = new Game(5, 5);
				mydesk.addChild(new GoLMenu(mydesk, game), xpos, ypos);
				xpos += 20;
				ypos += 20;
			}
		});
		setIconifiable(true);
		setMaximizable(false);
		setClosable(false);// weitere Parameter setzen
	} // end Konstruktor
} // end class ChildFrame
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class IdealWeight extends JFrame implements ActionListener {// sollte
																	// auch
																	// ActionListener
																	// sein
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JRadioButton genderM, genderF;// Knoepfe fuer Geschlecht
	ButtonGroup genderGroup;// ... dazu Knopfgruppe
	JPanel genderPanel;// ... dazu Panel
	JRadioButton heightA, heightB, heightC, heightD, heightE;// Kn. Groesse
	ButtonGroup heightGroup;// ... Gruppe
	JPanel heightPanel;// ... Panel
	JTextField resultText;// Textfeld fuer Ergebnis
	JLabel resultLabl;// ... dazu Label
	JPanel resultPanel;// ... dazu Panel
	Boolean male = true;
	int height = 62;

	public IdealWeight() { // Konstruktor
		setTitle("Your Ideal Weight");// Fenstertitel
		setDefaultCloseOperation(EXIT_ON_CLOSE);// zum Fensterschliessen
		// Geschlechts-Gruppe
		genderM = new JRadioButton("Male", true);// Knopf M. selekt.
		genderF = new JRadioButton("Female", false);// Knopf F. nicht s.
		genderGroup = new ButtonGroup();// Gruppe def.
		genderGroup.add(genderM);
		genderGroup.add(genderF);// Kn. hinzufuegen
		genderPanel = new JPanel();// G.-Panel
		genderPanel.setLayout(// Layout
				new BoxLayout(genderPanel, BoxLayout.Y_AXIS));// ... vertikal
		genderPanel.add(new JLabel("Your Gender"));// Label &
		genderPanel.add(genderM);
		genderPanel.add(genderF);// Knoepfe hinzuf.
		// Hoehen-Gruppe
		heightA = new JRadioButton("60 to 64 inches", true);// ... selektiert
		heightB = new JRadioButton("64 to 68 inches", false);// nicht selektiert
		heightC = new JRadioButton("68 to 72 inches", false);// ...
		heightD = new JRadioButton("72 to 76 inches", false);// ...
		heightE = new JRadioButton("76 to 80 inches", false);// ...
		heightGroup = new ButtonGroup();// Gruppe def.
		heightGroup.add(heightA);
		heightGroup.add(heightB);// Kn.
		heightGroup.add(heightC);
		heightGroup.add(heightD);// ... hinzufuegen
		heightGroup.add(heightE);//
		heightPanel = new JPanel();// H-Panel
		heightPanel.setLayout(// Layout
				new BoxLayout(heightPanel, BoxLayout.Y_AXIS));// ... vertikal
		heightPanel.add(new JLabel("Your Height"));// Label &
		heightPanel.add(heightA);
		heightPanel.add(heightB);// Kn. hinzufuegen
		heightPanel.add(heightC);
		heightPanel.add(heightD);// ...
		heightPanel.add(heightE);// ...
		// Ergebnis-Panel
		resultText = new JTextField(7);// Textfeld
		resultText.setEditable(false); // ... nur Ausgabe
		resultLabl = new JLabel("Ideal Weight");// Label def.
		resultPanel = new JPanel();// Panel def.
		resultPanel.add(resultLabl);// Label hinzufuegen
		resultPanel.add(resultText);// Textfeld ...
		// Gesamt-Fenster
		getContentPane().setLayout(new BorderLayout());// Layout: Border
		getContentPane().add(genderPanel, BorderLayout.WEST);// G-Panel hinzuf.
		getContentPane().add(heightPanel, BorderLayout.EAST);// H-Panel ...
		getContentPane().add(resultPanel, BorderLayout.SOUTH);// Ergebnis-Panel
																// ..
		genderM.addActionListener(this);
		genderF.addActionListener(this);
		heightA.addActionListener(this);
		heightB.addActionListener(this);
		heightC.addActionListener(this);
		heightD.addActionListener(this);
		heightE.addActionListener(this);
	}

	public static void main(String[] args) {// main ...
		IdealWeight weightApp = new IdealWeight();
		weightApp.setSize(250, 225);
		weightApp.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		double weight = 0;
		String ac = e.getActionCommand();
		switch (ac) {
		case "Male":
			male = true;
			break;
		case "Female":
			male = false;
			break;
		case "60 to 64 inches":
			height = 62;
			break;
		case "64 to 68 inches":
			height = 66;
			break;
		case "68 to 72 inches":
			height = 70;
			break;
		case "72 to 76 inches":
			height = 74;
			break;
		case "76 to 80 inches":
			height = 78;
			break;
		}

		if (male == true) {
			weight = height * height / 30;
		} else {
			weight = height * height / 28;
		}

		resultText.setText("" + weight);

	}
}
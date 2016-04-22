import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
// fuer Observer und Observable 
import java.util.Observable;
import java.util.Observer;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * 
 * @author Manuel Wessner <191711>
 * @author Phi Long Tran <191624>
 * @author Steve Nono <191709>
 *
 */

// Das ist ein View fuer textuelle Darstellung eines Quadratischen Polynoms
class TextQView extends JPanel implements Observer { // Beobachter
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextField a = new JTextField(10), // Textfelder fuer
			b = new JTextField(10), // drei Koeffizienten
			c = new JTextField(10), // ...
			d = new JTextField(10);
	JLabel al = new JLabel("Konstante", JLabel.RIGHT), // Labels ...
			bl = new JLabel("Linearer Koeffizient", JLabel.RIGHT),
			cl = new JLabel("Quadratischer Koeffizient", JLabel.RIGHT),
			dl = new JLabel("Kubischer Koeffizient", JLabel.RIGHT);
	Qpolynom myPolynom; // das Modell, ein Polynom

	TextQView(Qpolynom q) { // Konstuktor
		myPolynom = q; // merke Polynom
		setLayout(new GridLayout(4, 2, 5, 5)); // 3x2-Grid, 5-er Abstaende
		add(al);
		add(a); // Labels und Textfelder
		add(bl);
		add(b); // hinzufuegen
		add(cl);
		add(c);
		add(dl);
		add(d); // ...
		a.setEditable(false);
		b.setEditable(false); // Editierbarkeit
		c.setEditable(false); // der Textfelder
		d.setEditable(false);
	} // end Konstuktor
		// ..
		// ..

	@Override
	public void update(Observable o, Object arg) { // fuer Observer
		if (o == myPolynom)
			repaint(); // neu darstellen
	}

	@Override
	public void paintComponent(Graphics g) { // Component darstllen
		super.paintComponent(g); // super aufrufen
		a.setText("" + myPolynom.getConstant()); // Textfelder neu schreiben
		b.setText("" + myPolynom.getLinear()); // dabei get... Methoden
		c.setText("" + myPolynom.getQuadratic()); // aus Modell benutzen
		d.setText("" + myPolynom.getCubic());
	}
} // end TextQView

// Das ist ein View fuer graphische Darstellung eines Quadratischen Polynoms
class GraphQView extends JPanel implements Observer {
	Qpolynom myPolynom;
	static final int SCALEFACTOR = 200;// Konstante
	int cycles;// # Perioden
	int points;// # zu zeichnender Punkte
	double[] sines;// Funktions-Werte
	int[] pts;// Y-Positionen in Panel
	double[] polynom;
	double zwischenschritte;
	private Color color;

	int constant, linear, quadratic, cubic;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int instanceCounter;
	private static final Color[] someColors = { Color.GREEN, Color.BLUE };

	public GraphQView(Qpolynom q) {
		myPolynom = q; // merke Polynom
		color = someColors[instanceCounter];
		instanceCounter++;
		update(myPolynom, null);
	}

	@Override
	public void update(Observable o, Object arg) {
		// Uebungsaufgabe ....
		// TODO Auto-generated method stub

		double x = -200;
		points = 100;
		cycles = 1;
		points = SCALEFACTOR * cycles * 2;// # Punkte berechnen
		sines = new double[points];// Arrays dimensionieren
		polynom = new double[points];
		pts = new int[points];// ...

		constant = myPolynom.getConstant();
		linear = myPolynom.getLinear();
		quadratic = myPolynom.getQuadratic();
		cubic = myPolynom.getCubic();
		// color = myPolynom.getColor();

		for (int i = 0; i < points; i++) {// fuer jeden Punkt:
			double radians = (Math.PI / SCALEFACTOR) * i;// berechne Winkel
			sines[i] = Math.sin(radians);// ... und Funktionswert
			zwischenschritte = x / 20.0;
			x++;
			polynom[i] = constant + linear * zwischenschritte + Math.pow(zwischenschritte, 2) * quadratic
					+ Math.pow(zwischenschritte, 3) * cubic;
			System.out.println(zwischenschritte + ": " + polynom[i]);
		}

		if (o == myPolynom)
			repaint();
	} // Beobachter

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);// in Superklasse aufrufen ...
		int maxWidth = getWidth();// Weite bestimmen
		double hstep = (double) maxWidth / (double) points; // horizontale
															// Schrittweite
		int maxHeight = getHeight();// Hoehe Bestimmen
		for (int i = 0; i < points; i++)// fuer alle Punkte:
			pts[i] = (int) ((0.5 - polynom[i] * 0.05) * maxHeight); // skalieren
		g.setColor(color);//
		for (int i = 1; i < points; i++) {// fuer alle Punkte (bis auf ersten):
			int x1 = (int) ((i - 1) * hstep);// bestimme x,y-Koordinaten
			int x2 = (int) (i * hstep);// des aktuellen Punkts und des
			int y1 = pts[i - 1];// linken Nachbarn
			int y2 = pts[i];//
			g.drawLine(x1, y1, x2, y2);// Zeichne Linie
		}
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
} // end GraphQView

class GraphQView2 extends JPanel implements Observer {
	Qpolynom myPolynom;
	static final int SCALEFACTOR = 200;// Konstante
	int cycles;// # Perioden
	int points;// # zu zeichnender Punkte
	double[] sines;// Funktions-Werte
	int[] pts;// Y-Positionen in Panel
	double[] polynom;
	double zwischenschritte;
	private Color color;

	int constant, linear, quadratic, cubic;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int instanceCounter;
	private static final Color[] someColors = { Color.GREEN, Color.BLUE };

	public GraphQView2(Qpolynom q) {
		myPolynom = q; // merke Polynom
		color = someColors[instanceCounter];
		instanceCounter++;
		update(myPolynom, null);
	}

	@Override
	public void update(Observable o, Object arg) {
		// Uebungsaufgabe ....
		// TODO Auto-generated method stub

		double x = -200;
		points = 100;
		cycles = 1;
		points = SCALEFACTOR * cycles * 2;// # Punkte berechnen
		sines = new double[points];// Arrays dimensionieren
		polynom = new double[points];
		pts = new int[points];// ...

		constant = myPolynom.getConstant();
		linear = myPolynom.getLinear();
		quadratic = myPolynom.getQuadratic();
		cubic = myPolynom.getCubic();
		// color = myPolynom.getColor();

		for (int i = 0; i < points; i++) {// fuer jeden Punkt:
			double radians = (Math.PI / SCALEFACTOR) * i;// berechne Winkel
			sines[i] = Math.sin(radians);// ... und Funktionswert
			zwischenschritte = x / 20.0;
			x++;
			polynom[i] = constant + linear * zwischenschritte + Math.pow(zwischenschritte, 2) * quadratic
					+ Math.pow(zwischenschritte, 3) * cubic;
			System.out.println(zwischenschritte + ": " + polynom[i]);
		}

		if (o == myPolynom)
			repaint();
	} // Beobachter

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);// in Superklasse aufrufen ...
		int maxWidth = getWidth();// Weite bestimmen
		double hstep = (double) maxWidth / (double) points; // horizontale
															// Schrittweite
		int maxHeight = getHeight();// Hoehe Bestimmen
		for (int i = 0; i < points; i++)// fuer alle Punkte:
			pts[i] = (int) ((0.5 - ((-1) * polynom[i]) * 0.01) * maxHeight); // skalieren
		g.setColor(Color.RED);//
		for (int i = 1; i < points; i++) {// fuer alle Punkte (bis auf ersten):
			int x1 = (int) ((i - 1) * hstep);// bestimme x,y-Koordinaten
			int x2 = (int) (i * hstep);// des aktuellen Punkts und des
			int y1 = pts[i - 1];// linken Nachbarn
			int y2 = pts[i];//
			g.drawLine(x1, y1, x2, y2);// Zeichne Linie
		}
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = Color.RED;
	}
} // end GraphQView
	// Das ist das Modell

class Qpolynom extends Observable { // Beobachtbares
	private int // interne Daten
	constant, linear, quadratic, cubic; // Polynom-Koeffizienten
	private Color color;

	public Qpolynom(int a, int b, int c, int d) { // Konstuktor
		constant = a;
		linear = b;
		quadratic = c;
		cubic = d;
		color = Color.RED;
	} // end Konstuktor

	public void setColor(Color color) {
		this.color = color;
		setChanged();
		notifyObservers();
	}

	public Color getColor() {
		return color;
	}

	public int getConstant() { // getter Methode
		return constant; // konstanter Koeffizient
	}

	// ...
	// ...
	public int getLinear() { // getter Methode
		return linear; // linearer Koeffizient
	}

	public int getQuadratic() { // getter Methode
		return quadratic; // quadratischer Koeffizient
	}

	public int getCubic() {
		return cubic;
	}

	public void setConstant(int n) { // setter Methode
		constant = n; // konstanter Koeffizient
		setChanged();
		notifyObservers();
	}

	public void setLinear(int n) { // setter Methode
		linear = n; // linearer Koeffizient
		setChanged();
		notifyObservers();
	}

	public void setQuadratic(int n) { // setter Methode
		quadratic = n; // quadratischer Koeffizient
		setChanged();
		notifyObservers();
	}

	public void setCubic(int n) {
		cubic = n;
		setChanged();
		notifyObservers();
	}
} // end Qpolynom

// ..
// ..
public class MVCexample extends JApplet { // Das GUI-Programm
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JSlider sa, sb, sc, sd;// Controller

	@Override
	public void init() {
		Container cp = getContentPane(); // Fenster-Container
		cp.setLayout(new GridLayout(7, 1, 10, 10)); // 5x1-Grid, 10-er
													// Abstaende

		final Qpolynom p = new Qpolynom(1, 2, 3, 4); // das Modell

		sa = new JSlider(SwingConstants.HORIZONTAL, -10, 10, 1); // Erzeugung
		sb = new JSlider(SwingConstants.HORIZONTAL, -10, 10, 2); // der
																	// Controller
		sc = new JSlider(SwingConstants.HORIZONTAL, -10, 10, 3); //
		sd = new JSlider(SwingConstants.HORIZONTAL, -10, 10, 4);

		sa.setMajorTickSpacing(10); // Parameter
		sa.setMinorTickSpacing(1);
		sa.setSnapToTicks(true);
		sb.setMajorTickSpacing(10);
		sb.setMinorTickSpacing(1);
		sb.setSnapToTicks(true);
		sc.setMajorTickSpacing(10);
		sc.setMinorTickSpacing(1);
		sc.setSnapToTicks(true);
		sd.setMajorTickSpacing(10); // Parameter
		sd.setMinorTickSpacing(1);
		sd.setSnapToTicks(true);
		sa.setPaintTicks(true);
		sb.setPaintTicks(true);
		sc.setPaintTicks(true);
		sd.setPaintTicks(true);

		// ...
		// ...
		sa.setPaintLabels(true); // Parameter
		sb.setPaintLabels(true);
		sc.setPaintLabels(true);
		sd.setPaintLabels(true);

		sa.setPreferredSize(new Dimension(400, 70));
		sb.setPreferredSize(new Dimension(400, 70));
		sc.setPreferredSize(new Dimension(400, 70));
		sd.setPreferredSize(new Dimension(400, 70));

		sa.setBorder(new TitledBorder("Konstante")); // Border fuer
		sb.setBorder(new TitledBorder("Linearer Koeffizient")); // Schiebe-
		sc.setBorder(new TitledBorder("Quadratischer Koeffizient")); // Regler
		sd.setBorder(new TitledBorder("Kubischer Koeffizient"));

		sa.addChangeListener(new ChangeListener() { // Listener, i.Kl.
			@Override
			public void stateChanged(ChangeEvent evt) {
				JSlider source = (JSlider) evt.getSource();
				if (!source.getValueIsAdjusting()) {
					p.setConstant(source.getValue()); // set... benutzen
				}
			}
		});
		// ...
		// ...
		sb.addChangeListener(new ChangeListener() { // Listener, i.Kl.
			@Override
			public void stateChanged(ChangeEvent evt) {
				JSlider source = (JSlider) evt.getSource();
				if (!source.getValueIsAdjusting()) {
					p.setLinear(source.getValue()); // set... benutzen
				}
			}
		});
		sc.addChangeListener(new ChangeListener() { // Listener, i.Kl.
			@Override
			public void stateChanged(ChangeEvent evt) {
				JSlider source = (JSlider) evt.getSource();
				if (!source.getValueIsAdjusting()) {
					p.setQuadratic(source.getValue()); // set... benutzen
				}
			}
		});

		sd.addChangeListener(new ChangeListener() { // Listener, i.Kl.
			@Override
			public void stateChanged(ChangeEvent evt) {
				JSlider source = (JSlider) evt.getSource();
				if (!source.getValueIsAdjusting()) {
					p.setCubic(source.getValue()); // set... benutzen
				}
			}
		});

		TextQView view1 = new TextQView(p); // 1. View
		// GraphQView view2 = new GraphQView (p); // nach Uebung
		// TextQView view2 = new TextQView(p); // 2. View
		GraphQView view3 = new GraphQView(p);
		GraphQView2 view4 = new GraphQView2(p);

		p.addObserver(view1); // Views als Observer registrieren
		// p.addObserver(view2); // ..
		p.addObserver(view3);
		p.addObserver(view4);

		cp.add(view1); // Views zum Fenster hinzufuegen
		// cp.add(view2); // ..
		cp.add(view3);
		cp.add(view4);
		// ...
		// ...
		cp.add(sa); // Controller hinzufuegen
		cp.add(sb); // ...
		cp.add(sc); // ...
		cp.add(sd);

	} // end init

	public static void main(String[] args) {
		Konsole.run(new MVCexample(), 400, 800); // Konsolenstart
	}
} // end MVCexample

// ********************************************************************************

class Konsole {
	public static String title(Object o) {
		String t = o.getClass().toString();
		if (t.indexOf("class") != -1)
			t = t.substring(6);
		System.out.println("Konsole: running " + t);
		return t;
	}

	public static void setupClosing(JFrame frame) {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void run(JFrame frame, int width, int height) {
		setupClosing(frame);
		frame.setSize(width, height);
		frame.setVisible(true);
	}

	public static void run(JApplet applet, int width, int height) {
		JFrame frame = new JFrame(title(applet));
		setupClosing(frame);
		frame.getContentPane().add(applet);
		frame.setSize(width, height);
		applet.init();
		applet.start();
		frame.setVisible(true);
	}

	public static void run(JPanel panel, int width, int height) {
		JFrame frame = new JFrame(title(panel));
		setupClosing(frame);
		frame.getContentPane().add(panel);
		frame.setSize(width, height);
		frame.setVisible(true);
	}

}

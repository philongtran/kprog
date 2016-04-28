import javax.swing.DefaultDesktopManager;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

public class Menu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JDesktopPane desk;

	public Menu() {
		desk = new JDesktopPane();
		desk.setDesktopManager(new DefaultDesktopManager());
		setContentPane(desk);
	}

	public void addChild(JInternalFrame child, int x, int y) {// Hinzufuegen
		child.setLocation(x, y);// Ort und
		child.setSize(200, 150);// Groessesetzen
		child.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);// Schiessoperation
		desk.add(child);// Kindfenstereinfuegen
		child.setVisible(true);// und sichtbar machen
	} // end addChild

}

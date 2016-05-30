import javax.swing.DefaultDesktopManager;
import javax.swing.JApplet;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

public class MainWindow extends JApplet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JDesktopPane desk;

	public MainWindow() {
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

	public void addChildGoL(JInternalFrame child, int x, int y, int sizeX, int sizeY) {// Hinzufuegen
		child.setLocation(x, y);// Ort und
		child.setSize(sizeX, sizeY);// Groessesetzen
		child.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);// Schiessoperation
		desk.add(child);// Kindfenstereinfuegen
		child.setVisible(true);// und sichtbar machen
		GameSelectChildWindow.xpos += 20;
		GameSelectChildWindow.ypos += 20;
	} // end addChild

}
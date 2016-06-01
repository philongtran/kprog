
/* This class creates the main window
 * 
 * @author Phi Long Tran <191624>
 * @author Manuel Wessner <191711>
 * @author Steve Nono <191709>
 */

import javax.swing.DefaultDesktopManager;
import javax.swing.JApplet;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

public class MainWindow extends JApplet {
	private static final long serialVersionUID = 1L;
	private JDesktopPane desk;

	public MainWindow() {
		desk = new JDesktopPane();
		desk.setDesktopManager(new DefaultDesktopManager());
		setContentPane(desk);
	}

	// add game select child window
	public void addChild(JInternalFrame child, int x, int y) {
		child.setLocation(x, y);
		child.setSize(200, 150);
		child.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		desk.add(child);
		child.setVisible(true);
	}

	// add game of life child window
	public void addChildGoL(JInternalFrame child, int x, int y, int sizeX, int sizeY) {
		child.setLocation(x, y);
		child.setSize(sizeX, sizeY);
		child.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		desk.add(child);
		child.setVisible(true);
		GameSelectChildWindow.xpos += 20;
		GameSelectChildWindow.ypos += 20;
	}

}
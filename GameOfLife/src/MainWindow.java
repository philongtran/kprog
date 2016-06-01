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

	public void addChild(JInternalFrame child, int x, int y) {
		child.setLocation(x, y);
		child.setSize(200, 150);
		child.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		desk.add(child);
		child.setVisible(true);
	}

	public void addChildGoL(JInternalFrame child, int x, int y, int sizeX, int sizeY) {
		addChild(child, x, y);
		child.setSize(sizeX, sizeY);
		GameSelectChildWindow.xpos += 20;
		GameSelectChildWindow.ypos += 20;
	}

}
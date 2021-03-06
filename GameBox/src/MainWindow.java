
import java.awt.Dimension;

import javax.swing.DefaultDesktopManager;
import javax.swing.JApplet;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

/**
 * This class creates the main window
 * 
 * @author Phi Long Tran <191624>
 * @author Manuel Wessner <191711>
 * @author Steve Nono <191709>
 */

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
    desk.add(child);
    child.setVisible(true);
    child.toFront();
  }

  public void addChild(JInternalFrame child, int x, int y, Dimension size) {
    addChild(child, x, y);
    child.setSize(size);
  }

  public void addChildGoL(JInternalFrame child, int x, int y, int sizeX, int sizeY) {
    addChild(child, x, y, new Dimension(sizeX, sizeY));
    GameSelectChildWindow.xpos += 20;
    GameSelectChildWindow.ypos += 20;
  }
}

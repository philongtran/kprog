
import java.awt.Container;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JInternalFrame;

/**
 * This class represents the game select child window
 * 
 * @author Phi Long Tran <191624>
 * @author Manuel Wessner <191711>
 * @author Steve Nono <191709>
 */
class GameSelectChildWindow extends JInternalFrame {

  // instance variables
  private static final long serialVersionUID = 1L;
  static int nr = -1, xpos = 30, ypos = 30;
  private MainWindow mydesk;

  public GameSelectChildWindow(MainWindow dft) {
    super("Select Game", false, false);
    mydesk = dft;
    Container cp = getContentPane();
    cp.setLayout(new BoxLayout(cp, BoxLayout.PAGE_AXIS));
    JButton quodButton = new JButton("Quod");
    JButton gameOfLifeButton = new JButton("Game of Life");
    cp.add(quodButton);
    cp.add(gameOfLifeButton);
    quodButton.addActionListener(listener -> {
      createQuodGameInNewWindow();
    });
    quodButton.addActionListener(listener -> {
      // open game of life
    });
    setSize(200, 150);
    setIconifiable(true);
    setMaximizable(false);
    setClosable(false);
  }

  private void createQuodGameInNewWindow() {
    mydesk.addChild(new QuodFrame(), 10, 10);
  }
}

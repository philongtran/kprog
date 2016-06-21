
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
    JButton lockGameButton = new JButton("Rotating Lock V2");
    cp.add(quodButton);
    cp.add(gameOfLifeButton);
    cp.add(lockGameButton);
    quodButton.addActionListener(listener -> {
      createQuodGameInNewWindow();
    });
    gameOfLifeButton.addActionListener(listener -> {
      createGameOfLifeGameInNewWindow();
    });
    lockGameButton.addActionListener(listener -> {
      createRotatingLockGameInNewWindow();
    });

    setSize(200, 150);
    setIconifiable(true);
    setMaximizable(false);
    setClosable(false);
  }

  private void createRotatingLockGameInNewWindow() {
    mydesk.addChild(new DrehSchlossV2(), 10, 10);
  }

  private void createQuodGameInNewWindow() {
    mydesk.addChild(new QuodFrame(new QuodGame()), 10, 10);
  }

  /**
   * if create new game of life game button is pressed create a new game of life child window
   */
  private void createGameOfLifeGameInNewWindow() {
    GoLGame game = new GoLGame(50, 50);
    GoLChildWindow golChildWindow = new GoLChildWindow(mydesk, game);
    mydesk.addChildGoL(golChildWindow, xpos, ypos, 800, 600);
    game.addObserver(golChildWindow);
    GoLGame.GOLWINDOWNUMBER++;
  }
}

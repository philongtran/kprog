
import java.awt.Container;
import java.awt.GridLayout;

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
    cp.setLayout(new GridLayout(5, 1));
    JButton quodButton = new JButton("Quod");
    JButton gameOfLifeButton = new JButton("Game of Life");
    JButton maxButton = new JButton("MAX");
    JButton lockGameV1Button = new JButton("Rotating Lock V1");
    JButton lockGameButton = new JButton("Rotating Lock V2");

    addButtons(quodButton, gameOfLifeButton, maxButton, lockGameV1Button, lockGameButton);

    quodButton.addActionListener(listener -> {
      createQuodGameInNewWindow();
    });
    gameOfLifeButton.addActionListener(listener -> {
      createGameOfLifeGameInNewWindow();
    });
    lockGameV1Button.addActionListener(listener -> {
      createRotatingLockGameV1InNewWindow();
    });
    lockGameButton.addActionListener(listener -> {
      createRotatingLockGameInNewWindow();
    });
    maxButton.addActionListener(listener -> {
      createMAXGameInNewWindow();
    });

    setSize(200, 150);
    setIconifiable(true);
    setMaximizable(false);
    setClosable(false);
  }

  private void createMAXGameInNewWindow() {
    mydesk.addChild(new MAXGame(8, 8, 2).getDisplay(), xpos, ypos);
  }

  private void createRotatingLockGameV1InNewWindow() {
    mydesk.addChild(new DrehSchloss(), xpos, ypos);
  }

  private void createRotatingLockGameInNewWindow() {
    mydesk.addChild(new DrehSchlossV2(), xpos, ypos);
  }

  private void createQuodGameInNewWindow() {
    mydesk.addChild(new QuodFrame(new QuodGame()), xpos, ypos);
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

  private void addButtons(JButton... buttons) {
    for (JButton button : buttons) {
      getContentPane().add(button);
    }
  }
}

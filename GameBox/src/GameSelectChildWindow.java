
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
    super("Select Game", true, false);
    mydesk = dft;
    JButton quodButton = new JButton("Quod");
    JButton quodAIButton = new JButton("Quod with AI");
    JButton gameOfLifeButton = new JButton("Game of Life");
    JButton maxButton = new JButton("MAX");
    JButton lockGameV1Button = new JButton("Rotating Lock V1");
    JButton lockGameButton = new JButton("Rotating Lock V2");
    JButton rainbowGameButton = new JButton("Rainbow");
    JButton mvcEampleButton = new JButton("MVCExample");

    addButtons(quodButton, quodAIButton, gameOfLifeButton, maxButton, lockGameV1Button,
        lockGameButton, rainbowGameButton, mvcEampleButton);

    quodButton.addActionListener(listener -> {
      createQuodGameInNewWindow();
    });
    quodAIButton.addActionListener(listener -> {
      createQuodAIGameInNewWindow();
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
    rainbowGameButton.addActionListener(listener -> {
      createRainbowInNewWindow();
    });
    mvcEampleButton.addActionListener(listener -> {
      createMVCExampleInNewWindow();
    });

    setSize(200, 150);
    setIconifiable(true);
    setMaximizable(false);
    setClosable(false);
  }

  private void createMVCExampleInNewWindow() {
    mydesk.addChild(new MVCexample(), xpos, ypos);
  }

  private void createRainbowInNewWindow() {
    mydesk.addChild(new RegenbogenWindow(), xpos, ypos);
  }

  private void createMAXGameInNewWindow() {
    mydesk.addChild(new MAXGame(8, 8, 2).getDisplay(), xpos, ypos);
  }

  private void createRotatingLockGameV1InNewWindow() {
    mydesk.addChild(new DrehSchloss(), xpos, ypos);
  }

  private void createRotatingLockGameInNewWindow() {
    mydesk.addChild(new DrehSchlossV2(mydesk), xpos, ypos);
  }

  private void createQuodGameInNewWindow() {
    mydesk.addChild(new QuodFrame(new QuodGame()), xpos, ypos);
  }

  private void createQuodAIGameInNewWindow() {
    mydesk.addChild(new QuodFrame(new QuodGame(true)), xpos, ypos);
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
    getContentPane().setLayout(new GridLayout(buttons.length, 1));
    for (JButton button : buttons) {
      getContentPane().add(button);
    }
  }
}

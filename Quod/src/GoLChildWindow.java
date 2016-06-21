
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.EventObject;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.colorchooser.AbstractColorChooserPanel;

/**
 * this class represents the game of life child window.
 * 
 * @author Phi Long Tran <191624>
 * @author Manuel Wessner <191711>
 * @author Steve Nono <191709>
 */
class GoLChildWindow extends JInternalFrame implements Observer {

  // instance variables
  private static final long serialVersionUID = 1L;
  private JButton[][] buttons;
  private GoLGame game;
  private int leftOffset, rightOffset;
  private MainWindow mydesk; // reference to the main window
  private JPopupMenu popupMenu;
  private Color aliveColor;
  private Color deadColor;
  private int rotateLeftCount;
  private int rotateRightCount;

  // constructor
  public GoLChildWindow(MainWindow dft, GoLGame game) {
    super("Game of Life " + GoLGame.GOLWINDOWNUMBER, true, true);
    mydesk = dft;
    setIconifiable(true);
    setMaximizable(true);
    setClosable(true);
    this.game = game;
    createMenu();
    createPopupMenu();
    createFrame();
    createCells();
  }

  /**
   * create a pop up when right clicking a cell to change color
   */
  private void createPopupMenu() {
    popupMenu = new JPopupMenu("Color");
    JMenuItem colorMenuItem = new JMenuItem("Change Color");
    colorMenuItem.addActionListener(listener -> {
      createColorChooser(listener);
    });
    popupMenu.add(colorMenuItem);
  }

  private void createColorChooser(ActionEvent e) {
    JInternalFrame internalFrame = new JInternalFrame("Color Chooser");
    JColorChooser colorChooser = new JColorChooser(getDeadColor());
    for (AbstractColorChooserPanel panel : colorChooser.getChooserPanels()) {
      if (!panel.getDisplayName().equals("Swatches")) {
        colorChooser.removeChooserPanel(panel);
      }
    }
    internalFrame.setClosable(true);
    colorChooser.getSelectionModel().addChangeListener(listener -> {
      onColorChoose(colorChooser);
    });

    JButton okButton = new JButton("OK");
    internalFrame.add(okButton);
    internalFrame.add(colorChooser);
    mydesk.addChild(internalFrame, GameSelectChildWindow.xpos + 20,
        GameSelectChildWindow.ypos + 20);
    internalFrame.setSize(400, 400);

  }

  private void onColorChoose(JColorChooser colorChooser) {
    aliveColor = colorChooser.getColor();
    Color invertedColor = new Color(255 - aliveColor.getRed(), 255 - aliveColor.getGreen(),
        255 - aliveColor.getBlue());
    deadColor = invertedColor;
    JButton[][] existingButtons = getButtons();
    for (int y = 0; y < game.getSizeY(); y++) {
      for (int x = 0; x < game.getSizeX(); x++) {
        JButton cell = existingButtons[x][y];
        if (game.getStatus(x, y)) {
          cell.setBackground(aliveColor);
        } else {
          cell.setBackground(invertedColor);
        }
      }
    }

  }

  /**
   * creates the menu bar in the game of life child window
   */
  private void createMenu() {
    JMenu[] menus =
        {new JMenu("Mode"), new JMenu("Speed"), new JMenu("Window"), new JMenu("Figures")};
    JMenuItem[] menuItems = {GoLMenuAction.START_STOP.asMenuItem(), GoLMenuAction.DRAW.asMenuItem(),
        GoLMenuAction.SAVE.asMenuItem(), GoLMenuAction.LOAD.asMenuItem(), GoLMenuAction.EXIT.asMenuItem(),
        GoLMenuAction.FASTER.asMenuItem(), GoLMenuAction.SLOWER.asMenuItem(),
        GoLMenuAction.RESET.asMenuItem(), GoLMenuAction.ROTATELEFT.asMenuItem(),
        GoLMenuAction.ROTATERIGHT.asMenuItem(), GoLMenuAction.MAINVIEW.asMenuItem(),
        GoLMenuAction.BLINKER.asMenuItem(), GoLMenuAction.GLIDER.asMenuItem(),
        GoLMenuAction.GLIDERCANNON.asMenuItem()};

    for (int i = 0; i < menuItems.length; i++) {
      menus[(i < 5) ? 0 : (i < 8) ? 1 : (i < 11) ? 2 : 3].add(menuItems[i]);
      menuItems[i].addActionListener(menuItemClickEvent -> {
        onMenuItemClick(menuItemClickEvent);
      });
    }

    JMenuBar mb = new JMenuBar();
    for (int i = 0; i < menus.length; i++) {
      mb.add(menus[i]);
    }
    setJMenuBar(mb);
  }

  /**
   * action listener for the menu items
   */
  private void onMenuItemClick(ActionEvent e) {
    JMenuItem item = (JMenuItem) e.getSource();
    switch (GoLMenuAction.of(item.getActionCommand())) {
      case BLINKER:
        game.addBlinker();
        break;
      case EXIT:
        System.exit(0);
        break;
      case FASTER:
        game.faster();
        break;
      case GLIDER:
        game.addGlider();
        break;
      case GLIDERCANNON:
        game.addGliderCannon();
        break;
      case RESET:
        game.resetDelay();
        break;
      case ROTATELEFT:
        GoLChildWindow rotatedLeft = new GoLViewLeftRotated(mydesk, game, rotateLeftCount);
        mydesk.addChildGoL(rotatedLeft, GameSelectChildWindow.xpos, GameSelectChildWindow.ypos, 800,
            600);
        game.addObserver(rotatedLeft);
        break;
      case ROTATERIGHT:
        GoLChildWindow rotatedRight = new GoLViewRightRotated(mydesk, game, rotateRightCount);
        mydesk.addChildGoL(rotatedRight, GameSelectChildWindow.xpos, GameSelectChildWindow.ypos,
            800, 600);
        game.addObserver(rotatedRight);
        break;
      case MAINVIEW:
        GoLChildWindow mainView = new GoLChildWindow(mydesk, game);
        mydesk.addChildGoL(mainView, GameSelectChildWindow.xpos, GameSelectChildWindow.ypos, 800,
            600);
        game.addObserver(mainView);
        break;
      case SLOWER:
        game.slower();
        break;
      case START_STOP:
        game.startPause();
        break;
      case DRAW:
        game.toggleDrawingMode();
        break;
      case SAVE:
        GoLGameState.save(getGame().getBoard(), this);
        break;
      case LOAD:
        Optional.ofNullable(GoLGameState.load(this)).ifPresent(board -> game.setBoard(board));;
        break;
      case NONE:
        break;
    }
  }


  // create the contents of the child window
  private void createFrame() {
    Container cp = getContentPane();// Fenster-Container
    cp.setSize(game.getSizeX() * 60, game.getSizeY() * 60);
    cp.setLayout(new GridLayout(game.getSizeY(), game.getSizeX()));
  }

  protected void createCells() {
    createButtons();
    setButtonBackgroundColor();
  }

  private void createButtons() {
    buttons = new JButton[game.getSizeX()][game.getSizeY()];
    for (int y = 0; y < game.getSizeY(); y++) {
      for (int x = 0; x < game.getSizeX(); x++) {
        createButtonOnPosition(x, y);
      }
    }
  }

  private void createButtonOnPosition(int x, int y) {
    JButton button = new JButton(x + "," + y);
    buttons[x][y] = button;
    add(button);
    addButtonActions(button);
  }

  /**
   * action listener for the cells
   * 
   * @param button
   */
  protected void addButtonActions(JButton button) {
    button.addMouseMotionListener(new MouseMotionAdapter() {
      @Override
      public void mouseMoved(MouseEvent e) {
        if (game.isDrawingMode()) {
          onCellButtonAction(e);
        }
      };
    });
    button.addMouseListener(new MouseAdapter() {

      @Override
      public void mousePressed(MouseEvent e) {
        showPopupOnRightClick(e);
      }

      @Override
      public void mouseReleased(MouseEvent e) {
        showPopupOnRightClick(e);
      }

      private void showPopupOnRightClick(MouseEvent e) {
        if (e.isPopupTrigger()) {
          popupMenu.show(e.getComponent(), e.getX(), e.getY());
        }
      }
    });
    button.addActionListener(cellButtonClickListenerEvent -> {
      onCellButtonAction(cellButtonClickListenerEvent);
    });
  }

  protected void onCellButtonAction(EventObject e) {
    Object source = e.getSource();
    if (source instanceof JButton) {
      JButton jbutton = JButton.class.cast(source);
      onButtonAction(jbutton);
    }
  }

  /**
   * toggle cells to life or dead
   * 
   * @param source
   */
  private void onButtonAction(JButton source) {
    boolean x = true;
    String spositionX = "";
    String spositionY = "";

    int positionX;
    int positionY;

    String coordinates = source.getActionCommand();
    for (int i = 0; i < coordinates.length(); i++) {
      if (coordinates.substring(i, i + 1).equals(",")) {
        x = false;
        i++;
      }
      if (x) {
        spositionX = spositionX + (coordinates.substring(i, i + 1));
      } else {
        spositionY = spositionY + (coordinates.substring(i, i + 1));
      }
    }
    positionX = Integer.parseInt(spositionX);
    positionY = Integer.parseInt(spositionY);
    game.setStatus(positionX, positionY, !game.getStatus(positionX, positionY));

    setButtonColorBasedOnGame(source, positionX, positionY);
  }

  @Override
  public void update(Observable o, Object arg) {
    setButtonBackgroundColor();
  }

  /**
   * sets background color of the buttons
   */
  private void setButtonBackgroundColor() {
    for (int y = 0; y < game.getSizeY(); y++) {
      for (int x = 0; x < game.getSizeX(); x++) {
        setButtonColorBasedOnGame(buttons[x][y], x, y);
      }
    }
  }

  protected int getLeftOffset() {
    return this.leftOffset;
  }

  protected void setLeftOffset(int offset) {
    leftOffset = offset;
  }

  protected void setRotateRightCount(int rotateRightCount) {
    this.rotateRightCount = rotateRightCount;
  }

  protected int getRotateRightCount() {
    return rotateRightCount;
  }

  protected void setRotateLeftCount(int rotateLeftCount) {
    this.rotateLeftCount = rotateLeftCount;
  }

  protected int getRotateLeftCount() {
    return rotateLeftCount;
  }

  protected int getRightOffset() {
    return this.rightOffset;
  }

  protected void setRightOffset(int offset) {
    rightOffset = offset;
  }

  protected GoLGame getGame() {
    return game;
  }

  protected void setButtons(JButton[][] buttons) {
    this.buttons = buttons;
  }

  protected JButton[][] getButtons() {
    return buttons;
  }

  /**
   * sets button color
   * 
   * @param button
   * @param x
   * @param y
   */
  private void setButtonColorBasedOnGame(JButton button, int x, int y) {
    Color colorToSet = getColor(game.getStatus(x, y));
    button.setBackground(colorToSet);
    button.setForeground(colorToSet);
  }

  protected Color getColor(boolean isAlive) {
    return isAlive ? getAliveColor() : getDeadColor();
  }

  protected Color getAliveColor() {
    return aliveColor != null ? aliveColor : Color.GREEN;
  }

  protected Color getDeadColor() {
    return deadColor != null ? deadColor : Color.RED;
  }
}

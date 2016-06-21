import javax.swing.JButton;

/**
 * This class represents the rotated right view
 * 
 * @author Phi Long Tran <191624>
 * @author Manuel Wessner <191711>
 * @author Steve Nono <191709>
 */
public class GoLViewRightRotated extends GoLChildWindow {

  // instance variables
  private static final long serialVersionUID = 1L;
  private JButton[][] rotatedButtons;

  // constructor
  public GoLViewRightRotated(MainWindow dft, GoLGame game, int rotateCount) {
    super(dft, game);
    setRotateRightCount(rotateCount + 1);
    rotateButtons();
  }

  /**
   * rotate the buttons to the right
   */
  private void rotateButtons() {
    int sizeX = getGame().getSizeX();
    int sizeY = getGame().getSizeY();
    for (int i = 0; i < getRotateRightCount(); i++) {
      JButton[][] buttonsToRotate = getButtonsToRotate();
      rotatedButtons = rotateIt(buttonsToRotate, sizeX, sizeY);
      removeUnrotatedButtons(buttonsToRotate);
      addRotatedButtons(sizeX, sizeY);
    }
  }

  /**
   * rotate and add the rotated buttons to an array
   */
  private JButton[][] rotateIt(JButton[][] unrotatedButtonsArray, int sizeX, int sizeY) {
    rotatedButtons = new JButton[sizeX][sizeY];

    for (int i = 0; i < sizeY; i++) {
      for (int j = 0; j < sizeX; j++) {
        rotatedButtons[i][j] = unrotatedButtonsArray[j][sizeY - i - 1];
      }
    }
    return rotatedButtons;
  }

  private void addRotatedButtons(int sizeX, int sizeY) {
    for (int y = 0; y < sizeY; y++) {
      for (int x = 0; x < sizeX; x++) {
        add(rotatedButtons[x][y]);
      }
    }
  }

  private void removeUnrotatedButtons(JButton[][] unrotatedButtonsArray) {
    for (JButton[] unrotatedButtons : unrotatedButtonsArray) {
      for (JButton unrotaedButton : unrotatedButtons) {
        remove(unrotaedButton);
      }
    }
  }

  @Override
  protected JButton[][] getButtons() {
    return rotatedButtons;
  }

  private JButton[][] getButtonsToRotate() {
    if (rotatedButtons != null) {
      return rotatedButtons;
    } else {
      return super.getButtons();
    }
  }

}

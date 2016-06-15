import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

public class QuodFrame extends JInternalFrame {
  private static final long serialVersionUID = 5613284283010650242L;

  public QuodFrame(Game game) {
    super("Quod", true, true);
    JPanel gameBoard = new JPanel();
    gameBoard.setLayout(new GridLayout(11, 11, 1, 1));
    for (int i = 1; i < 11 * 11; i++) {
      gameBoard.add(createCell(String.valueOf(i)));
    }
    add(gameBoard);
    setSize(new Dimension(700, 700));
    setVisible(true);
  }

  private JButton createCell(String buttonNr) {
    JButton button = new JButton(buttonNr);

    // check if all chars are 1 (left upper corner, right upper corner, left
    // down corner)
    boolean invisible = buttonNr.chars().allMatch(c -> c == '1');
    button.setVisible(!invisible);
    return button;
  }

}

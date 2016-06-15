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
    int boardSize = game.getBoard().getSize();
    gameBoard.setLayout(new GridLayout(boardSize, boardSize, 1, 1));
    for (int i = 1; i < boardSize * boardSize; i++) {
      gameBoard.add(createCell(String.valueOf(i)));
    }
    add(gameBoard);
    setSize(new Dimension(700, 700));
    setVisible(true);
  }

  private JButton createCell(String buttonNr) {
    Cell cell = new Cell(buttonNr);
    return cell.asButton();
  }

}

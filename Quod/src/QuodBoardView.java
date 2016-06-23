import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * This class is responsible for the graphical representation
 * 
 * @author Phi Long Tran <191624>
 * @author Manuel Wessner <191711>
 * @author Steve Nono <191709>
 */

public class QuodBoardView {

  public static JPanel asPanel(QuodBoard board) {
    JPanel gameBoard = new JPanel();
    int boardSize = board.getSize();
    gameBoard.setLayout(new GridLayout(boardSize, boardSize));

    for (int x = 0; x < boardSize; x++) {
      for (int y = 0; y < boardSize; y++) {
        QuodCell cell = board.getCell(Position.of(x, y));
        JButton cellAsButton = cell.asButton();
        gameBoard.add(cellAsButton);
      }
    }
    return gameBoard;

  }


}

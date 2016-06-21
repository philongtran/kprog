import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class QuodBoardView {

  public static JPanel asPanel(QuodGame game) {
    JPanel gameBoard = new JPanel();
    JButton[][] buttons = new JButton[11][11];
    int boardSize = game.getBoard().getSize();
    gameBoard.setLayout(new GridLayout(boardSize, boardSize, 1, 1));
    int x = 0;
    int y = 0;
    for (int i = 1; i < boardSize * boardSize; i++) {
      QuodCell cell = new QuodCell(String.valueOf(i), game);
      JButton tempButton = cell.asButton();
      tempButton.setActionCommand(Position.of(x, y).asActionCommand());
      gameBoard.add(tempButton);
      buttons[y][x] = tempButton;
      // System.out.println(tempButton.getActionCommand());
      x++;
      if (x >= boardSize) {
        x = 0;
        y++;
      }
    }
    return gameBoard;

  }


}

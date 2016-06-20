import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

public class QuodFrame extends JInternalFrame implements Observer {
  private static final long serialVersionUID = 5613284283010650242L;
  private QuodGame game;

  public QuodFrame(QuodGame game) {
    super("Quod", true, true);
    this.game = game;
    JPanel gameBoard = new JPanel();
    JButton[][] buttons = new JButton[11][11];
    int boardSize = game.getBoard().getSize();
    gameBoard.setLayout(new GridLayout(boardSize, boardSize, 1, 1));
    int x = 0;
    int y = 0;
    for (int i = 1; i < boardSize * boardSize; i++) {
      JButton tempButton = createCell(String.valueOf(i));
      tempButton.setActionCommand(x + "," + y);
      gameBoard.add(tempButton);
      buttons[y][x] = tempButton;
      // System.out.println(tempButton.getActionCommand());
      x++;
      if (x >= boardSize) {
        x = 0;
        y++;
      }
    }
    add(gameBoard);
    setSize(new Dimension(700, 700));
    setVisible(true);
  }

  private JButton createCell(String buttonNr) {
    Cell cell = new Cell(buttonNr, game);
    return cell.asButton();
  }


  @Override
  public void update(Observable o, Object arg) {
    // TODO Auto-generated method stub

  }

}

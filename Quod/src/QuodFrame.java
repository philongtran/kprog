import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

public class QuodFrame extends JInternalFrame implements Observer {
  private static final long serialVersionUID = 5613284283010650242L;
  private final QuodGame game;

  public QuodFrame(QuodGame game) {
    super("Quod", true, true);
    this.game = game;
    game.addObserver(this);
    setLayout(new BorderLayout());
    add(new QuodPlayerStatsView(game.getPlayer1()).getPanel(), BorderLayout.WEST);
    add(new QuodPlayerStatsView(game.getPlayer2()).getPanel(), BorderLayout.EAST);
    add(QuodBoardView.asPanel(game.getBoard()), BorderLayout.CENTER);
    setSize(new Dimension(800, 700));
    setVisible(true);
  }


  @Override
  public void update(Observable o, Object arg) {
    if (o.equals(game)) {
      switch (game.getResult()) {
        case WIN:
          String winMessage = game.getPlayer() + " won";
          showWinMessage(winMessage);
          break;
        case DRAW:
          showWinMessage("It's a draw");
          break;
        case ONGOING:
          break;
      }
    }
  }


  private void showWinMessage(String winMessage) {
    JOptionPane.showMessageDialog(this, winMessage);
  }

}

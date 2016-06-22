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
    add(QuodBoardView.asPanel(game.getBoard()));
    setSize(new Dimension(700, 700));
    setVisible(true);
  }


  @Override
  public void update(Observable o, Object arg) {
    if (o.equals(game)) {
      if (!game.isRunning()) {
        String winMessage = game.getPlayer() + " won";
        JOptionPane.showMessageDialog(this, winMessage);
        System.out.println(winMessage);
      }
    }
  }

}

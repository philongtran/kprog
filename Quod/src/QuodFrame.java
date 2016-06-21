import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JInternalFrame;

public class QuodFrame extends JInternalFrame implements Observer {
  private static final long serialVersionUID = 5613284283010650242L;

  public QuodFrame(QuodGame game) {
    super("Quod", true, true);
    add(QuodBoardView.asPanel(game.getBoard()));
    setSize(new Dimension(700, 700));
    setVisible(true);
  }


  @Override
  public void update(Observable o, Object arg) {
    // TODO Auto-generated method stub

  }

}

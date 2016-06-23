import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JInternalFrame;
import javax.swing.Timer;

public class RegenbogenWindow extends JInternalFrame implements ActionListener {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  Color[] colors =
      {Color.red, Color.orange, Color.yellow, Color.green, Color.blue, Color.cyan, Color.magenta};
  int col = 0;

  public RegenbogenWindow() {
    super("Rainbow", true, true);
    setSize(400, 400);
    setBackground(colors[col]);
    setVisible(true);
    int delay = 750; // milliseconds
    ActionListener taskPerformer = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent evt) {
        col = (col + 1) % colors.length;
        setBackground(colors[col]);
        repaint();
      }
    };
    new Timer(delay, taskPerformer).start();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    col = (col + 1) % colors.length;
    setBackground(colors[col]);
    repaint();
  }

}

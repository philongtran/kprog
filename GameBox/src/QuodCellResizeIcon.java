import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * This class resizes the image to a manageable size
 * 
 * @author Phi Long Tran <191624>
 * @author Manuel Wessner <191711>
 * @author Steve Nono <191709>
 */

final class QuodCellResizeIcon extends ComponentAdapter {
  private final JButton quodCellButton;

  QuodCellResizeIcon(JButton quodCellButton) {
    this.quodCellButton = quodCellButton;
  }

  @Override
  public void componentShown(ComponentEvent e) {
    resizeIcon();
  }

  @Override
  public void componentResized(ComponentEvent e) {
    resizeIcon();
  }

  private void resizeIcon() {
    ImageIcon resizedIcon;
    Color color = quodCellButton.getForeground();
    if (color.equals(Color.RED)) {
      resizedIcon = IconHelper.getIcon("red", quodCellButton);
    } else if (color.equals(Color.BLUE)) {
      resizedIcon = IconHelper.getIcon("blue", quodCellButton);
    } else if (color.equals(Color.GRAY)) {
      resizedIcon = IconHelper.getIcon("quasar", quodCellButton);
    } else if (color.equals(Color.YELLOW)) {
      resizedIcon = IconHelper.getIcon("won", quodCellButton);
    } else {
      resizedIcon = IconHelper.getIcon("emptyCell", quodCellButton);
    }

    quodCellButton.setIcon(resizedIcon);
  }
}

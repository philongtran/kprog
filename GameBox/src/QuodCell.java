import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

/**
 * This class is responsible for cell
 * 
 * @author Phi Long Tran <191624>
 * @author Manuel Wessner <191711>
 * @author Steve Nono <191709>
 */

public class QuodCell {
  // instance variables
  private boolean invisible;
  private JButton button;
  private final QuodGame game;
  private QuodCellContent content;
  private final Position position;

  /**
   * constructor
   * 
   * @param position
   * @param game
   */
  public QuodCell(Position position, QuodGame game) {
    this.position = position;
    this.game = game;
    this.invisible = QuodCellBorders.get().contains(position);
    this.content = QuodCellContent.EMPTY;
  }

  /**
   * create a button for the cell
   * 
   * @return
   */
  public JButton asButton() {
    button = new JButton(IconHelper.getIcon("emptyCell", 60, 60));
    button.addComponentListener(new QuodCellResizeIcon(button));
    button.setVisible(!invisible);
    button.addActionListener(event -> {
      onClick();
    });
    button.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        onRightClick(e);
      }
    });
    return button;
  }

  /**
   * action listener for left mouse click, adds player stone
   */
  public void onClick() {
    if (isFree() && game.isRunning()) {
      QuodPlayer player = game.getPlayer();
      if (!player.hasUsedAllStones()) {
        player.reduceRemainingStones();
        setContent(QuodCellContent.QUAD);
        setColor(player.getColor());

        game.setBoard(getPosition(), player);
        game.switchPlayer();
      }
    }
  }

  /**
   * action listener for right mouse button, adds grey stone
   * 
   * @param e
   */
  private void onRightClick(MouseEvent e) {
    boolean isRightClick = e.getButton() == MouseEvent.BUTTON3;
    QuodPlayer player = game.getPlayer();
    if (isFree() && game.isRunning() && isRightClick && player.hasGreyStones()) {
      player.reduceGreyStones();
      setContent(QuodCellContent.QUASAR);
      setColor(Color.GRAY);
    }
  }

  /**
   * set the color of the cell ( which stone its occupied with)
   * 
   * @param color
   */
  void setColor(Color color) {
    if (color.equals(Color.RED)) {
      IconHelper.setIcon("red", button);
    } else if (color.equals(Color.BLUE)) {
      IconHelper.setIcon("blue", button);
    } else if (color.equals(Color.GRAY)) {
      IconHelper.setIcon("quasar", button);
    }
    button.setForeground(color);
    button.setBackground(color);
  }

  /**
   * returns status if cell is free
   * 
   * @return
   */
  public boolean isFree() {
    return getContent().equals(QuodCellContent.EMPTY);
  }

  public QuodCellContent getContent() {
    return content;
  }

  public void setContent(QuodCellContent content) {
    if (!content.equals(QuodCellContent.EMPTY)) {
      game.getBoard().getFreeCellPositions().remove(getPosition());
    }
    this.content = content;
  }

  public Position getPosition() {
    return position;
  }

  @Override
  public String toString() {
    return position.toString() + ", " + getContent();
  }

}

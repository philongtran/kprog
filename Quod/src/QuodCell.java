import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class QuodCell {

  private boolean invisible;
  private JButton button;
  private final QuodGame game;
  private QuodCellContent content;
  private final Position position;

  public QuodCell(Position position, QuodGame game) {
    this.position = position;
    this.game = game;
    this.invisible = QuodCellBorders.get().contains(position);
    this.content = QuodCellContent.EMPTY;
  }

  public JButton asButton() {
    button = new JButton(IconHelper.getIcon("emptyCell", 60, 60));
    button.addComponentListener(new QuodCellResizeIcon(button));
    button.setVisible(!invisible);
    button.addActionListener(event -> {
      onClick(event);
    });
    button.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        onRightClick(e);
      }
    });
    return button;
  }

  private void onClick(ActionEvent e) {
    if (isFree() && game.isRunning()) {
      QuodPlayer player = game.getPlayer();
      if (!player.hasUsedAllStones()) {
        player.reduceRemainingStones();
        System.out.println("Player stones left: " + player.getRemainingStones());
        setContent(QuodCellContent.QUAD);
        setColor(player.getColor());

        game.setBoard(position, player);
        game.switchPlayer();
      }
    }
  }

  private void onRightClick(MouseEvent e) {
    boolean isRightClick = e.getButton() == MouseEvent.BUTTON3;
    QuodPlayer player = game.getPlayer();
    if (isFree() && game.isRunning() && isRightClick && player.hasGreyStones()) {
      player.reduceGreyStones();
      setContent(QuodCellContent.QUASAR);
      setColor(Color.GRAY);
      System.out.println("Grey stones left: " + player.hasGreyStones());
    }
  }

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

  public boolean isFree() {
    return getContent().equals(QuodCellContent.EMPTY);
  }

  public QuodCellContent getContent() {
    return content;
  }

  public void setContent(QuodCellContent content) {
    this.content = content;
  }

}

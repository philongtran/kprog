import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class QuodCell {

  private String cellText;
  private boolean invisible;
  private JButton button;
  private QuodGame game;
  private QuodCellContent content;

  public QuodCell(String cellText, QuodGame game) {
    this.cellText = cellText;
    this.game = game;
    // check if all chars are 1 (left upper corner, right upper corner, left
    // down corner)
    this.invisible = cellText.chars().allMatch(c -> c == '1');
    this.content = QuodCellContent.EMPTY;
  }

  public JButton asButton() {
    button = new JButton(cellText);
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
      if (!player.isDone() && !game.getUseGreyStones()) {
        player.reduceRemainingStones();
        System.out.println("Player stones left: " + player.getRemainingStones());
        setContent(QuodCellContent.QUAD);
        setColor(player.getColor());

        Position position = Position.fromActionCommand(e.getActionCommand());
        game.setBoard(position, player);
        game.switchPlayer();
      }
    }
  }

  private void onRightClick(MouseEvent e) {
    boolean isRightClick = e.getButton() == MouseEvent.BUTTON3;
    QuodPlayer player = game.getPlayer();
    if (isRightClick && player.hasGreyStones() && isFree()) {
      player.reduceGreyStones();
      setContent(QuodCellContent.QUASAR);
      setColor(Color.GRAY);
      System.out.println("Grey stones left: " + player.hasGreyStones());
    }
  }

  void setColor(Color color) {
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

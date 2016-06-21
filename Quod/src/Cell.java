import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class Cell {

  private String cellText;
  private boolean invisible;
  private JButton button;
  private QuodGame game;
  private CellContent content;

  public Cell(String cellText, QuodGame game) {
    this.cellText = cellText;
    this.game = game;
    // check if all chars are 1 (left upper corner, right upper corner, left
    // down corner)
    this.invisible = cellText.chars().allMatch(c -> c == '1');
    this.content = CellContent.EMPTY;
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
    if (game.isRunning()) {
      String actionCommand = e.getActionCommand();
      Position position = Position.fromActionCommand(actionCommand);
      if (isFree()) {
        // System.out.println(x + "," + y);
        Player player = game.getPlayer();
        if (!player.isDone() && !game.getUseGreyStones()) {
          player.reduceRemainingStones();
          System.out.println("Player stones left: " + player.getRemainingStones());
          setContent(CellContent.QUAD);
          setColor(player.getColor());
          game.setBoard(position, player);

          game.switchPlayer();
        }
      }

    }
  }

  private void onRightClick(MouseEvent e) {
    boolean isRightClick = e.getButton() == MouseEvent.BUTTON3;
    Player player = game.getPlayer();
    if (isRightClick && player.hasGreyStones() && isFree()) {
      player.setGreyStones();
      setContent(CellContent.QUASAR);
      setColor(Color.GRAY);
      System.out.println("Grey stones left: " + player.hasGreyStones());
    }
  }

  void setColor(Color color) {
    button.setForeground(color);
    button.setBackground(color);
  }

  public boolean isFree() {
    return getContent().equals(CellContent.EMPTY);
  }

  public CellContent getContent() {
    return content;
  }

  public void setContent(CellContent content) {
    this.content = content;
  }

}

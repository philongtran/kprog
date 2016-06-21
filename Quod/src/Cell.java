import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.JButton;

public class Cell {

  private String cellText;
  private boolean invisible;
  private JButton button;
  private QuodGame game;
  private int x, y;
  private boolean used = false;

  private int player = 0;

  public Cell(String cellText, QuodGame game) {
    this.cellText = cellText;
    this.game = game;
    // check if all chars are 1 (left upper corner, right upper corner, left
    // down corner)
    this.invisible = cellText.chars().allMatch(c -> c == '1');
  }

  public JButton asButton() {
    button = new JButton(cellText);
    button.setVisible(!invisible);
    button.addActionListener(event -> {
      onClick(event);
    });
    return button;
  }

  public int getPlayer() {
    return player;
  }

  public void setPlayer(int playerID) {
    player = playerID;
  }

  public void onClick(ActionEvent e) {
    if (game.isRunning() && !used) {
      String actionCommand = e.getActionCommand();
      int commaPosition = actionCommand.indexOf(",");
      x = Integer.parseInt(actionCommand.substring(0, commaPosition));
      y = Integer.parseInt(actionCommand.substring(commaPosition + 1));
      // System.out.println(x + "," + y);
      Player player = game.getPlayer();
      if (!player.isDone() && !game.getUseGreyStones()) {
        player.reduceRemainingStones();
        setColor(player.getColor());
        System.out.println("Player stones left: " + player.getRemainingStones());

        game.setBoard(x, y, player);


        game.switchPlayer();
        used = true;
      } else if (player.getGreyStones() > 0 && game.getUseGreyStones()) {
        player.setGreyStones();
        setColor(Color.WHITE);
        System.out.println("Grey stones left: " + player.getGreyStones());
        used = true;
      }

    }
  }

  void setColor(Color color) {
    button.setForeground(color);
    button.setBackground(color);
  }

}

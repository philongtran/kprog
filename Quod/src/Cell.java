import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.JButton;

public class Cell {

  private String cellText;
  private boolean invisible;
  private JButton button;
  private Color[] colors = {Color.WHITE, Color.BLUE, Color.RED, Color.DARK_GRAY};
  private int colorID = 0;
  private QuodGame game;
  private int x, y;

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
    String actionCommand = e.getActionCommand();
    int commaPosition = actionCommand.indexOf(",");
    x = Integer.parseInt(actionCommand.substring(0, commaPosition));
    y = Integer.parseInt(actionCommand.substring(commaPosition + 1));
    // System.out.println(x + "," + y);
    if (player < 3) { // toggle cell between players for testing purposes
      player++;
      colorID++;
    } else {
      colorID = 0;
      player = 0;
    }

    setColor(colors[colorID]);
    // System.out.println(e.getActionCommand());

    game.setBoard(x, y, 1);
  }

  void setColor(Color color) {
    button.setForeground(color);
    button.setBackground(color);
  }

}

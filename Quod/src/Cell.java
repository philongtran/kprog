import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.JButton;

public class Cell {

  private String cellText;
  private boolean invisible;
  private JButton button;
  private Color[] colors = {Color.WHITE, Color.BLUE, Color.RED, Color.DARK_GRAY};
  private int colorID = 0;

  private int player = 0;

  public Cell(String cellText) {
    this.cellText = cellText;
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
    if (player < 3) { // toggle cell between players for testing purposes
      player++;
      colorID++;
    } else {
      colorID = 0;
      player = 0;
    }

    setColor(colors[colorID]);
    System.out.println(player);
  }

  void setColor(Color color) {
    button.setForeground(color);
    button.setBackground(color);
  }

}

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.JButton;

public class Cell {

  private String cellText;
  private boolean invisible;
  private JButton button;

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

  public void onClick(ActionEvent e) {

  }

  void setColor(Color color) {
    button.setForeground(color);
    button.setBackground(color);
  }

}

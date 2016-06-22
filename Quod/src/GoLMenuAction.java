
import java.util.Optional;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;

/**
 * Reusable enumeration for all menu actions and its menu description
 * 
 * @author Phi Long Tran <191624>
 * @author Manuel Wessner <191711>
 * @author Steve Nono <191709>
 */
public enum GoLMenuAction {

  // menu items
  NONE("Unknown"), START_STOP("Start/Stop"), DRAW("Draw"), SAVE("Save"), LOAD("Load"), EXIT(
      "Exit"), FASTER("Faster"), SLOWER("Slower"), RESET("Reset"), ROTATELEFT(
          "Rotate view to the left"), ROTATERIGHT("Rotate view to the right"), MAINVIEW(
              "Main view"), BLINKER("Blinker"), GLIDER("Glider"), GLIDERCANNON("Glider cannon");

  private final String description;

  private GoLMenuAction(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }

  // add menu item and give it an icon if needed
  public JMenuItem asMenuItem() {
    Optional<ImageIcon> icon = Optional.ofNullable(getIcon());
    JMenuItem menuItem = new JMenuItem(getDescription());
    if (icon.isPresent()) {
      menuItem.setIcon(icon.get());
    }
    return menuItem;
  }

  private ImageIcon getIcon() {
    return IconHelper.getIcon(toString().toLowerCase(), 30, 30);
  }

  static GoLMenuAction of(String action) {
    for (GoLMenuAction menuAction : values()) {
      if (action.equals(menuAction.getDescription())) {
        return menuAction;
      }
    }
    return NONE;
  }
}

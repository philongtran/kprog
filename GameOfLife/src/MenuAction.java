
import java.awt.Image;
import java.net.URL;
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
public enum MenuAction {

	// menu items
	NONE("Unknown"), START_STOP("Start/Stop"), DRAW("Draw"), EXIT("Exit"), FASTER("Faster"), SLOWER("Slower"), RESET(
			"Reset"), LEFTVIEW("Shift view to left"), RIGHTVIEW("Shift view to right"), ROTATELEFT(
					"Rotate view to the left"), ROTATERIGHT("Rotate view to the right"), MAINVIEW("Main view"), BLINKER(
							"Blinker"), GLIDER("Glider"), GLIDERCANNON("Glider cannon");

	private static final String IMAGEFILEEXTENSION = ".png";
	private final String description;

	private MenuAction(String description) {
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
		String imageName = toString().toLowerCase() + IMAGEFILEEXTENSION;
		URL imagePath = getClass().getResource("resources/icons/" + imageName);
		if (imagePath != null) {
			Image resizedImage = new ImageIcon(imagePath).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
			return new ImageIcon(resizedImage);
		}
		return null;
	}

	static MenuAction of(String action) {
		for (MenuAction menuAction : values()) {
			if (action.equals(menuAction.getDescription())) {
				return menuAction;
			}
		}
		return NONE;
	}
}

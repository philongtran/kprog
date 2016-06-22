import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class IconHelper {

  private static final String IMAGEFILEEXTENSION = ".png";

  public static ImageIcon getIcon(String imageName, int width, int height) {
    String image = imageName + IMAGEFILEEXTENSION;
    URL imagePath = IconHelper.class.getResource("resources/icons/" + image);
    if (imagePath != null) {
      Image resizedImage =
          new ImageIcon(imagePath).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
      return new ImageIcon(resizedImage);
    }
    return null;
  }

  public static ImageIcon getIcon(String imageName, JButton button) {
    return getIcon(imageName, button.getWidth(), button.getHeight());
  }

  public static void setIcon(String imageName, JButton button) {
    button.setIcon(getIcon(imageName, button));
  }

}

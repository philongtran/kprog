import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * This class loads the menu icons and applies it to the menubar
 * 
 * @author Phi Long Tran <191624>
 * @author Manuel Wessner <191711>
 * @author Steve Nono <191709>
 */

public class IconHelper {

  private static final String IMAGEFILEEXTENSION = ".png";

  /**
   * loads the image from hdd
   * 
   * @param imageName
   * @param width
   * @param height
   * @return
   */
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

  /**
   * this method creates an icon with an image and size
   * 
   * @param imageName
   * @param button
   * @return
   */
  public static ImageIcon getIcon(String imageName, JButton button) {
    return getIcon(imageName, button.getWidth(), button.getHeight());
  }

  /**
   * this method applies an icon to a button
   * 
   * @param imageName
   * @param button
   */
  public static void setIcon(String imageName, JButton button) {
    button.setIcon(getIcon(imageName, button));
  }

}

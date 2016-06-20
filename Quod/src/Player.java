import java.awt.Color;

public class Player {

  private final Color color;

  public Player(Color color) {
    this.color = color;
  }

  public Color getColor() {
    return color;
  }

  @Override
  public String toString() {
    return color.toString();
  }
}

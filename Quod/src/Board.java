
public class Board {

  private static final int DEFAULTBOARDSIZE = 11;
  private final int height;
  private final int width;

  public Board() {
    this(DEFAULTBOARDSIZE, DEFAULTBOARDSIZE);
  }

  public Board(int width, int height) {
    this.width = width;
    this.height = height;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }
}

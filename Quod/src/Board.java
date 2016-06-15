
public class Board {

  private static final int DEFAULTBOARDSIZE = 11;
  private final int size;

  public Board() {
    this(DEFAULTBOARDSIZE);
  }

  private Board(int size) {
    this.size = size;
  }

  public int getSize() {
    return size;
  }
}

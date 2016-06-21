import java.util.Arrays;
import java.util.List;

public class QuodCellBorders {
  private static final Position leftUpperBoarder = Position.of(0, 0);
  private static final Position rightUpperBoarder = Position.of(0, 10);
  private static final Position leftBottomBoarder = Position.of(10, 0);
  private static final Position rightBottomBoarder = Position.of(10, 10);

  static List<Position> get() {
    return Arrays.asList(rightUpperBoarder, leftUpperBoarder, rightBottomBoarder,
        leftBottomBoarder);
  }
}

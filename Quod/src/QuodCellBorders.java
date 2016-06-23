import java.util.Arrays;
import java.util.List;

/**
 * This class marks the corner of the board which is not used
 * 
 * @author Phi Long Tran <191624>
 * @author Manuel Wessner <191711>
 * @author Steve Nono <191709>
 */

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

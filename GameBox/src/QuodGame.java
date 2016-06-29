import java.awt.Color;
import java.util.List;
import java.util.Observable;
import java.util.Optional;
import java.util.Random;

import javax.swing.Timer;

/**
 * This class is responsible for game logic
 * 
 * @author Phi Long Tran <191624>
 * @author Manuel Wessner <191711>
 * @author Steve Nono <191709>
 */

public class QuodGame extends Observable {
  // instance variables
  private final QuodBoard board;
  private final QuodPlayer player1;
  private final QuodPlayer player2;
  private QuodPlayer currentPlayer;
  private QuodResult result;
  private boolean aiMovementInProgress;
  private Position attackStone;

  /**
   * constructor, create players and board
   */
  QuodGame() {
    this(false);
  }

  QuodGame(boolean withAI) {
    board = new QuodBoard(this);
    player1 = new QuodPlayer(Color.blue, "Player One");
    player2 = new QuodPlayer(Color.red, "Player Two", withAI);
    currentPlayer = player1;
    result = QuodResult.ONGOING;
    if (withAI) {
      createPostPonedAIMove();
    }
  }

  private void createPostPonedAIMove() {
    int delay = 1800;
    new Timer(delay, listener -> {
      doAIMove();
    }).start();
  }

  /**
   * returns the board
   * 
   * @return
   */
  public QuodBoard getBoard() {
    return board;
  }

  /**
   * add stones to the board and checks for winner
   * 
   * @param stonePosition
   * @param player
   */
  public void setBoard(Position stonePosition, QuodPlayer player) {
    player.getExistingStones().add(stonePosition);
    positionCheckForPlayer(player.getExistingStones());
    existWinnerOnEnd();
    areAllStonesUsed();
    setChanged();
    notifyObservers();
  }

  /**
   * calculates if draw or win
   */
  private void existWinnerOnEnd() {
    if (areAllStonesUsed()) {
      boolean draw = player1.getGreyStones() == player2.getGreyStones();
      boolean playerOneWon = player1.getGreyStones() > player2.getGreyStones();
      if (!draw) {
        setResult(QuodResult.DRAW);
      } else if (playerOneWon) {
        setResult(QuodResult.WIN);
        switchToWinningPlayer(player1);
      } else {
        setResult(QuodResult.WIN);
        switchToWinningPlayer(player2);
      }
    }
  }

  private void switchToWinningPlayer(QuodPlayer winningPlayer) {
    if (!currentPlayer.equals(winningPlayer)) {
      switchPlayer();
    }
  }

  /**
   * checks if player 1 and player 2 used all their stones
   * 
   * @return
   */
  private boolean areAllStonesUsed() {
    return isRunning() && player1.hasUsedAllStones() && player2.hasUsedAllStones();
  }

  /**
   * calculate the position for the winning condition
   * 
   * @param playerStones
   */
  private void positionCheckForPlayer(List<Position> playerStones) {
    for (int lineIndex = 0; lineIndex < playerStones.size(); lineIndex++) {
      for (int verticalIndex = 0; verticalIndex < playerStones.size(); verticalIndex++) {
        if (lineIndex != verticalIndex) {
          Position lineStone = playerStones.get(lineIndex);
          Position verticalStone = playerStones.get(verticalIndex);
          Position lineStoneToFind = new Position(
              verticalStone.getPositionX() - verticalStone.getPositionY()
                  + lineStone.getPositionY(),
              verticalStone.getPositionY() + verticalStone.getPositionX()
                  - lineStone.getPositionX());
          Position verticalStoneToFind = new Position(
              lineStone.getPositionX() - verticalStone.getPositionY() + lineStone.getPositionY(),
              lineStone.getPositionY() + verticalStone.getPositionX() - lineStone.getPositionX());

          if (getBoard().isValidPosition(verticalStoneToFind)
              && getBoard().isValidPosition(lineStoneToFind)) {
            boolean lineStoneFound = playerStones.contains(lineStoneToFind);
            boolean verticalStoneFound = playerStones.contains(verticalStoneToFind);
            if (lineStoneFound && verticalStoneFound) {
              setResult(QuodResult.WIN);
              return;
            }
          }
        }
      }
    }
  }


  /**
   * returns current player
   * 
   * @return
   */
  public QuodPlayer getPlayer() {
    return currentPlayer;
  }

  /**
   * switches the player after another players turn
   */
  public void switchPlayer() {
    currentPlayer = getOtherPlayer();
    setChanged();
    notifyObservers();
  }

  private void doAIMove() {
    if (currentPlayer.isAI() && !aiMovementInProgress) {
      aiMovementInProgress = true;
      if (shouldAIAttack()) {
        placeAttack();
      }
      Position aiPosition = getPositionToSet();
      getBoard().getCell(aiPosition).onClick();
      aiMovementInProgress = false;
    }
  }

  private QuodPlayer getOtherPlayer() {
    if (currentPlayer.equals(player1)) {
      return player2;
    } else {
      return player1;
    }
  }

  private void placeAttack() {
    getBoard().getCell(attackStone).onRightClick();
  }

  private boolean shouldAIAttack() {
    if (getPlayer().getGreyStones() < getOtherPlayer().getGreyStones()) {
      return false;
    }
    List<Position> playerStones = getOtherPlayer().getExistingStones();
    for (int lineIndex = 0; lineIndex < playerStones.size(); lineIndex++) {
      for (int verticalIndex = 0; verticalIndex < playerStones.size(); verticalIndex++) {
        if (lineIndex != verticalIndex) {
          Position lineStone = playerStones.get(lineIndex);
          Position verticalStone = playerStones.get(verticalIndex);
          Position lineStoneToFind = new Position(
              verticalStone.getPositionX() - verticalStone.getPositionY()
                  + lineStone.getPositionY(),
              verticalStone.getPositionY() + verticalStone.getPositionX()
                  - lineStone.getPositionX());
          Position verticalStoneToFind = new Position(
              lineStone.getPositionX() - verticalStone.getPositionY() + lineStone.getPositionY(),
              lineStone.getPositionY() + verticalStone.getPositionX() - lineStone.getPositionX());
          if (getBoard().isValidPosition(verticalStoneToFind)
              && getBoard().isValidPosition(lineStoneToFind)) {
            boolean lineStoneFound = playerStones.contains(lineStoneToFind);
            boolean verticalStoneFound = playerStones.contains(verticalStoneToFind);
            boolean isVerticalStoneFree = getBoard().getCell(verticalStoneToFind).isFree();
            boolean isLineStoneFree = getBoard().getCell(lineStoneToFind).isFree();
            if (lineStoneFound && isVerticalStoneFree) {
              attackStone = verticalStoneToFind;
              return true;
            } else if (verticalStoneFound && isLineStoneFree) {
              attackStone = lineStoneToFind;
              return true;
            }
          }
        }
      }
    }
    return false;
  }

  private Position getPositionToSet() {
    Position bestPosition = getBestPosition(currentPlayer.getExistingStones()) //
        .orElseGet(() -> getBestPosition(getOtherPlayer().getExistingStones()) //
            .orElseGet(() -> {
              List<Position> freeCellPositions = getBoard().getFreeCellPositions();
              int randomPositionIndex = new Random().nextInt(freeCellPositions.size());
              return freeCellPositions.get(randomPositionIndex);
            }));
    return bestPosition;
  }

  private Optional<Position> getBestPosition(List<Position> playerStones) {
    for (int lineIndex = 0; lineIndex < playerStones.size(); lineIndex++) {
      for (int verticalIndex = 0; verticalIndex < playerStones.size(); verticalIndex++) {
        if (lineIndex != verticalIndex) {
          Position lineStone = playerStones.get(lineIndex);
          Position verticalStone = playerStones.get(verticalIndex);
          Position lineStoneToFind = new Position(
              verticalStone.getPositionX() - verticalStone.getPositionY()
                  + lineStone.getPositionY(),
              verticalStone.getPositionY() + verticalStone.getPositionX()
                  - lineStone.getPositionX());
          Position verticalStoneToFind = new Position(
              lineStone.getPositionX() - verticalStone.getPositionY() + lineStone.getPositionY(),
              lineStone.getPositionY() + verticalStone.getPositionX() - lineStone.getPositionX());
          if (getBoard().isValidPosition(verticalStoneToFind)
              && getBoard().isValidPosition(lineStoneToFind)) {
            boolean lineStoneFound = playerStones.contains(lineStoneToFind);
            boolean verticalStoneFound = playerStones.contains(verticalStoneToFind);
            boolean isVerticalStoneFree = getBoard().getCell(verticalStoneToFind).isFree();
            boolean isLineStoneFree = getBoard().getCell(lineStoneToFind).isFree();
            if (lineStoneFound && isVerticalStoneFree) {
              return Optional.of(verticalStoneToFind);
            } else if (verticalStoneFound && isLineStoneFree) {
              return Optional.of(lineStoneToFind);
            } else if (isVerticalStoneFree) {
              return Optional.of(verticalStoneToFind);
            } else if (isLineStoneFree) {
              return Optional.of(lineStoneToFind);
            }
          }
        }
      }
    }
    return Optional.empty();
  }

  /**
   * is the game still running?
   * 
   * @return
   */
  public boolean isRunning() {
    return getResult().equals(QuodResult.ONGOING);
  }

  /**
   * gets the game status
   * 
   * @return
   */
  public QuodResult getResult() {
    return result;
  }

  /**
   * sets the game status
   * 
   * @param result
   */
  public void setResult(QuodResult result) {
    this.result = result;
  }

  /**
   * returns payer 1
   * 
   * @return
   */
  public QuodPlayer getPlayer1() {
    return player1;
  }

  /**
   * returns player 2
   * 
   * @return
   */
  public QuodPlayer getPlayer2() {
    return player2;
  }

}

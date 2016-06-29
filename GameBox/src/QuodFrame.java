import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * This class is responsible for the frame
 * 
 * @author Phi Long Tran <191624>
 * @author Manuel Wessner <191711>
 * @author Steve Nono <191709>
 */

public class QuodFrame extends JInternalFrame implements Observer {
  // instance variables
  private static final long serialVersionUID = 5613284283010650242L;
  private final QuodGame game;
  private JLabel lblPlayerTurn;
  boolean finished;

  /**
   * constructor
   * 
   * @param game
   */
  public QuodFrame(QuodGame game) {
    super("Quod", true, true);
    this.game = game;
    game.addObserver(this);
    setBackground(Color.black);
    setLayout(new BorderLayout());
    add(new QuodPlayerStatsView(game.getPlayer1()).getPanel(), BorderLayout.WEST);
    add(new QuodPlayerStatsView(game.getPlayer2()).getPanel(), BorderLayout.EAST);
    add(QuodBoardView.asPanel(game.getBoard()), BorderLayout.CENTER);
    add(createStatusPanel(), BorderLayout.SOUTH);
    setSize(new Dimension(800, 700));
    setVisible(true);
  }


  @Override
  public void update(Observable o, Object arg) {
    if (o.equals(game)) {
      checkForWinMessage();
      setPlayerTurnLabel();
    }
  }


  /**
   * creates the status panel
   * 
   * @return
   */
  private JPanel createStatusPanel() {
    JPanel statusPanel = new JPanel();
    lblPlayerTurn = new JLabel();
    setPlayerTurnLabel();
    statusPanel.add(new JLabel("Active Player: "));
    statusPanel.add(lblPlayerTurn);
    return statusPanel;
  }


  /**
   * display which players turn it is
   */
  private void setPlayerTurnLabel() {
    lblPlayerTurn.setText(game.getPlayer().getPlayerDescription());
    lblPlayerTurn.setForeground(game.getPlayer().getColor());
  }

  /**
   * set win condition messages
   */
  private void checkForWinMessage() {
    if (!finished) {
      switch (game.getResult()) {
        case WIN:
          String winMessage = game.getPlayer() + " won";
          showWinMessage(winMessage);
          finished = true;
          break;
        case DRAW:
          showWinMessage("It's a draw");
          finished = true;
          break;
        case ONGOING:
          break;
      }
    }
  }

  /**
   * display win massage
   * 
   * @param winMessage
   */
  private void showWinMessage(String winMessage) {
    JOptionPane.showMessageDialog(this, winMessage);
  }

}

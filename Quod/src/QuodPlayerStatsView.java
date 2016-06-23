import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * This class is responsible for the player status
 * 
 * @author Phi Long Tran <191624>
 * @author Manuel Wessner <191711>
 * @author Steve Nono <191709>
 */

public class QuodPlayerStatsView implements Observer {


  private final QuodPlayer player;
  private final JPanel playerStats;
  private JLabel lblRemainingQuasarsContent;
  private JLabel lblRemainingStonesContent;

  /**
   * constructor
   * 
   * @param playerToDisplayStats
   */
  public QuodPlayerStatsView(QuodPlayer playerToDisplayStats) {
    player = playerToDisplayStats;
    player.addObserver(this);
    playerStats = new JPanel();
    playerStats.setBackground(Color.black);
    playerStats.setLayout(new BorderLayout());
    playerStats.add(createTitle(), BorderLayout.PAGE_START);
    playerStats.add(createDescription(), BorderLayout.LINE_START);
    playerStats.add(createContent(), BorderLayout.CENTER);
    playerStats.setSize(100, 700);
  }


  /**
   * creates the title window for the player stats
   * 
   * @return
   */
  private JLabel createTitle() {
    JLabel lblplayerTitle = new JLabel(player.getPlayerDescription());
    lblplayerTitle.setHorizontalAlignment(SwingConstants.LEFT);
    lblplayerTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
    lblplayerTitle.setForeground(player.getColor());
    return lblplayerTitle;
  }

  /**
   * creates the player stats
   * 
   * @return
   */
  private JPanel createContent() {
    JPanel content = new JPanel();
    content.setLayout(new GridLayout(5, 1, 1, 1));
    content.setBackground(Color.black);

    lblRemainingStonesContent = new JLabel(String.valueOf(player.getRemainingStones()));
    lblRemainingStonesContent.setForeground(Color.white);
    lblRemainingStonesContent.setHorizontalAlignment(SwingConstants.TRAILING);
    content.add(lblRemainingStonesContent);

    lblRemainingQuasarsContent = new JLabel(String.valueOf(player.getGreyStones()));
    lblRemainingQuasarsContent.setForeground(Color.white);
    lblRemainingQuasarsContent.setHorizontalAlignment(SwingConstants.TRAILING);
    content.add(lblRemainingQuasarsContent);
    return content;
  }

  /**
   * creates the description of the player stats
   * 
   * @return
   */
  private JPanel createDescription() {
    JPanel description = new JPanel();
    description.setBackground(Color.black);
    description.setLayout(new GridLayout(5, 1, 1, 1));

    JLabel lblRemainingStones = new JLabel("Quods:");
    lblRemainingStones.setForeground(Color.white);
    description.add(lblRemainingStones);

    JLabel lblRemainingQuasars = new JLabel("Quasars:");
    lblRemainingQuasars.setForeground(Color.white);
    description.add(lblRemainingQuasars);
    return description;
  }

  @Override
  public void update(Observable o, Object arg) {
    if (o.equals(player)) {
      lblRemainingStonesContent.setText(String.valueOf(player.getRemainingStones()));
      lblRemainingQuasarsContent.setText(String.valueOf(player.getGreyStones()));
    }
  }


  public JPanel getPanel() {
    return playerStats;
  }

}

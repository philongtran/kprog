import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class QuodPlayerStatsView implements Observer {


  private final QuodPlayer player;
  private final JPanel playerStats;
  private JLabel lblRemainingQuasarsContent;
  private JLabel lblRemainingStonesContent;

  public QuodPlayerStatsView(QuodPlayer player) {
    this.player = player;
    this.player.addObserver(this);
    playerStats = new JPanel();
    playerStats.setLayout(new BorderLayout());
    playerStats.add(createTitle(), BorderLayout.PAGE_START);
    playerStats.add(createDescription(), BorderLayout.LINE_START);
    playerStats.add(createContent(), BorderLayout.CENTER);
    playerStats.setSize(100, 700);
  }


  private JLabel createTitle() {
    JLabel lblplayerTitle = new JLabel(player.getPlayerDescription());
    lblplayerTitle.setHorizontalAlignment(SwingConstants.LEFT);
    lblplayerTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
    lblplayerTitle.setForeground(player.getColor());
    return lblplayerTitle;
  }

  private JPanel createContent() {
    JPanel content = new JPanel();
    content.setLayout(new GridLayout(5, 1, 1, 1));

    lblRemainingStonesContent = new JLabel(String.valueOf(player.getRemainingStones()));
    lblRemainingStonesContent.setHorizontalAlignment(SwingConstants.TRAILING);
    content.add(lblRemainingStonesContent);

    lblRemainingQuasarsContent = new JLabel(String.valueOf(player.getGreyStones()));
    lblRemainingQuasarsContent.setHorizontalAlignment(SwingConstants.TRAILING);
    content.add(lblRemainingQuasarsContent);
    return content;
  }

  private JPanel createDescription() {
    JPanel description = new JPanel();
    description.setLayout(new GridLayout(5, 1, 1, 1));

    JLabel lblRemainingStones = new JLabel("Quods:");
    description.add(lblRemainingStones);

    JLabel lblRemainingQuasars = new JLabel("Quasars:");
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

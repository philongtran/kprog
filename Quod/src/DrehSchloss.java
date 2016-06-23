import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * This application is an extended version of the "Schloss" from last week. The buttons are located
 * at the border and starting to rotate clockwise. Each time the user inputs a wrong number the
 * direction of the rotation changes. Wrong > buttons get colored red and then starts from the
 * beginning. Right > buttons get colored green and if the full combination is inputed correctly the
 * window closes.
 * 
 * 
 * @author Phi Long Tran <191624>
 * @author Steve Nono <191709>
 * @author Manuel Wessner <191711>
 */

public class DrehSchloss extends JInternalFrame implements ActionListener {

  // Instance variables

  private static final long serialVersionUID = 1L;

  static DrehSchloss jframe;
  int index = 0;
  JButton[] buttons = new JButton[10];
  JPanel panel;
  JPanel panel2;
  boolean switching = false;
  int[] code = {2, 3, 0, 3, 6, 0}; // secret code
  int i = 0;
  // Color col = Color.blue;

  /**
   * 
   * Constructor
   * 
   */
  public DrehSchloss() {
    super("Rotating Lock V1", true, true);
    setSize(480, 480);
    setLayout(new GridLayout(4, 3));
    panel = new JPanel();
    panel2 = new JPanel();
    for (int i = 0; i < 10; i++) { // register 10 buttons
      buttons[i] = new JButton("" + i); // and add them
      buttons[i].addActionListener(this); // register to the action
                                          // listener
    }

    // layout of the buttons to a ring
    add(buttons[1]);
    add(buttons[0]);
    add(buttons[9]);
    add(buttons[2]);
    add(panel);
    add(buttons[8]);
    add(buttons[3]);
    add(panel2);
    add(buttons[7]);
    add(buttons[4]);
    add(buttons[5]);
    add(buttons[6]);

    // timer which sends an action every 1sec.
    int delay = 1000; // milliseconds
    ActionListener taskPerformer = new ActionListener() {
      // make the ring rotate clockwise or counterclockwise
      @Override
      public void actionPerformed(ActionEvent evt) {
        if (switching) {
          counterClockwise();
        } else {
          clockwise();
        }
      }
    };
    new Timer(delay, taskPerformer).start();
  }

  /**
   * 
   * main method creating the frame and set the parameters
   * 
   * @param args
   */
  public static void main(String[] args) {
    jframe = new DrehSchloss();
    jframe.setSize(480, 480);
    jframe.setLayout(new GridLayout(4, 3));
    jframe.setTitle("Drehschloss");
    jframe.setVisible(true);
  }

  /**
   * 
   * let the ring rotate clockwise by changing the label from (old value +1)
   * 
   */
  public void clockwise() {
    for (int i = 0; i < 10; i++) {
      int old = Integer.parseInt(buttons[i].getActionCommand());
      old += 1;
      if (old < 10) {
        buttons[i].setText("" + old);
      } else { // if label would be set to number "10" it will set it to
               // "0"
        buttons[i].setText("0");
      }
    }
  }

  /**
   * 
   * let the ring rotate counterclockwise by changing the label from (old value -1)
   * 
   */
  public void counterClockwise() {
    for (int i = 0; i < 10; i++) {
      int old = Integer.parseInt(buttons[i].getActionCommand());
      if (old > 0) {
        old -= 1;
        buttons[i].setText("" + old);
      } else { // if label would be set to number "-1" it will set it to
               // "9"
        buttons[i].setText("9");
      }
    }
  }

  /**
   * 
   * setting the background of the buttons to either red (wrong input) or green (correct input)
   * 
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    if (Integer.parseInt(e.getActionCommand()) == code[i]) {
      i++;
      for (int i = 0; i < 10; i++) { // for all buttons
        buttons[i].setBackground(Color.green);
      }
      // SwingUtilities.updateComponentTreeUI(jframe);
      if (i >= code.length) {
        System.exit(0); // all numbers inputed correctly > close window
      }
    } else {
      switching = !switching; // wrong input > switch rotation
      i = 0;
      for (int i = 0; i < 10; i++) { // for all buttons
        buttons[i].setBackground(Color.red);
      }
      // SwingUtilities.updateComponentTreeUI(jframe);
    }

  }

}

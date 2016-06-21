import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
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
 *
 */

public class DrehSchlossV2 extends JInternalFrame {

  private static final int AMOUNTOFBUTTONS = 10;
  // Instance variables

  private static final long serialVersionUID = 1L;
  private static int universalTimer = 1000;
  private boolean isProcessing = false;
  private JButton[] buttons = new JButton[AMOUNTOFBUTTONS];
  private Panel panel;
  private Panel panel2;
  private boolean switching = false;
  private int[] code = {0, 7, 0, 4, 6, 1, 2}; // secret code
  private int codeIndex = 0;
  private static int instance = 0;

  public DrehSchlossV2() {
    this(universalTimer);
  }

  /**
   * Constructor
   */
  public DrehSchlossV2(int timer) {
    super("Rotating Lock", true, true);
    // creating panels and buttons
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    // this.timer = timer;
    panel = new Panel();
    panel2 = new Panel();
    for (int i = 0; i < AMOUNTOFBUTTONS; i++) { // register 10 buttons
      buttons[i] = new JButton("" + i); // and add them
      // buttons[i].addActionListener(this); // register to the action
      // listener
      buttons[i].addMouseMotionListener(mml);
      buttons[i].addMouseListener(ml);
    }

    setSize(480, 480);
    setLayout(new GridLayout(4, 3));
    setTitle("Drehschloss");
    setVisible(true);

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
    int delay = timer; // milliseconds
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
    new DrehSchlossV2(universalTimer);
  }

  /**
   * 
   * let the ring rotate clockwise by changing the label from (old value +1)
   * 
   */
  public void clockwise() {
    for (int i = 0; i < AMOUNTOFBUTTONS; i++) {
      int old = Integer.parseInt(buttons[i].getActionCommand());
      old += 1;
      if (old < AMOUNTOFBUTTONS) {
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
    for (int i = 0; i < AMOUNTOFBUTTONS; i++) {
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

  MouseListener ml = new MouseAdapter() {

    @Override
    public void mouseReleased(MouseEvent e) {
      isProcessing = false;
    }

  };

  MouseMotionListener mml = new MouseAdapter() {

    /**
     * 
     * setting the background of the buttons to either red (wrong input) or green (correct input)
     * 
     */
    @Override
    public void mouseDragged(MouseEvent e) {
      if (isProcessing) {
        return;
      } else {
        isProcessing = true;
        Object source = e.getSource();
        if (source instanceof JButton) {
          JButton jbutton = JButton.class.cast(source);
          if (Integer.parseInt(jbutton.getText()) == code[codeIndex]) {
            codeIndex++;
            for (int i = 0; i < AMOUNTOFBUTTONS; i++) { // for all
                                                        // buttons
              buttons[i].setBackground(Color.green);
            }
            if (codeIndex >= code.length) {
              if (instance == 0) {
                // all numbers inputed correctly
                // > close window
                System.exit(0);
              } else {
                instance--;
                dispose();
              }
            }
          } else {
            switching = !switching; // wrong input > switch rotation
            codeIndex = 0;
            for (int i = 0; i < AMOUNTOFBUTTONS; i++) { // for all
                                                        // buttons
              buttons[i].setBackground(Color.red);
            }
            instance++;
            universalTimer = (int) (universalTimer * 0.66);
            new DrehSchlossV2(universalTimer);
          }
        }
      }
    }
  };

}

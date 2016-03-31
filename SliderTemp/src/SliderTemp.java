import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SliderTemp extends JFrame implements ChangeListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JSlider sliderC, sliderF;
	JTextField textC, textF;
	int tempc;
	int tempf;
	boolean c = true;

	public SliderTemp() {
		setTitle("Change Listener");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		sliderC = new JSlider(SwingConstants.HORIZONTAL, 0, 1000, 400);
		sliderC.setMajorTickSpacing(100);
		sliderC.setMinorTickSpacing(50);
		sliderC.setPaintTicks(true);
		sliderC.setPaintLabels(true);
		sliderC.setPreferredSize(new Dimension(300, 50));
		sliderC.addChangeListener(this);
		sliderC.setName("sliderC");
		sliderC.setToolTipText("Schieber A einstellen");
		textC = new JTextField(4);
		textC.setText(sliderC.getValue() + " ");
		textC.setToolTipText("Ergebnis A");
		sliderF = new JSlider(SwingConstants.HORIZONTAL, 0, 1000, 400);
		sliderF.setMajorTickSpacing(100);
		sliderF.setMinorTickSpacing(50);
		sliderF.setPaintTicks(true);
		sliderF.setPaintLabels(true);
		sliderF.setPreferredSize(new Dimension(300, 50));
		sliderF.addChangeListener(this);
		sliderF.setName("sliderF");
		textF = new JTextField(4);
		textF.setText(sliderF.getValue() + " ");
		// content pane
		getContentPane().setLayout(new FlowLayout());
		getContentPane().add(textC);
		getContentPane().add(sliderC);
		getContentPane().add(textF);
		getContentPane().add(sliderF);
		textC.setEditable(false);
		textF.setEditable(false);
	}

	@Override
	public void stateChanged(ChangeEvent evt) {
		JSlider source;
		source = (JSlider) evt.getSource();
		if (source.getName().equals("sliderC")) {
			textC.setText(source.getValue() + " ");

			tempc = source.getValue();
			int c = (int) (tempc * 1.8 + 32);
			textF.setText("" + c);
			if (c <= 995) {
				sliderF.setValue(c);
			} else {
				sliderF.setValue(995);
			}
		}

		if (source.getName().equals("sliderF")) {
			textF.setText(source.getValue() + " ");

			tempf = source.getValue();
			int f = (int) ((tempf - 32) * 5.0 / 9.0);
			textC.setText("" + f);
			sliderC.setValue(f);
		}

	}

	public static void main(String[] args) {
		SliderTemp weightApp = new SliderTemp();
		weightApp.setSize(400, 150);
		weightApp.setVisible(true);
	}
}
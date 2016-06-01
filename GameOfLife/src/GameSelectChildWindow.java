import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JInternalFrame;

class GameSelectChildWindow extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	static int nr = -1, xpos = 30, ypos = 30;
	private MainWindow mydesk;

	public GameSelectChildWindow(MainWindow dft) {
		super("Select Game", false, false);
		mydesk = dft;
		Container cp = getContentPane();// window-Container
		cp.setLayout(new FlowLayout());
		JButton jb = new JButton("Game of Life");
		cp.add(jb);
		jb.addActionListener(listener -> {
			createGameInNewWindow();
		});

		setIconifiable(true);
		setMaximizable(false);
		setClosable(false);
	}

	private void createGameInNewWindow() {
		Game game = new Game(50, 50);
		GoLChildWindow golChildWindow = new GoLChildWindow(mydesk, game);
		mydesk.addChildGoL(golChildWindow, xpos, ypos, 800, 600);
		game.addObserver(golChildWindow);
		GameOfLife.GOLWINDOWNUMBER++;
	}
}
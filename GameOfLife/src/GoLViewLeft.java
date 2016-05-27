import java.util.Observer;

class GoLViewLeft extends GoLChildWindow implements Observer {
	private static final long serialVersionUID = 1L;
	private final int offset;

	public GoLViewLeft(MainWindow dft, Game game, int offset) {
		super(dft, game);
		this.offset = offset + 1;
	}

	@Override
	protected int getLeftOffset() {
		return offset;
	}

}
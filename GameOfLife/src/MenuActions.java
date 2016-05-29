/**
 * Reusable enumeration for all menu actions and its menu description
 */
public enum MenuActions {

	START_STOP("Start/Stop"), EXIT("Exit"), FASTER("Faster"), SLOWER("Slower"), RESET("Reset"), LEFTVIEW(
			"Shift view to left"), RIGHTVIEW("Shift view to right"), VIEWUPSIDEDOWN(
					"Flip View"), BLINKER("Blinker"), GLIDER("Glider"), GLIDERCANNON("Glider cannon");

	private final String description;

	private MenuActions(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}

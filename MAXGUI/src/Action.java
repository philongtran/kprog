/**
 * Visible representation of a players action
 *
 * @author Manuel Wessner <191711>
 * @author Phi Long Tran <191624>
 * @author Steve Nono <191709>
 */

enum Action {

	LEFT("a"), RIGHT("d"), UP("w"), DOWN("s"), RESTART("r"), NEW("n"), QUIT("q"), HELP("h"), UNKNOWN("");

	private final String key;

	/**
	 * Representation of action as string.
	 * 
	 * @param key
	 *            - Representation of action as string
	 */
	private Action(String key) {
		this.key = key;
	}

	/**
	 * Returns the action as string.
	 * 
	 * @return - Returns the direction as string
	 */
	private String getAction() {
		return key;
	}

	/**
	 * Gets the corresponding action value of a string.
	 * 
	 * @param action
	 *            - w,a,s,d
	 * @return - Action as Enum
	 */
	static Action of(String action) {
		for (Action directionEnum : Action.values()) {
			if (directionEnum.getAction().equals(action)) {
				return directionEnum;
			}
		}
		return UNKNOWN;
	}
}

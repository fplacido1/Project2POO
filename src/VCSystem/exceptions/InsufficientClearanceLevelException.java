package Exceptions;

@SuppressWarnings("serial")
public class InsufficientClearanceLevelException extends Exception {

	private static final String MESSAGE = "%s: insufficient clearance level.";
	
	public InsufficientClearanceLevelException(String param) {
		super(String.format(MESSAGE, param));
	}
	
}

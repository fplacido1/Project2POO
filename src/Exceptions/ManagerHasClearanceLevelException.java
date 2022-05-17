package Exceptions;

@SuppressWarnings("serial")
public class ManagerHasClearanceLevelException extends Exception{

	private static final String MESSAGE = "Project manager %s has clearance level %d.";
	
	public ManagerHasClearanceLevelException(String param) {
		super(String.format(MESSAGE, param));
	}
}

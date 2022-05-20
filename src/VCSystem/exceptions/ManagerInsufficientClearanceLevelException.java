package VCSystem.exceptions;

@SuppressWarnings("serial")
public class ManagerInsufficientClearanceLevelException extends Exception{

	private static final String MESSAGE = "Project manager %s has clearance level %d.";
	
	public ManagerInsufficientClearanceLevelException(String param, int param2) {
		super(String.format(MESSAGE, param, param2));
	}
}

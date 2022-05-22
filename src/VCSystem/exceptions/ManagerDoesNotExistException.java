package VCSystem.exceptions;

@SuppressWarnings("serial")
public class ManagerDoesNotExistException extends Exception {

	private static final String MESSAGE = "Project manager %s does not exist.";
	
	public ManagerDoesNotExistException(String param) {
		super(String.format(MESSAGE, param));
	}
}

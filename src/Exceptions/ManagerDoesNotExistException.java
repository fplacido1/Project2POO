package Exceptions;

@SuppressWarnings("serial")
public class ManagerDoesNotExistException extends Exception {

	private static final String MESSAGE = "Project manager %s does not exists.\n";
	
	public ManagerDoesNotExistException(String param) {
		super(String.format(MESSAGE, param));
	}
}

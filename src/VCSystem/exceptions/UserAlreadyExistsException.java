package VCSystem.exceptions;

@SuppressWarnings("serial")
public class UserAlreadyExistsException extends Exception{
	
	private static final String MESSAGE = "User %s already exists.\n";

	public UserAlreadyExistsException(String param) {
		super(String.format(MESSAGE, param));
	}
}

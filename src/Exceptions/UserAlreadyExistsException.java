package Exceptions;

@SuppressWarnings("serial")
public class UserAlreadyExistsException extends Exception{
	
	private static final String MESSAGE = "User %s already exists.\n";

	public UserAlreadyExistsException() {
		super(MESSAGE);
	}
}

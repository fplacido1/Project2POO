package Exceptions;

@SuppressWarnings("serial")
public class UserDoesNotExistException extends Exception{
	
	private static final String MESSAGE = "User %s does not exist.";
	
	public UserDoesNotExistException() {
		super(MESSAGE);
	}
	

}

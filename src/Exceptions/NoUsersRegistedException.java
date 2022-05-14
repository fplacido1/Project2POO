package Exceptions;

public class NoUsersRegistedException extends Exception{
	
	private static final String MESSAGE = "No user is registered.";
	
	public  NoUsersRegistedException() {
		super(MESSAGE);
	}

}

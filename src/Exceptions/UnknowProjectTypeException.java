package Exceptions;

public class UnknowProjectTypeException extends Exception{
	
	private static final String MESSAGE = "No user is registered.";
	
	public UnknowProjectTypeException() {
		super(MESSAGE);
	}

}

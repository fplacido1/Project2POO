package Exceptions;

public class UnknowJobPositionException extends Exception{
	
	private static final String MESSAGE = "Unknown job position.";
	
	public UnknowJobPositionException() {
		super(MESSAGE);
	}

}

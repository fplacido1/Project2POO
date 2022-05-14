package Exceptions;

public class ProjectNameDoesNotExistsException extends Exception{

	private static final String MESSAGE = "%s project does not exist.";
	
	public ProjectNameDoesNotExistsException() {
		super(MESSAGE);
	}
}

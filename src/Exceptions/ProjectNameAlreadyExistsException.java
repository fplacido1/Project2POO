package Exceptions;

public class ProjectNameAlreadyExistsException extends Exception{
	
	private static final String MESSAGE = "%s project already exists.";
	
	public ProjectNameAlreadyExistsException() {
		super(MESSAGE);
	}

}

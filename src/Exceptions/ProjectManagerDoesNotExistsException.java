package Exceptions;

public class ProjectManagerDoesNotExistsException extends Exception{

	private static final String MESSAGE = "Project manager %s does not exist.";
	
	public ProjectManagerDoesNotExistsException() {
		super(MESSAGE);
	}
}

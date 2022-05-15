package Exceptions;

@SuppressWarnings("serial")
public class ProjectManagerDoesNotExistsException extends Exception{

	private static final String MESSAGE = "Project manager %s does not exist.\n";
	
	public ProjectManagerDoesNotExistsException() {
		super(MESSAGE);
	}
}

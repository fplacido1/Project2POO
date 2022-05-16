package Exceptions;

@SuppressWarnings("serial")
public class ProjectNameDoesNotExistsException extends Exception{

	private static final String MESSAGE = "%s project does not exist.";
	
	public ProjectNameDoesNotExistsException(String param) {
		super(String.format(MESSAGE, param));
	}
}

package Exceptions;

@SuppressWarnings("serial")
public class ProjectNotManagedByUserException extends Exception{

	private static final String MESSAGE = "%s is managed by %s.";
	
	public ProjectNotManagedByUserException(String param) {
		super(String.format(MESSAGE, param));
	}
	
	

}

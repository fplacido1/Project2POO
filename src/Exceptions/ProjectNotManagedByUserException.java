package Exceptions;

@SuppressWarnings("serial")
public class ProjectNotManagedByUserException extends Exception{

	private static final String MESSAGE = "%s is managed by %s.";
	
	public ProjectNotManagedByUserException(String param, String param2) {
		super(String.format(MESSAGE, param, param2));
	}
	
	

}

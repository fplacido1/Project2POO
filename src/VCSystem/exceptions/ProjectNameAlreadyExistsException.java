package VCSystem.exceptions;

@SuppressWarnings("serial")
public class ProjectNameAlreadyExistsException extends Exception{
	
	private static final String MESSAGE = "%s project already exists.";
	
	public ProjectNameAlreadyExistsException(String param) {
		super(String.format(MESSAGE, param));
	}

}

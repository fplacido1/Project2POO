package VCSystem.exceptions;

@SuppressWarnings("serial")
public class NoCommonProjectsException extends Exception{
	
	private static final String MESSAGE = "Cannot determine employees with common projects.";
	
	public NoCommonProjectsException() {
		super(MESSAGE);
	}

}

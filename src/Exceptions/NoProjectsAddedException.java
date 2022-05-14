package Exceptions;

public class NoProjectsAddedException extends Exception{
	
	private static final String MESSAGE = "No projects added.";
	
	public NoProjectsAddedException() {
		super(MESSAGE);
	}

}

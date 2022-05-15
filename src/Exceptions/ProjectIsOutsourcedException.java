package Exceptions;

public class ProjectIsOutsourcedException extends Exception{
	
	private static final String MESSAGE = "%s is an outsourced project.";
	
	public ProjectIsOutsourcedException() {
		super(MESSAGE);
	}

}

package Exceptions;

@SuppressWarnings("serial")
public class UserCannotAcessArtefectException extends Exception{
	
	private static final String MESSAGE = "User %s cannot access artefact.";
	
	public UserCannotAcessArtefectException() {
		super(MESSAGE);
	}

}

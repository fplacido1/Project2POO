package VCSystem.exceptions;

@SuppressWarnings("serial")
public class UserCannotAcessArtefectException extends Exception{
	
	private static final String MESSAGE = "User %s cannot access artefact.";
	
	public UserCannotAcessArtefectException(String param) {
		super(String.format(MESSAGE, param));
	}

}

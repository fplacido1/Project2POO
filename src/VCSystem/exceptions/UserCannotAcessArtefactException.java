package VCSystem.exceptions;

@SuppressWarnings("serial")
public class UserCannotAcessArtefactException extends Exception{
	
	private static final String MESSAGE = "User %s cannot access artefact.";
	
	public UserCannotAcessArtefactException(String param) {
		super(String.format(MESSAGE, param));
	}

}

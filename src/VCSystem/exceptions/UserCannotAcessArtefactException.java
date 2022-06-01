package VCSystem.exceptions;

/**
 * 
 * @author Joao Norberto (62685) & Francisco Placido (62674)
 * 
 * Date of last update: 1 of june of 2022
 */
@SuppressWarnings("serial")
public class UserCannotAcessArtefactException extends Exception{
	
	private static final String MESSAGE = "User %s cannot access artefact.";
	
	public UserCannotAcessArtefactException(String param) {
		super(String.format(MESSAGE, param));
	}

}

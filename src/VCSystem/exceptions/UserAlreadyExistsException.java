package VCSystem.exceptions;

/**
 * 
 * @author Joao Norberto (62685) & Francisco Placido (62674)
 * 
 * Date of last update: 1 of june of 2022
 */
@SuppressWarnings("serial")
public class UserAlreadyExistsException extends Exception{
	
	private static final String MESSAGE = "User %s already exists.";

	public UserAlreadyExistsException(String param) {
		super(String.format(MESSAGE, param));
	}
}

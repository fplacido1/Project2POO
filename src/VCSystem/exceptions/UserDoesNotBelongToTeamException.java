package VCSystem.exceptions;

/**
 * 
 * @author Joao Norberto (62685) & Francisco Placido (62674)
 * 
 * Date of last update: 1 of june of 2022
 */
@SuppressWarnings("serial")
public class UserDoesNotBelongToTeamException extends Exception{
	
	private static final String MESSAGE = "User %s does not belong to the team of %s.";
	
	public UserDoesNotBelongToTeamException(String param, String param2) {
		super(String.format(MESSAGE, param, param2));
	}

}

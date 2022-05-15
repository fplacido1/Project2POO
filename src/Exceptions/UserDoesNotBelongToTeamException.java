package Exceptions;

@SuppressWarnings("serial")
public class UserDoesNotBelongToTeamException extends Exception{
	
	private static final String MESSAGE = "User %s does not belong to the team of %s.";
	
	public UserDoesNotBelongToTeamException() {
		super(MESSAGE);
	}

}

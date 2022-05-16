package Exceptions;

@SuppressWarnings("serial")
public class AlreadyTeamMemberException extends Exception {

	private static final String MESSAGE = "%s: already a member.\n";
	
	public AlreadyTeamMemberException() {
		super(MESSAGE);
	}
}

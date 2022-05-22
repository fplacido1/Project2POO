package VCSystem.exceptions;

@SuppressWarnings("serial")
public class AlreadyTeamMemberException extends Exception {

	private static final String MESSAGE = "%s: already a member.";
	
	public AlreadyTeamMemberException(String param) {
		super(String.format(MESSAGE, param));
	}
}

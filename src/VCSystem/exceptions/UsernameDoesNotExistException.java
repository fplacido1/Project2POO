package VCSystem.exceptions;

@SuppressWarnings("serial")
public class UsernameDoesNotExistException extends Exception{
	
	private static final String MESSAGE = "%s: does not exist.";
	
	public UsernameDoesNotExistException(String param) {
		super(String.format(MESSAGE, param));
	}
	

}

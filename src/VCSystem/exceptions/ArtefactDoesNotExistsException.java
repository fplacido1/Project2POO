package VCSystem.exceptions;

@SuppressWarnings("serial")
public class ArtefactDoesNotExistsException extends Exception {
	
	private static final String MESSAGE = "%s does not exist in the project.";
	
	public ArtefactDoesNotExistsException(String param) {
		super(String.format(MESSAGE, param));
	}

}

package Exceptions;

@SuppressWarnings("serial")
public class ArtefactAlreadyInProjectException extends Exception{
	
	private static final String MESSAGE = "%s: already in the project.\n";
	
	public ArtefactAlreadyInProjectException(String param) {
		super(String.format(MESSAGE, param));
	}

}

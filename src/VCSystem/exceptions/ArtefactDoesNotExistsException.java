package VCSystem.exceptions;

@SuppressWarnings("serial")
public class ArtefactDoesNotExistsException extends Exception {
	
	private static final String MESSAGE = "Artefact does not exist in the project.";
	
	public ArtefactDoesNotExistsException() {
		super(MESSAGE);
	}

}

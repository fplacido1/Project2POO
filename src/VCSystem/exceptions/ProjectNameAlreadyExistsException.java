package VCSystem.exceptions;

/**
 * 
 * @author Joao Norberto (62685) & Francisco Placido (62674)
 * 
 * Date of last update: 1 of june of 2022
 */
@SuppressWarnings("serial")
public class ProjectNameAlreadyExistsException extends Exception{
	
	private static final String MESSAGE = "%s project already exists.";
	
	public ProjectNameAlreadyExistsException(String param) {
		super(String.format(MESSAGE, param));
	}

}

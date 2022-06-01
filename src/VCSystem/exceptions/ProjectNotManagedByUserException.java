package VCSystem.exceptions;

/**
 * 
 * @author Joao Norberto (62685) & Francisco Placido (62674)
 * 
 * Date of last update: 1 of june of 2022
 */
@SuppressWarnings("serial")
public class ProjectNotManagedByUserException extends Exception{

	private static final String MESSAGE = "%s is managed by %s.";
	
	public ProjectNotManagedByUserException(String param, String param2) {
		super(String.format(MESSAGE, param, param2));
	}
	
	

}

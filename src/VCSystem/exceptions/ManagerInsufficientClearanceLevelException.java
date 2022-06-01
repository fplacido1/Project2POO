package VCSystem.exceptions;

/**
 * 
 * @author Joao Norberto (62685) & Francisco Placido (62674)
 * 
 * Date of last update: 1 of june of 2022
 */
@SuppressWarnings("serial")
public class ManagerInsufficientClearanceLevelException extends Exception{

	private static final String MESSAGE = "Project manager %s has clearance level %d.";
	
	public ManagerInsufficientClearanceLevelException(String param, int param2) {
		super(String.format(MESSAGE, param, param2));
	}
}

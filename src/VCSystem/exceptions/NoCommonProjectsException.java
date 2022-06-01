package VCSystem.exceptions;

/**
 * 
 * @author Joao Norberto (62685) & Francisco Placido (62674)
 * 
 * Date of last update: 1 of june of 2022
 */
@SuppressWarnings("serial")
public class NoCommonProjectsException extends Exception{
	
	private static final String MESSAGE = "Cannot determine employees with common projects.";
	
	public NoCommonProjectsException() {
		super(MESSAGE);
	}

}

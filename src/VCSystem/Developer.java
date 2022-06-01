package VCSystem;

/**
 * 
 * @author Joao Norberto (62685) & Francisco Placido (62674)
 * 
 * Date of last update: 1 of june of 2022
 */
public interface Developer extends User {
	
	/**
	 * This method returns the name of
	 * his manager
	 * @return name of the manager
	 */
	Manager getManager();

}

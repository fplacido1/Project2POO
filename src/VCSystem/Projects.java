package VCSystem;

import java.util.Iterator;

/**
 * 
 * @author Joao Norberto (62685) & Francisco Placido (62674)
 * 
 * Date of last update: 1 of june of 2022
 */
public interface Projects {
	
	/**
	 * This method returns the name
	 * of the project
	 * @return name of the project
	 */
	String getProjName();
	
	/**
	 * This method returns the manager
	 * of the project
	 * @return manager of the project
	 */
	Manager getManager();
	
	/**
	 * This method returns all the keywords
	 * of the project
	 * @return iterator of all the keywords
	 */
	Iterator<String> getKeyWords();
}

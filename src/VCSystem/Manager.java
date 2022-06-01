package VCSystem;

import java.util.Iterator;

/**
 * 
 * @author Joao Norberto (62685) & Francisco Placido (62674)
 * 
 * Date of last update: 1 of june of 2022
 */
public interface Manager extends User {

	/**
	 * This method returns the number
	 * of developers managed by the manager
	 * @return number of developers managed
	 * by the manager
	 */
	int getNumManagedDevs();
	
	/**
	 * This methods returns the number of
	 * managed projects by the manager
	 * @return
	 */
	int getNumManagedProjs();

	/**
	 * This method adds a developer to
	 * the <code>managedUsers</code> TreeMap
	 * @param d , object developer to add
	 */
	// pre: d != null
	void addManagedDev(Developer d);

	/**
	 * This method adds a project to the
	 * <code>managedProjs</code> HashMap
	 * @param proj , project to add
	 */
	// pre: proj != null
	void addToManagedProjs(Projects proj);

	/**
	 * This method returns all the developers
	 * managed by the manager
	 * @return iterator of all the developers
	 */
	Iterator<User> getAllUsers();

	/**
	 * This method returns the number of common
	 * projects between the <code>managedProjs</code>
	 * and user projects from <code>u2</code>
	 * @param u2 , user to get all projects
	 * @return integer of managed projects in common
	 */
	// pre: u2 != null
	int getManagedProjsCommon(User u2);

}

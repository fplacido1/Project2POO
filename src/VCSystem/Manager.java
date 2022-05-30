package VCSystem;

import java.util.Iterator;

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
	void addManagedDev(Developer d);

	/**
	 * This method adds a project to the
	 * <code>managedProjs</code> HashMap
	 * @param proj , project to add
	 */
	void addToManagedProjs(Projects proj);

	/**
	 * This method returns all the developers
	 * managed by the manager
	 * @return iterator of all the developers
	 */
	Iterator<User> getAllUsers();

	int getManagedProjsCommon(User u2);

}

package VCSystem;

import java.time.LocalDate;
import java.util.Iterator;

/**
 * 
 * @author João Norberto (62685) & Francisco Plácido (62674)
 * 
 * Date of last update: 30 of may of 2022
 */
public interface User extends Comparable<User> {

	/**
	 * This method returns the name
	 * of the user
	 * @return name of the user
	 */
	String getName();

	/**
	 * This method returns the
	 * clearance the level of
	 * the user
	 * @return clearance level
	 * of the user
	 */
	int getClearanceLvl();
	
	/**
	 * This method returns the number
	 * of projects a user is in, as
	 * manager and/or developer
	 * @return number of projects
	 * the user is participating
	 */
	int getNumProjs();

	/**
	 * This method adds a project
	 * to the user projects
	 * @param p , project to add
	 */
	void addProj(InHouse p);

	/**
	 * This method adds a revision
	 * done by the user to it's revisions
	 * @param r , revision to add
	 */
	void addArtefactRevised(Revision r);

	/**
	 * This method returns all the
	 * revisions done by the user
	 * @return iterator of all the
	 * revisions
	 */
	Iterator<Revision> getAllRevisions();
	
	/**
	 * This method returns the number
	 * of updates the user has done
	 * @return number of updates done
	 * by the user
	 */
	int getNumUpdates();
	
	/**
	 * This method returns the date
	 * of the last update done by
	 * the user
	 * @return date of the last update
	 */
	LocalDate getLastUpdateDone();

	/**
	 * This method gets all the projects
	 * between two users
	 * @param u2 , user to check
	 * @return number of projects
	 * in common between the users
	 */
	int getProjsInCommon(User u2);

	/**
	 * This method returns all the projects
	 * that the user participates in, as
	 * a developer
	 * @return iterator of projects as a
	 * developer
	 */
	Iterator<InHouse> getAllProjs();

}

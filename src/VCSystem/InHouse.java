package VCSystem;
import java.time.*;
import java.util.Iterator;

import VCSystem.exceptions.*;

/**
 * 
 * @author João Norberto (62685) & Francisco Plácido (62674)
 * 
 * Date of last update: 30 of may of 2022
 */
public interface InHouse extends Projects {
	
	/**
	 * This method returns the confidentiality
	 * level of the project
	 * @return
	 */
	int getConfLvl();
	
	/**
	 * This method adds an artefact to the project
	 * @param e , artefact to add
	 * @throws ArtefactAlreadyInProjectException
	 * if the artefact is already in the project
	 * @throws ExceedsProjectConfidentialityLevelException
	 * if the artefact confidentiality level is superior than the projects level
	 */
	void addArtefact(Artefacts e) throws ArtefactAlreadyInProjectException, ExceedsProjectConfidentialityLevelException;
		
	/**
	 * This method returns the number of artefacts
	 * that the project has
	 * @return number of artefacts the project has
	 */
	int getNumArtefacts();
	
	/**
	 * This method returns the number of revisions
	 * that the artefacts from the project have
	 * @return number of revisions the artefacts
	 * have
	 */
	int getNumRevisions();

	/**
	 * This method returns the last revision
	 * date done to an artefact from the project
	 * @return date of the last revision done to
	 * an artefact
	 */
	LocalDate getLastRevisionDate();
	
	/**
	 * This method returns the number of developers
	 * the project has
	 * @return number of developers the project has
	 */
	int getNumDevs();

	/**
	 * This method adds a user to the project
	 * @param u , user to add
	 * @throws AlreadyTeamMemberException
	 * if the user is already in the project team
	 * @throws InsufficientClearanceLevelException
	 * if the user has a clearance level smaller than
	 * the project's clearance level
	 */
	void addUser(User u) throws AlreadyTeamMemberException, InsufficientClearanceLevelException;

	/**
	 * This method returns all the users the
	 * project has
	 * @return iterator of all the users
	 */
	Iterator<User> getAllUsers();

	/**
	 * This method returns all the artefact the
	 * project has
	 * @return iterator of all the artefacts
	 */
	Iterator<Artefacts> getAllArtefacts();

	/**
	 * This method revises an artefact from the
	 * project
	 * @param u , author of the revision
	 * @param artefactName , name of the artefact
	 * @param revisionDate , date of the revision
	 * @param comment , comment of the revision
	 * @return the revision that was done
	 */
	Revision reviseArtefact(User u, String artefactName, LocalDate revisionDate, String comment);

	/**
	 * This method checks if a user is in
	 * the project or not
	 * @param user , name of the user to
	 * check
	 * @return boolean value, that is
	 * <code>True</code> when the project
	 * contains the user
	 */
	boolean containsUser(String user);
	
	/**
	 * This method checks if a artefact is in
	 * the project or not
	 * @param artefactName , name of the
	 * artefact to check
	 * @return boolean value, that is
	 * <code>True</code> when the project
	 * contains the artefact
	 */
	boolean containsArtefact(String artefactName);

}

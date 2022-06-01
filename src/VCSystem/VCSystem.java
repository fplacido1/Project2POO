package VCSystem;
import java.time.LocalDate;
import java.util.*;
import VCSystem.exceptions.*;

/**
 * 
 * @author Joao Norberto (62685) & Francisco Placido (62674)
 * 
 * Date of last update: 30 of may of 2022
 */
public interface VCSystem {

	/**
	 * This method adds a manager to the
	 * system
	 * @param name , name of the manager
	 * @param clearanceLvl , clearance level
	 * of the manager
	 * @throws UserAlreadyExistsException 
	 * if the user is already registered
	 */
	// pre: name != null && clearanceLvl != null
	void addManager(String name, int clearanceLvl)
			throws UserAlreadyExistsException;
	
	/**
	 * This method adds a developer to the
	 * system
	 * @param name , name of the developer
	 * @param mng , name of the manager, in
	 * charge of this developer
	 * @param clearanceLvl , clearance level
	 * of the developer
	 * @throws UserAlreadyExistsException
	 * if the user is already registered
	 * @throws ManagerDoesNotExistException
	 * if the manager is not registered in the system
	 */
	// pre: name != null && mng != null && clearanceLvl != null
	void addDeveloper(String name, String mng, int clearanceLvl)
			throws UserAlreadyExistsException, ManagerDoesNotExistException;
	
	/**
	 * This method returns all the
	 * users registered in the system
	 * @return iterator of all the
	 * users in the system
	 */
	Iterator<User> getAllUsers();
	
	/**
	 * This method returns all the
	 * projects registered in the system
	 * @return iterator of all the
	 * projects in the system
	 */
	Iterator<Projects> getAllProjects();

	/**
	 * This method returns the number of
	 * users registered in the system
	 * @return number of users registered
	 */
	int numUsers();

	/**
	 * This method adds a existing user to
	 * a project team
	 * @param projectName , name of the project
	 * @param userName , name of the user to add
	 * @throws UsernameDoesNotExistException
	 * if the user is not registered
	 * @throws AlreadyTeamMemberException
	 * if the user is already in the project team
	 * @throws InsufficientClearanceLevelException
	 * if the user has a clearance level smaller than
	 * the project's clearance level
	 */
	// pre: projectName != null && userName != null
	void addUserToProj(String projectName, String userName)
			throws UsernameDoesNotExistException, AlreadyTeamMemberException, InsufficientClearanceLevelException;

	/**
	 * This method checks if the type
	 * wrote by the user is valid or not
	 * @param type , type of the project
	 * @throws UnknownProjectTypeException
	 * if the project type is not valid
	 */
	// pre: type != null
	void checkProjType(String type)
			throws UnknownProjectTypeException;

	/**
	 * This method creates a new in-house project
	 * @param projMng , name of the project
	 * manager
	 * @param projName , name of the project
	 * @param keyWords , key words related
	 * to the project
	 * @param confLvl , confidentiality level of
	 * the project
	 * @throws ManagerDoesNotExistException
	 * if the manager is not registered
	 * @throws ManagerInsufficientClearanceLevelException
	 * if the manager has a clearance level smaller than
	 * the project's clearance level
	 * @throws ProjectNameAlreadyExistsException
	 * if the project is already registered
	 */
	// pre: projMng != null && projName != null && keyWords != null && keyWords.size() != 0 && confLvl != 0
	void createNewInHouseProj(String projMng, String projName, List<String> keyWords, int confLvl)
			throws ManagerDoesNotExistException, ManagerInsufficientClearanceLevelException, ProjectNameAlreadyExistsException;

	/**
	 * This method creates a new outsourced project
	 * @param projMng , name of the project
	 * manager
	 * @param projName , name of the project
	 * @param keyWords , key words related
	 * to the project
	 * @param companyName , name of the company
	 * responsible for the project
	 * @throws ManagerDoesNotExistException
	 * if the manager is not registered
	 * @throws ProjectNameAlreadyExistsException
	 * if the project is already registered
	 */
	// pre: projMng != null && projName != null && keyWords != null && keyWords.size() != 0 && companyName != 0
	void createNewOutSourcedProj(String projMng, String projName, List<String> keyWords, String companyName)
			throws ManagerDoesNotExistException, ProjectNameAlreadyExistsException;

	/**
	 * This method checks if the manager name
	 * and the project name are valid
	 * @param mngName , manager name
	 * @param projectName , project name
	 * @throws ManagerDoesNotExistException
	 * if the manager is not registered
	 * @throws ProjectNameDoesNotExistsException
	 * if the project is not registered
	 * @throws ProjectNotManagedByUserException
	 * if the project is not managed by the given manager
	 */
	// pre: mngName != null && projectName != null
	void checkProjAndMng(String mngName, String projectName)
			throws ManagerDoesNotExistException, ProjectNameDoesNotExistsException, ProjectNotManagedByUserException;

	/**
	 * This method checks if a job position
	 * is valid or not
	 * @param jobPosition , name of the job
	 * position
	 * @throws UnknowJobPositionException
	 * if the position does not exist
	 */
	// pre: jobPosition != null
	void checkJobPos(String jobPosition)
			throws UnknowJobPositionException;
	
	/**
	 * This method checks if the user
	 * and project are valid or not
	 * @param user , name of the user
	 * @param project , name of the project
	 * @throws UserDoesNotExistException
	 * if the user is not registered
	 * @throws ProjectNameDoesNotExistsException
	 * if the project is not registered
	 * @throws UserDoesNotBelongToTeamException
	 * if the user does not belong to the team
	 */
	// pre: user != null && project != null
	void checkUserAndProj(String user, String project)
			throws UserDoesNotExistException, ProjectNameDoesNotExistsException, UserDoesNotBelongToTeamException;
	
	/**
	 * This method adds an artefact to
	 * a project
	 * @param artefactName , name of the artefact
	 * @param confidentialityLevel , confidentiality
	 * level of the artefact
	 * @param description , description of the artefact
	 * @param date , date of the artefact
	 * @param projectName , name of the project
	 * @param userName , name of the user that
	 * created the artefact
	 * @throws ArtefactAlreadyInProjectException
	 * if the artefact is already in the project
	 * @throws ExceedsProjectConfidentialityLevelException
	 * if the artefact confidentiality level is superior than the projects level
	 * @throws UserDoesNotBelongToTeamException
	 * if the user does not belong to the team
	 */
	// pre: artefactName != null && confidentialityLevel != null && description != null &&
	// 		date != null && projectName != null && userName != null
	void addArtefact(String artefactName, int confidentialityLevel, String description, LocalDate date,
			         String projectName, String userName)
			throws ArtefactAlreadyInProjectException, ExceedsProjectConfidentialityLevelException, UserDoesNotBelongToTeamException;
	
	/**
	 * This method returns all the InHouse projects
	 * with the keyword <code>keyWord</code>
	 * @param keyWord , a keyword given by
	 * the user
	 * @return an iterator of all the InHouse
	 * projects
	 */
	// pre: keyWords != null
	Iterator<InHouse> getInHouseByKeyword(String keyWord);
	
	/**
	 * This method returns all the OutSourced projects
	 * with the keyword <code>keyWord</code>
	 * @param keyWord , a keyword given by
	 * the user
	 * @return an iterator of all the InHouse
	 * projects
	 */
	// pre: keyWords != null
	Iterator<OutSourced> getOutSourcedByKeyword(String keyWord);

	/**
	 * This method returns all the users of
	 * a InHouse project
	 * @param proj , InHouse project to get
	 * all the users from
	 * @return iterator of all the users
	 * from the project
	 */
	// pre: proj != null
	Iterator<User> getAllProjUsers(InHouse proj);

	/**
	 * This method returns all the artefacts of
	 * a InHouse project
	 * @param proj , InHouse project to get
	 * all the users from
	 * @return iterator of all the users
	 * from the project
	 */
	// pre: proj != null 
	Iterator<Artefacts> getAllProjArtefacts(InHouse proj);

	/**
	 * This method returns a InHouse
	 * project
	 * @param projName , name of the
	 * project
	 * @return corresponding object
	 * @throws ProjectNameDoesNotExistsException
	 * if the project is not registered
	 * @throws ProjectIsOutsourcedException
	 * if the project is OutSourced
	 */
	// pre: projName != null
	InHouse getInHouseProj(String projName)
			throws ProjectNameDoesNotExistsException, ProjectIsOutsourcedException;

	/**
	 * This method returns a revision,
	 * done to an artefact
	 * @param userName , name of the user
	 * that revised the artefact
	 * @param projectName , name of the
	 * project
	 * @param artefactName , name of the
	 * artefact
	 * @param revisionDate , date of the
	 * revision
	 * @param comment , comment of the
	 * revision
	 * @return Object revision representing
	 * the revision that was done
	 * @throws UserDoesNotExistException
	 * if the user is not registered
	 * @throws ProjectNameDoesNotExistsException
	 * if the project is not registered
	 * @throws ArtefactDoesNotExistsException
	 * if the artefact does not exist
	 * @throws UserDoesNotBelongToTeamException
	 * if the user does not belong to the team
	 */
	// pre: userName != null && projectName != null && artefactName != null && revisionDate != null && comment != null
	Revision reviseArtefact(String userName, String projectName, String artefactName, LocalDate revisionDate, String comment)
			throws UserDoesNotExistException, ProjectNameDoesNotExistsException, ArtefactDoesNotExistsException, 
			       UserDoesNotBelongToTeamException;

	/**
	 * This method returns gives detailed
	 * information about the developers
	 * managed by a given manager
	 * @param managerName , name of the
	 * manager
	 * @return iterator of all the users
	 * managed by the given manager
	 * @throws ManagerDoesNotExistException
	 * if the manager is not registered in the system
	 */
	// pre: managerName != null
	Iterator<User> getAllManagerUsers(String managerName)
			throws ManagerDoesNotExistException;

	/**
	 * This method gets all the projects by
	 * confidentiality level within two given limits
	 * @param lowerLimit , minimum limit
	 * @param upperLimit , maximum limit
	 * @return iterator of all the projects between
	 * the two limits
	 */
	// pre: lowerLimit != null && upperLimit != null
	Iterator<InHouse> getProjsWithIn(int lowerLimit, int upperLimit);

	/**
	 * This method returns the three
	 * users that have more artefact
	 * updates
	 * @return an iterator of the three
	 * workaholic users
	 */
	Iterator<User> getWorkaholics();

	/**
	 * This method determines the two
	 * employees that have more projects
	 * in common
	 * @return object Common with the
	 * two employees
	 */
	Common getCommonUsers();
	
}

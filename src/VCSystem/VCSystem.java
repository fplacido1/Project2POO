package VCSystem;
import java.time.LocalDate;
import java.util.*;
import VCSystem.exceptions.*;


public interface VCSystem {

	/**
	 * This method adds a manager to the
	 * system
	 * @param name, name of the manager
	 * @param clearanceLvl, clearance level
	 * of the manager
	 * @throws UserAlreadyExistsException 
	 * if the user is already registered
	 */
	void addManager(String name, int clearanceLvl)
			throws UserAlreadyExistsException;
	
	/**
	 * This method adds a developer to the
	 * system
	 * @param name, name of the developer
	 * @param mng, name of the manager, in
	 * charge of this developer
	 * @param clearanceLvl, clearance level
	 * of the developer
	 * @throws UserAlreadyExistsException
	 * if the user is already registered
	 * @throws ManagerDoesNotExistException
	 * if the manager is not registered in the system
	 */
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
	 * @param projectName, name of the project
	 * @param userName, name of the user to add
	 * @throws UsernameDoesNotExistException
	 * if the user is not registered
	 * @throws AlreadyTeamMemberException
	 * if the user is already in the project team
	 * @throws InsufficientClearanceLevelException
	 * if the user has a clearance level smaller than
	 * the project's clearance level
	 */
	void addUserToProj(String projectName, String userName)
			throws UsernameDoesNotExistException, AlreadyTeamMemberException, InsufficientClearanceLevelException;

	/**
	 * This method checks if the type
	 * wrote by the user is valid or not
	 * @param type, type of the project
	 * @throws UnknownProjectTypeException
	 * if the project type is not valid
	 */
	void checkProjType(String type)
			throws UnknownProjectTypeException;

	/**
	 * This method creates a new in-house project
	 * @param projMng, name of the project
	 * manager
	 * @param projName, name of the project
	 * @param keyWords, key words related
	 * to the project
	 * @param confLvl, confidentiality level of
	 * the project
	 * @throws ManagerDoesNotExistException
	 * if the manager is not registered
	 * @throws ManagerInsufficientClearanceLevelException
	 * if the manager has a clearance level smaller than
	 * the project's clearance level
	 * @throws ProjectNameAlreadyExistsException
	 * if the project is already registered
	 */
	void createNewInHouseProj(String projMng, String projName, List<String> keyWords, int confLvl)
			throws ManagerDoesNotExistException, ManagerInsufficientClearanceLevelException, ProjectNameAlreadyExistsException;

	/**
	 * This method creates a new outsourced project
	 * @param projMng, name of the project
	 * manager
	 * @param projName, name of the project
	 * @param keyWords, key words related
	 * to the project
	 * @param companyName, name of the company
	 * responsible for the project
	 * @throws ManagerDoesNotExistException
	 * if the manager is not registered
	 * @throws ProjectNameAlreadyExistsException
	 * if the project is already registered
	 */
	void createNewOutSourcedProj(String projMng, String projName, List<String> keyWords, String companyName)
			throws ManagerDoesNotExistException, ProjectNameAlreadyExistsException;

	/**
	 * This method checks if the manager name
	 * and the project name are valid
	 * @param mngName, manager name
	 * @param projectName, project name
	 * @throws ManagerDoesNotExistException
	 * if the manager is not registered
	 * @throws ProjectNameDoesNotExistsException
	 * if the project is not registered
	 * @throws ProjectNotManagedByUserException
	 * if the project is not managed by the given manager
	 */
	void checkProjAndMng(String mngName, String projectName)
			throws ManagerDoesNotExistException, ProjectNameDoesNotExistsException, ProjectNotManagedByUserException;

	/**
	 * This method checks if a job position
	 * is valid or not
	 * @param jobPosition, name of the job
	 * position
	 * @throws UnknowJobPositionException
	 * if the position does not exist
	 */
	void checkJobPos(String jobPosition)
			throws UnknowJobPositionException;
	
	/**
	 * This method checks if the user
	 * and project are valid or not
	 * @param user, name of the user
	 * @param project, name of the project
	 * @throws UserDoesNotExistException
	 * 
	 * @throws ProjectNameDoesNotExistsException
	 * @throws UserDoesNotBelongToTeamException
	 */
	void checkUserAndProj(String user, String project)
			throws UserDoesNotExistException, ProjectNameDoesNotExistsException, UserDoesNotBelongToTeamException;
	
	void addArtefact(Artefacts e, String projectName)
			throws ArtefactAlreadyInProjectException, ExceedsProjectConfidentialityLevelException, UserDoesNotBelongToTeamException;

	Iterator<Projects> getProjsByKeyword(String keyWord);

	Iterator<User> getAllProjUsers(InHouse proj);

	Iterator<Artefacts> getAllProjArtefacts(InHouse proj);

	InHouse getInHouseProj(String projName)
			throws ProjectNameDoesNotExistsException, ProjectIsOutsourcedException;

	Revision reviseArtefact(String userName, String projectName, String artefactName, LocalDate revisionDate, String comment)
			throws UserDoesNotExistException, ProjectNameDoesNotExistsException, ArtefactDoesNotExistsException, 
			       UserDoesNotBelongToTeamException;

	Iterator<User> getAllManagerUsers(String managerName)
			throws ManagerDoesNotExistException;

	Iterator<InHouse> getProjsWithIn(int lowerLimit, int upperLimit);

	Iterator<User> getWorkaholics();

	Iterator<Common> getCommonUsers();
	
}

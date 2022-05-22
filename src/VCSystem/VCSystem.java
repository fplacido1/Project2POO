package VCSystem;
import java.time.LocalDate;
import java.util.*;
import VCSystem.exceptions.*;


public interface VCSystem {

	void addManager(String name, int clearanceLvl) throws UserAlreadyExistsException;
	
	User addDeveloper(String name, String mng, int clearanceLvl) throws UserAlreadyExistsException,
																		ManagerDoesNotExistException;
	
	Iterator<User> getAllUsers();
	
	Iterator<Projects> getAllProjects();

	int numUsers();

	void addUserToProj(String projectName, String userName) throws UsernameDoesNotExistException,
																				 AlreadyTeamMemberException,
																				 InsufficientClearanceLevelException;

	void checkProjType(String type) throws UnknownProjectTypeException;

	void createNewInHouseProj(String projMng, String projName, List<String> keyWords, int confLvl) throws ManagerDoesNotExistException,
																										  ManagerInsufficientClearanceLevelException,
																										  ProjectNameAlreadyExistsException;

	void createNewOutSourcedProj(String projMng, String projName, List<String> keyWords, String companyName) throws ManagerDoesNotExistException,
																													ProjectNameAlreadyExistsException;

	void checkProjAndMng(String mngName, String projectName) throws ManagerDoesNotExistException,
																	ProjectNameDoesNotExistsException,
																	ProjectNotManagedByUserException;

	void checkJobPos(String jobPosition) throws UnknowJobPositionException;
	
	void checkUserAndProj(String user, String project) throws UserDoesNotExistException,
															  ProjectNameDoesNotExistsException,
															  UserDoesNotBelongToTeamException;
	
	void addArtefact(Artefacts e, String projectName) throws ArtefactAlreadyInProjectException,
															 ExceedsProjectConfidentialityLevelException,
															 UserDoesNotBelongToTeamException;

	Iterator<Projects> getProjsByKeyword(String keyWord);

	Iterator<User> getAllProjUsers(InHouse proj);

	Iterator<Artefacts> getAllProjArtefacts(InHouse proj);

	InHouse getInHouseProj(String projName) throws ProjectNameDoesNotExistsException, ProjectIsOutsourcedException;

	Revision reviseArtefact(String userName, String projectName,
			                String artefactName, LocalDate revisionDate, String comment) throws UserDoesNotExistException,
																							    ProjectNameDoesNotExistsException,
																							    ArtefactDoesNotExistsException,
																							    UserDoesNotBelongToTeamException;
	
}

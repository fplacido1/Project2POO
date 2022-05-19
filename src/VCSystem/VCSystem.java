package VCSystem;
import java.util.*;
import Exceptions.*;

public interface VCSystem {

	User addManager(String name, int clearanceLvl) throws UserAlreadyExistsException;
	
	User addDeveloper(String name, String mng, int clearanceLvl) throws UserAlreadyExistsException,
																		ManagerDoesNotExistException;
	
	Iterator<User> getAllUsers();

	int numUsers();

	void addUserToProj(String mngName, String projectName, String string) throws ManagerDoesNotExistException,
																				 ProjectNameDoesNotExistsException,
																				 ProjectNotManagedByUserException,
																				 UserDoesNotExistException,
																				 AlreadyTeamMemberException,
																				 InsufficientClearanceLevelException;

	void checkProjType(String type) throws UnknownProjectTypeException;

	void createNewInHouseProj(String projMng, String projName, List<String> keyWords, int confLvl) throws ManagerDoesNotExistException,
																										  ManagerInsufficientClearanceLevelException;

	void createNewOutSourcedProj(String projMng, String projName, List<String> keyWords, String companyName) throws ManagerDoesNotExistException;

	void checkProjAndMng(String mngName, String projectName) throws ManagerDoesNotExistException,
																	ProjectNameDoesNotExistsException,
																	ProjectNotManagedByUserException;

	void checkJobPos(String jobPosition) throws UnknowJobPositionException;
}

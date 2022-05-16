package VCSystem;
import java.util.*;
import Exceptions.*;

public interface VCSystem {

	void addUser(String jobPosition, String name, int clearanceLvl) throws UnknowJobPositionException,
																		   UserAlreadyExistsException,
																		   ProjectManagerDoesNotExistsException;

//	Iterator<Users> getAllUsers();

	int numUsers();

	void addUserToProj(String mngName, String projectName, String string) throws ManagerDoesNotExistException,
																				 ProjectNameDoesNotExistsException,
																				 ProjectNotManagedByUserException,
																				 UserDoesNotExistException,
																				 AlreadyTeamMemberException,
																				 InsufficientClearanceLevelException;

	void checkProjType(String type) throws UnknownProjectTypeException;

	void createNewInHouseProj(String projMng, String projName, List<String> keyWords, int confLvl);

	void createNewOutSourcedProj(String projMng, String projName, List<String> keyWords, String companyName);

	void checkProjAndMng(String mngName, String projectName) throws ManagerDoesNotExistException,
																	ProjectNameDoesNotExistsException,
																	ProjectNotManagedByUserException;

}

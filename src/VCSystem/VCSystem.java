package VCSystem;
import java.util.Iterator;

import Exceptions.*;

public interface VCSystem {

	void addUser(String jobPosition, String name, int clearanceLvl) throws UnknowJobPositionException,
																		   UserAlreadyExistsException,
																		   ProjectManagerDoesNotExistsException;

	Iterator<Users> getAllUsers();

	int numUsers();

}

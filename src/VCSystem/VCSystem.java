package VCSystem;
import Exceptions.*;

public interface VCSystem {

	void addUser(String jobPosition, String name, int clearanceLvl) throws UnknowJobPositionException,
																		   UserAlreadyExistsException,
																		   ProjectManagerDoesNotExistsException;

}

package VCSystem;

import java.util.Iterator;
import Exceptions.*;

public class VCSystemClass implements VCSystem {

	@Override
	public void addUser(String jobPosition, String name, int clearanceLvl)
			throws UnknowJobPositionException, UserAlreadyExistsException, ProjectManagerDoesNotExistsException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterator<Users> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int numUsers() {
		// TODO Auto-generated method stub
		return 0;
	}

}

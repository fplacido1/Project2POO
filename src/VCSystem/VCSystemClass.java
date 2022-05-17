package VCSystem;

import java.util.Iterator;
import java.util.List;

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

	@Override
	public void addUserToProj(String mngName, String projectName, String string)
			throws ManagerDoesNotExistException, ProjectNameDoesNotExistsException, ProjectNotManagedByUserException,
			UserDoesNotExistException, AlreadyTeamMemberException, InsufficientClearanceLevelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkProjType(String type) throws UnknownProjectTypeException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createNewInHouseProj(String projMng, String projName, List<String> keyWords, int confLvl) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createNewOutSourcedProj(String projMng, String projName, List<String> keyWords, String companyName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkProjAndMng(String mngName, String projectName)
			throws ManagerDoesNotExistException, ProjectNameDoesNotExistsException, ProjectNotManagedByUserException {
		// TODO Auto-generated method stub
		
	}

}

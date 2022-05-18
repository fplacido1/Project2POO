package VCSystem;

import java.util.*;

import Exceptions.*;


public class VCSystemClass implements VCSystem {
	
	private Map<String, User> users;
	private Map<String, Manager> managers;
	private Map<String, Projects> projects;
	
	private enum JobType{
		MNG("manager"), DEV("developer");
		
		private String type;
		
		private JobType(String type) {
			this.type = type;
		}
		
		private String getType() {
			return type;
		}
	}
	
	public VCSystemClass() {
		users = new TreeMap<>();
		projects = new HashMap<>();
		managers = new HashMap<>();
	}

	@Override
	public User addManager(String name, int clearanceLvl) throws UserAlreadyExistsException {
		if(managers.containsKey(name)) {
			throw new UserAlreadyExistsException(name);
		}
		else {
			Manager m = new ManagerClass(name, clearanceLvl);
			users.put(name, m);
			managers.put(name, m);
			return m;
		}
	}

	@Override
	public User addDeveloper(String name, String mng, int clearanceLvl) throws UserAlreadyExistsException, ManagerDoesNotExistException {
		Manager m = managers.get(mng);
		if(users.containsKey(name)) {
			throw new UserAlreadyExistsException(name);
		}
		else if(m == null) {
			throw new ManagerDoesNotExistException(mng);
		}
		else {
			Developer d = new DeveloperClass(name, mng, clearanceLvl);
			users.put(name, d);
			m.addManagedDev(name, d);
			return d;
		}
	}

	@Override
	public void checkJobPos(String jobPosition) throws UnknowJobPositionException {
		if(!jobPosition.equals(JobType.DEV.getType()) && !jobPosition.equals(JobType.MNG.getType())) {
			throw new UnknowJobPositionException();
		}
	}
	
	@Override
	public Iterator<User> getAllUsers() {
		return users.values().iterator();
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

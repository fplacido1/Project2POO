package VCSystem;

import java.util.*;
import VCSystem.exceptions.*;

public class VCSystemClass implements VCSystem {
	
	private Map<String, User> users;
	private Map<String, Manager> managers;
	private Map<String, Projects> projects;
	private Map<String, List<Projects>> projsByKeyWord;
	
	private enum JobType{
		MNG("manager"), DEV("developer");
		
		private String type;
		
		private JobType(String type) {
			this.type = type;
		}
	}
	
	private enum ProjType{
		IN("inhouse"), OUT("outsourced");
		
		private String type;
		
		private ProjType(String type) {
			this.type = type;
		}
	}
	
	public VCSystemClass() {
		users = new TreeMap<>();
		projects = new HashMap<>();
		managers = new HashMap<>();
		projsByKeyWord = new HashMap<>();
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
			Developer d = new DeveloperClass(name, m, clearanceLvl);
			users.put(name, d);
			m.addManagedDev(name, d);
			return d;
		}
	}

	@Override
	public void checkJobPos(String jobPosition) throws UnknowJobPositionException {
		if(!jobPosition.equals(JobType.DEV.type) && !jobPosition.equals(JobType.MNG.type)) {
			throw new UnknowJobPositionException();
		}
	}
	
	@Override
	public Iterator<User> getAllUsers() {
		return users.values().iterator();
	}

	@Override
	public int numUsers() {
		return users.size();
	}

	@Override
	public void addUserToProj(String mngName, String projectName, String string)
			throws ManagerDoesNotExistException, ProjectNameDoesNotExistsException, ProjectNotManagedByUserException,
			UsernameDoesNotExistException, AlreadyTeamMemberException, InsufficientClearanceLevelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkProjType(String type) throws UnknownProjectTypeException {
		if(!type.equals(ProjType.IN.type) && !type.equals(ProjType.OUT.type)) {
			throw new UnknownProjectTypeException();
		}
		
	}

	@Override
	public void createNewInHouseProj(String projMng, String projName, List<String> keyWords, int confLvl) 
			throws ManagerDoesNotExistException,ManagerInsufficientClearanceLevelException {
		
		Manager mng = managers.get(projMng);
		if(mng == null) {
			throw new ManagerDoesNotExistException(projMng);
		}
		else if(mng.getClearanceLvl() < confLvl){
			throw new ManagerInsufficientClearanceLevelException(mng.getName(), mng.getClearanceLvl());
		}
		else {
			Projects inHouse = new InHouseClass(mng, projName, confLvl, keyWords);
			projects.put(projName, inHouse);
			for(int i = 0; i < keyWords.size(); i++) {
				if(!projsByKeyWord.containsKey(keyWords.get(i))) {
					List<Projects> projs = new ArrayList<>();
					projs.add(inHouse);
					projsByKeyWord.put(keyWords.get(i), projs);
				}
				else {
					projsByKeyWord.get(keyWords.get(i)).add(inHouse);
				}
			}
		}
	}

	@Override
	public void createNewOutSourcedProj(String projMng, String projName, List<String> keyWords, String companyName) 
			throws ManagerDoesNotExistException {
		
		Manager mng = managers.get(projMng);
		if(mng == null) {
			throw new ManagerDoesNotExistException(projMng);
		}
		else {
			Projects out = new OutSourcedClass(mng, projName, companyName, keyWords);
			projects.put(projName, out);
			for(int i = 0; i < keyWords.size(); i++) {
				if(!projsByKeyWord.containsKey(keyWords.get(i))) {
					List<Projects> projs = new ArrayList<>();
					projs.add(out);
					projsByKeyWord.put(keyWords.get(i), projs);
				}
				else {
					projsByKeyWord.get(keyWords.get(i)).add(out);
				}
			}
		}
	}

	@Override
	public void checkProjAndMng(String mngName, String projectName)
			throws ManagerDoesNotExistException, ProjectNameDoesNotExistsException, ProjectNotManagedByUserException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkUserBelongsToTeam(String user, String project)// falta um else
			throws UserDoesNotExistException, ProjectNameDoesNotExistsException, UserDoesNotBelongToTeamException {
		if(!users.containsKey(user)) {
			throw new UserDoesNotExistException(user);
		}else if(!projects.containsKey(project)) {
			throw new ProjectNameDoesNotExistsException(project);
		}
		
	}

	@Override
	public void addArtefect(Artefacts e, String projectName) 
			throws ArtefactAlreadyInProjectException, ExceedsProjectConfidentialityLevelException {
		InHouse tmp = (InHouse)projects.get(projectName);
		if(tmp.containsArtefact(e)) {// TODO é objeto ou nome?
			throw new ArtefactAlreadyInProjectException(e.getName());
		}else if(tmp.getConfLvl() < e.getConfidentialityLevel()) {
			throw new ExceedsProjectConfidentialityLevelException(e.getName());
		}else {
			tmp.addArtefact(e);
		}
		
	}

	@Override
	public Iterator<Projects> getAllProjects(){
		return projects.values().iterator();
	}

	@Override
	public Iterator<Projects> getProjsByKeyword(String keyWord) {
		return projsByKeyWord.get(keyWord).iterator();
	}
}

package VCSystem;

import java.time.LocalDate;
import java.util.*;
import VCSystem.exceptions.*;

public class VCSystemClass implements VCSystem {
	
	private Map<String, User> users;
	private Map<String, Manager> managers;
	private Map<String, Projects> projects;
	private Map<String, InHouse> inHouseProjs;
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
		projects = new LinkedHashMap<>(); //TODO DUVIDA
		managers = new HashMap<>();
		inHouseProjs = new HashMap<>();
		projsByKeyWord = new HashMap<>();
	}

	@Override
	public void addManager(String name, int clearanceLvl) throws UserAlreadyExistsException {
		if(users.containsKey(name)) {
			throw new UserAlreadyExistsException(name);
		}
		else {
			Manager m = new ManagerClass(name, clearanceLvl);
			users.put(name, m);
			managers.put(name, m);
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
	public void addUserToProj(String projectName, String userName)
			throws UsernameDoesNotExistException, AlreadyTeamMemberException, InsufficientClearanceLevelException {
		InHouse p = inHouseProjs.get(projectName);
		User u = users.get(userName);
		if(u == null) {
			throw new UsernameDoesNotExistException(userName);
		}
		else {
			p.addUser(u);
			u.addProj(p);
		}
	}

	@Override
	public void checkProjType(String type) throws UnknownProjectTypeException {
		if(!type.equals(ProjType.IN.type) && !type.equals(ProjType.OUT.type)) {
			throw new UnknownProjectTypeException();
		}
		
	}

	@Override
	public void createNewInHouseProj(String projMng, String projName, List<String> keyWords, int confLvl) 
			throws ManagerDoesNotExistException,ManagerInsufficientClearanceLevelException, ProjectNameAlreadyExistsException {
		
		Manager mng = managers.get(projMng);
		if(mng == null) {
			throw new ManagerDoesNotExistException(projMng);
		}
		else if(projects.containsKey(projName)) {
			throw new ProjectNameAlreadyExistsException(projName);
		}
		else if(mng.getClearanceLvl() < confLvl){
			throw new ManagerInsufficientClearanceLevelException(mng.getName(), mng.getClearanceLvl());
		}
		else {
			InHouse inHouse = new InHouseClass(mng, projName, confLvl, keyWords);
			projects.put(projName, inHouse);
			inHouseProjs.put(projName, inHouse);
			mng.addToManagedProjs(inHouse);
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
			throws ManagerDoesNotExistException, ProjectNameAlreadyExistsException {
		
		Manager mng = managers.get(projMng);
		if(mng == null) {
			throw new ManagerDoesNotExistException(projMng);
		}
		else if(projects.containsKey(projName)) {
			throw new ProjectNameAlreadyExistsException(projName);
		}
		else {
			Projects out = new OutSourcedClass(mng, projName, companyName, keyWords);
			projects.put(projName, out);
			mng.addToManagedProjs(out);
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
		Manager mng = managers.get(mngName);
		InHouse p = inHouseProjs.get(projectName);
		if(mng == null) {
			throw new ManagerDoesNotExistException(mngName);
		}
		else if(p == null) {
			throw new ProjectNameDoesNotExistsException(projectName);
		}
		else if(!p.getManager().getName().equals(mngName)) {
			throw new ProjectNotManagedByUserException(projectName, p.getManager().getName());
		}
	}

	@Override
	public void checkUserAndProj(String user, String project)
			throws UserDoesNotExistException, ProjectNameDoesNotExistsException, UserDoesNotBelongToTeamException{
		if(!users.containsKey(user)) {
			throw new UserDoesNotExistException(user);
		}
		else if(!inHouseProjs.containsKey(project)) {
			throw new ProjectNameDoesNotExistsException(project);
		}
		else{
			InHouse p = inHouseProjs.get(project);
			if(!p.containsUser(user)) {
				throw new UserDoesNotBelongToTeamException(user, project);
			}
		}
		
	}

	@Override
	public void addArtefact(Artefacts e, String projectName) 
			throws ArtefactAlreadyInProjectException, ExceedsProjectConfidentialityLevelException {
		InHouse tmp = inHouseProjs.get(projectName);
		if(tmp.containsArtefact(e)) {// TODO objeto ou nome?
			throw new ArtefactAlreadyInProjectException(e.getName());
		}
		else if(tmp.getConfLvl() < e.getConfidentialityLevel()) {
			throw new ExceedsProjectConfidentialityLevelException(e.getName());
		}
		else {
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

	@Override
	public Iterator<User> getAllProjUsers(InHouse proj) {
		return proj.getAllUsers();
	}

	@Override
	public Iterator<Artefacts> getAllProjArtefacts(InHouse proj) {
		return proj.getAllArtefacts();
	}

	@Override
	public InHouse getInHouseProj(String projName) throws ProjectNameDoesNotExistsException, ProjectIsOutsourcedException {
		if(!projects.containsKey(projName)) {
			throw new ProjectNameDoesNotExistsException(projName);
		}
		else if(!inHouseProjs.containsKey(projName)) {
			throw new ProjectIsOutsourcedException(projName);
		}
		else {
			return inHouseProjs.get(projName);
		}
		
	}

	@Override
	public Revision reviseArtefact(String userName, String projectName, String artefactName, LocalDate revisionDate, String comment)
			throws UserDoesNotExistException, ProjectNameDoesNotExistsException,
			ArtefactDoesNotExistsException, UserDoesNotBelongToTeamException{

		User u = users.get(userName);
		InHouse p = inHouseProjs.get(projectName);
		if(u == null) {
			throw new UserDoesNotExistException(userName);
		}
		else if(p == null) {
			throw new ProjectNameDoesNotExistsException(projectName);
		}
		else {
			Revision r = p.reviseArtefact(u, artefactName, revisionDate, comment);
			u.addArtefactRevised(r);
			return r;
		}
	}

	@Override
	public Iterator<User> getAllManagerUsers(String managerName) throws ManagerDoesNotExistException {
		Manager mng = managers.get(managerName);
		if(mng == null) {
			throw new ManagerDoesNotExistException(managerName);
		}
		else {
			return mng.getAllUsers();
		}
	}
}

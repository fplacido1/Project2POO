package VCSystem;

import java.time.LocalDate;
import java.util.*;
import VCSystem.exceptions.*;

public class VCSystemClass implements VCSystem {
	
	private static final int WAHOLICS_SIZE = 3;
	
	private Map<String, User> users;
	private Map<String, Manager> managers;
	private Map<String, Projects> projects;
	private Map<String, InHouse> inHouseProjs;
	private Map<String, List<InHouse>> inHouseByKeyWord;
	private Map<String, List<OutSourced>> outSourcedByKeyWord;
	private List<User> workaholics;
	
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
		projects = new LinkedHashMap<>();
		managers = new HashMap<>();
		inHouseProjs = new TreeMap<>();
		inHouseByKeyWord = new HashMap<>();
		outSourcedByKeyWord = new HashMap<>();
		workaholics = new ArrayList<>(WAHOLICS_SIZE);
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
	public void addDeveloper(String name, String mng, int clearanceLvl) throws UserAlreadyExistsException, ManagerDoesNotExistException {
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
				if(!inHouseByKeyWord.containsKey(keyWords.get(i))) {
					List<InHouse> projs = new ArrayList<>();
					projs.add(inHouse);
					inHouseByKeyWord.put(keyWords.get(i), projs);
				}
				else {
					inHouseByKeyWord.get(keyWords.get(i)).add(inHouse);
				}
			}
		}
	}

	@Override
	public void createNewOutSourcedProj(String projMng, String projName, List<String> keyWords, String companyName) 
			throws ManagerDoesNotExistException, ProjectNameAlreadyExistsException {
		
		Manager mng = managers.get(projMng);
		Projects p = projects.get(projName);
		if(mng == null) {
			throw new ManagerDoesNotExistException(projMng);
		}
		else if(p instanceof OutSourced && p != null) {
			throw new ProjectNameAlreadyExistsException(projName);
		}
		else {
			OutSourced out = new OutSourcedClass(mng, projName, companyName, keyWords);
			projects.put(projName, out);
			mng.addToManagedProjs(out);
			for(int i = 0; i < keyWords.size(); i++) {
				if(!outSourcedByKeyWord.containsKey(keyWords.get(i))) {
					List<OutSourced> projs = new ArrayList<>();
					projs.add(out);
					outSourcedByKeyWord.put(keyWords.get(i), projs);
				}
				else {
					outSourcedByKeyWord.get(keyWords.get(i)).add(out);
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
	public void addArtefact(String artefactName, int confidentialityLevel, String description, LocalDate date, String projectName, String userName) 
			throws ArtefactAlreadyInProjectException, ExceedsProjectConfidentialityLevelException {
		Artefacts e = new ArtefactsClass(artefactName, confidentialityLevel, description, date);
		
		InHouse tmp = inHouseProjs.get(projectName);
	    tmp.addArtefact(e);
	    
	    User u = users.get(userName);
	    Revision r = tmp.reviseArtefact(u, artefactName, date, description);
	    u.addArtefactRevised(r);
	    checkWorkaholics(u);
	}

	@Override
	public Iterator<Projects> getAllProjects(){
		return projects.values().iterator();
	}

	@Override
	public Iterator<InHouse> getInHouseByKeyword(String keyWord) {
		if(inHouseByKeyWord.containsKey(keyWord)) {
			List<InHouse> byKeyWord = inHouseByKeyWord.get(keyWord);
			ComparatorProjectInHouse c1 = new ComparatorProjectInHouse();
			SortedSet<InHouse> projsKeyWord = new TreeSet<>(c1);
			for(int i = 0; i < byKeyWord.size(); i++) {
				projsKeyWord.add(byKeyWord.get(i));
			}
			return projsKeyWord.iterator();
		}
		return null;
	}

	@Override
	public Iterator<OutSourced> getOutSourcedByKeyword(String keyWord) {
		if(outSourcedByKeyWord.containsKey(keyWord)) {
			List<OutSourced> byKeyWord = outSourcedByKeyWord.get(keyWord);
			SortedMap<String, OutSourced> projsKeyWord = new TreeMap<>();
			for(int i = 0; i < byKeyWord.size(); i++) {
				projsKeyWord.put(byKeyWord.get(i).getProjName() ,byKeyWord.get(i));
			}
			return projsKeyWord.values().iterator();
		}
		return null;
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
		else if(!p.containsArtefact(artefactName)) {
			throw new ArtefactDoesNotExistsException(artefactName);
		}
		else if(!p.containsUser(userName)) {
			throw new UserDoesNotBelongToTeamException(userName, projectName);
		}
		else {
			Revision r = p.reviseArtefact(u, artefactName, revisionDate, comment);
			u.addArtefactRevised(r);
			checkWorkaholics(u);
			return r;
		}
	}
	
	private void checkWorkaholics(User u) {
		if(workaholics.isEmpty()) {
			workaholics.add(u);
		}
		else if(!workaholics.contains(u)) {
			updateWorkaholics(u);
		}
		else {
			workaholics(u);
		}
	}
			
	private void workaholics(User u) {
		int userIndex = getWorkIndex(u.getName());
		workaholics.remove(userIndex);
		int indexToAdd = userIndex;
		for(int i = 0 ; i < workaholics.size(); i++) {
			if(u.compareTo(workaholics.get(i)) > 0) {
				indexToAdd = i;
				break;
			}
		}
		workaholics.add(indexToAdd, u);
	}

	private void updateWorkaholics(User u) {
		int indexToAdd = -1;
		for(int i = 0; i < workaholics.size(); i++) {
			if(u.compareTo(workaholics.get(i)) > 0) {
				indexToAdd = i;
				break;
			}
		}
		if(indexToAdd != -1) {
			if(workaholics.size() == WAHOLICS_SIZE) {
				workaholics.remove(2);
			}
			workaholics.add(indexToAdd, u);
		}
	}

	private int getWorkIndex(String name) {
		int index = 0;
		for(int i = 0; i < workaholics.size(); i++) {
			if(workaholics.get(i).getName().equals(name)) {
				index = i;
				break;
			}
		}
		return index;
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

	@Override
	public Iterator<InHouse> getProjsWithIn(int lowerLimit, int upperLimit) {
		List<InHouse> temp = new ArrayList<>();
		for(InHouse proj : inHouseProjs.values()) {
			if(proj.getConfLvl() <= upperLimit && proj.getConfLvl() >= lowerLimit) {
				temp.add(proj);
			}
		}
		return temp.iterator();
	}

	@Override
	public Iterator<User> getWorkaholics() {
		return workaholics.iterator();
	}

	@Override
	public Iterator<Common> getCommonUsers() {
		Common common = new CommonClass(null,null,0);
		Iterator<User> it = getAllUsers();
		while(it.hasNext()) {
			User u1 = it.next();
			Iterator<User> it2 = getAllUsers();
			while(it2.hasNext()) {
				User u2 = it.next();
				int projsInCommon = u1.getProjsInCommon(u2);
				if(projsInCommon > common.getNumProjsInCommon()) {
					common = new CommonClass(u1, u2, projsInCommon);
				}
				else if(projsInCommon == common.getNumProjsInCommon()) {
					Common tmp = new CommonClass(u1, u2, projsInCommon);
					if(tmp.compareTo(common) > 0) {
						common = tmp;
					}
				}
			}
		}
		return null;
	}

	
}

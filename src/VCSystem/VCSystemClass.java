package VCSystem;

import java.time.LocalDate;
import java.util.*;
import VCSystem.exceptions.*;

/**
 * 
 * @author Joao Norberto (62685) & Francisco Placido (62674)
 * 
 * Date of last update: 30 of may of 2022
 */
public class VCSystemClass implements VCSystem {
	
	private static final int WAHOLICS_SIZE = 3;
	
	/**
	 * TreeMap of all the users registered in the system
	 */
	private Map<String, User> users;
	
	/**
	 * HashMap of all the managers registered in the system
	 */
	private Map<String, Manager> managers;
	
	/**
	 * LinkedHashMap of all the projects
	 */
	private Map<String, Projects> projects;
	
	/**
	 * TreeMap of all the In-House projects
	 */
	private Map<String, InHouse> inHouseProjs;
	
	/**
	 * HashMap of all the In-House projects by keyword
	 */
	private Map<String, List<InHouse>> inHouseByKeyWord;
	
	/**
	 * HashMap of all the In-House projects by keyword
	 */
	private Map<String, List<OutSourced>> outSourcedByKeyWord;
	
	/**
	 * Array of the top 3 workaholics in the system
	 */
	private List<User> workaholics;
	
	/**
	 * 
	 * @author Joao Norberto (62685) & Francisco Placido (62674)
	 * 
	 * This enum has all the valid job types
	 * 
	 * Date of last update: 23 of may of 2022
	 *
	 */
	private enum JobType{
		MNG("manager"), DEV("developer");
		
		private String type;
		
		private JobType(String type) {
			this.type = type;
		}
	}
	
	/**
	 * 
	 * @author Joao Norberto (62685) & Francisco Placido (62674)
	 * 
	 * This enum has all the valid project types
	 * 
	 * Date of last update: 23 of may of 2022
	 *
	 */
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
			m.addManagedDev(d);
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
			if(u.getLastUpdateDone() != null) {
				checkWorkaholics(u);
			}
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
	
	/**
	 * This method selects what update
	 * should be done to the array
	 * workaholics, based on a given 
	 * user
	 * @param u , given user, to update
	 * the workaholics array
	 */
	// pre u != null
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
	
	/**
	 * This method updates the array 
	 * workaholics if the user is already
	 * in the array
	 * @param u , user to update
	 */
	// pre: u != null
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

	/**
	 * This method updates the array
	 * workaholics if the user is not
	 * in the array
	 * @param u , user to update
	 */
	// pre: u != null
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

	/**
	 * This method retuns the index
	 * of a user in the workaholics
	 * array
	 * @param name , name of the user
	 * to search
	 * @return index of the user. Index = -1
	 * if the user is not in the array
	 */
	// pre: name != null
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
	public Common getCommonUsers() {
		Common common  = new CommonClass(null,null,0);
		User[] temp = users.values().toArray(new User[users.size()]);
		for(int i = 0; i < temp.length; i++) {
			User u1 = temp[i];
			if(u1.getNumProjs() >= common.getNumProjsInCommon()) {
				for(int j = i + 1; j < temp.length; j++) {
					User u2 = temp[j];
					if(u2.getNumProjs() >= common.getNumProjsInCommon()) {
						int projsInCommon = u1.getProjsInCommon(u2);
						if(projsInCommon > common.getNumProjsInCommon() || common.getFirstUser() == null) {
							common = createCommon(u1, u2, projsInCommon);
						}
						else if(projsInCommon == common.getNumProjsInCommon()) {
							Common tmp = createCommon(u1, u2, projsInCommon);
							if(tmp.compareTo(common) > 0) {
								common = tmp;						
							}
						}
					}
				}
			}
		}
		return common;
	}	
	
	/**
	 * This method creates a object
	 * of the type common and returns
	 * it
	 * @param u1 , user to add
	 * @param u2 , user to add
	 * @param projsInCommon , number
	 * of projects in common between
	 * the wtwo users
	 * @return object of the type
	 * common
	 */
	// pre: u1 != null && u2 != null && projsInCommon != null
	private Common createCommon(User u1, User u2, int projsInCommon) {
		Common common;
		if(u2.getName().compareTo(u1.getName()) > 0) {
			common = new CommonClass(u1, u2, projsInCommon);
		}
		else {
			common = new CommonClass(u2, u1, projsInCommon);
		}
		return common;
	}
}

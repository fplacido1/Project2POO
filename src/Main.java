import java.util.*;
import VCSystem.*;
import VCSystem.exceptions.*;
import java.time.*;
import java.time.format.DateTimeFormatter;//TODO duvida

/**
 * 
 * @author Joao Norberto (62685) & Francisco Placido(62764)
 *
 */
public class Main {
	
	private static final String BYE = "Bye!";
	private static final String UNKNOWN_COMMAND = "Unknown command. Type help to see available commands.";
	private static final String FORMAT_HELP = "%s - %s\n";
	private static final String ADDED_DEV = "User %s was registered as developer with clearance level %d.\n";
	private static final String ADDED_MNG = "User %s was registered as manager with clearance level %d.\n";
	private static final String DEV = "developer";
	private static final String MNG = "manager";
	private static final String NO_USERS = "No users registered.";
	private static final String NO_PROJECTS = "No projects added.";
	private static final String ALL_USERS = "All registered users:";
	private static final String DEV_REG = "developer %s is managed by %s [%d]\n";
	private static final String MNG_REG = "manager %s [%d, %d, %d]\n";
	private static final String INHOUSE = "inhouse";
	private static final String OUTSRC = "outsourced";
	private static final String ADDED_TO_TEAM = "%s: added to the team.\n";
	private static final String WAS_CREATED = "%s project was created.\n";
	private static final String ARTEFACT_ADDED_PROJECT = "%s: added to the project.\n";
	private static final String ARTEFACT_MSG = "Latest project artefacts:";
	private static final String ALL_PROJECTS = "All projects:";
	private static final String INHOUSE_REG = "in-house %s is managed by %s [%d, %d, %d, %d]\n";
	private static final String OUTSOURCED_REG = "outsourced %s is managed by %s and developed by %s\n";
	private static final String NO_PROJS_KW = "No projects with keyword %s.\n";
	private static final String ALL_PROJS_WITH_KW = "All projects with keyword %s:\n";
	private static final String OUTSRC_BY_KW = "outsourced %s is managed by %s and developed by %s\n";
	private static final String INHOUSE_BY_KW = "in-house %s is managed by %s [%d, %d, %d, %d, %s]\n";
	private static final String IN_HOUSE_DETAILS = "%s [%d] managed by %s [%d]:\n";
	private static final String USER_DETAILS = "%s [%d]\n";
	private static final String ART_DETAILS = "%s [%d]\n";
	private static final String REV_DETAILS = "revision %d %s %s %s\n";
	private static final String DATE_FORMAT = "dd-MM-yyyy";
	private static final String REVISION_DONE  = "Revision %d of artefact %s was submitted.\n";
	private static final String LATEST_MEMBERS = "Latest team members:";
	private static final String MANAGER = "Manager %s:\n";
	private static final String REVISION_DETAILS = "%s, %s, %d, %s, %s\n";
	private static final String NO_PROJS_WITHIN = "No projects within levels %d and %d.\n";
	private static final String PROJ_INFO = "%s [%d] is managed by %s and has keywords ";
	private static final String NO_WORKAHOLICS = "There are no workaholics.";
	private static final String WORKAHOLICS = "%s: %d updates, %d projects, last update on %s\n";
	private static final String SEPARATOR = ", ";
	private static final String TERMINATOR = ".";
	private static final String NO_COMMON_PROJS = "Cannot determine employees with common projects.";
	private static final String AVAILABLE_COMM = "Available commands:";
	
	/**
	 * 
	 * @author Joao Norberto (62685) & Francisco Placido ()
	 *
	 */
	private enum Command{
		
		REGISTER("register","adds a new user"), USERS("users","lists all registered users"), CREATE("create","creates a new project"),
		PROJECTS("projects","lists all projects"), TEAM("team","adds team members to a project"),
		ARTEFACTS("artefacts","adds artefacts to a project"), PROJECT("project","shows detailed project information"),
		REVISION("revision","revises an artefact"), MANAGES("manages","lists developers of a manager"),
		KEYWORD("keyword","filters projects by keyword"), CONFIDENTIALITY("confidentiality","filters projects by confidentiality level"),
		WORKAHOLICS("workaholics","top 3 employees with more artefacts updates"), COMMON("common","employees with more projects in common"),
		HELP("help","shows the available commands"), EXIT("exit","terminates the execution of the program"), UNKNOWN("","");
		
		private String desc;
		private String comm;
		
		private Command(String comm, String desc) {
			this.comm = comm;
			this.desc = desc;
		}
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		VCSystem vc = new VCSystemClass();
		Command command = getCom(in);
		while(!command.equals(Command.EXIT)) {
			chooseCommand(command, in, vc);
			command = getCom(in);
		}
		exit();
		in.close();
	}

	/**
	 * This method executes one of the commands
	 * in the enum Command, based on the user
	 * input
	 * @param command, command wrote by the
	 * user. <code>Unknown</code> if the
	 * command wrote is not on the enum
	 * @param in, allows the method to
	 * use the scanner so it can read the
	 * user inputs
	 * @param vc, allows the method to access
	 * the class VCSystemClass so it can
	 * execute the commands
	 */
	private static void chooseCommand(Command command, Scanner in, VCSystem vc) {
		switch(command) {
		case REGISTER:        registerUser(vc, in);           break;
		case USERS:           in.nextLine(); getAllUsers(vc); break;
		case CREATE:          createNewProject(vc, in);       break;
		case PROJECTS:        getAllProjects(vc);         	  break;
		case TEAM:            addTeamMembers(vc, in);         break;
		case ARTEFACTS:       addArtefact(vc, in);            break;
		case PROJECT:         getInHouseDetails(vc, in);      break;
		case REVISION:        reviseArtefact(vc, in);         break;
		case MANAGES:         getAllManaged(vc, in);          break;
		case KEYWORD:         filterByKeyword(vc, in);        break;
		case CONFIDENTIALITY: filterByConfidentiality(vc, in);break;
		case WORKAHOLICS:     getWorkaholics(vc);             break;
		case COMMON:          moreProjectsInCommon(vc);       break;//TODO
		case HELP:            help();                         break;
		default:                                              break;
		}
		
	}

	/**
	 * This method registers a user
	 * in the system.
	 * @param vc, allows the method to access
	 * the class VCSystemClass so it can
	 * execute the commands
	 * @param in, allows the method to
	 * use the scanner so it can read the
	 * user inputs
	 */
	private static void registerUser(VCSystem vc, Scanner in) {
		String jobPosition = in.next();
		String name = in.next();
		try {
			vc.checkJobPos(jobPosition);
			switch(jobPosition) {
			case MNG:
				int clearanceLvl = in.nextInt();
				addManager(name, clearanceLvl, vc); break;
			case DEV:
				String manager = in.next();
				int clearLvl = in.nextInt();
				addDeveloper(name, manager, clearLvl, vc);
			}
		}catch(UnknowJobPositionException e) {
			System.out.println(e.getMessage());
		}
		in.nextLine();
	}
	
	/**
	 * This method registers a manager
	 * to the system
	 * @param name, name of the manager
	 * @param clearanceLvl, clearance
	 * level of the manager
	 * @param vc, allows the method to access
	 * the class VCSystemClass so it can
	 * execute the commands
	 */
	private static void addManager(String name, int clearanceLvl, VCSystem vc) {
		try {
			vc.addManager(name, clearanceLvl);
			System.out.printf(ADDED_MNG, name, clearanceLvl);
		}
		catch(UserAlreadyExistsException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * This method registers a developer
	 * to the system
	 * @param name, name of the developer
	 * @param mng, name of the manager
	 * @param clearanceLvl, clearance
	 * level of the manager
	 * @param vc, allows the method to access
	 * the class VCSystemClass so it can
	 * execute the commands
	 */
	private static void addDeveloper(String name, String mng, int clearanceLvl, VCSystem vc) {
		try {
			vc.addDeveloper(name, mng, clearanceLvl);
			System.out.printf(ADDED_DEV, name, clearanceLvl);
		}
		catch(UserAlreadyExistsException | ManagerDoesNotExistException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * This method lists all registered
	 * users
	 * @param vc, allows the method to access
	 * the class VCSystemClass so it can
	 * execute the commands
	 */
	private static void getAllUsers(VCSystem vc) {
		Iterator<User> it = vc.getAllUsers();
		if(!it.hasNext()) {
			System.out.println(NO_USERS);
		}
		else {
			System.out.println(ALL_USERS);
			while(it.hasNext()) {
				User u = it.next();
				if(u instanceof Manager) {
					System.out.printf(MNG_REG, u.getName(), ((Manager) u).getNumManagedDevs(), 
							                 ((Manager) u).getNumManagedProjs(), u.getNumProjsAsDev());
				}
				else {
					System.out.printf(DEV_REG, u.getName(), ((Developer) u).getManager(), u.getNumProjsAsDev());
				}
			}
		}
	}

	/**
	 * This method creates a new project
	 * @param vc, allows the method to access
	 * the class VCSystemClass so it can
	 * execute the commands
	 * @param in, allows the method to
	 * use the scanner so it can read the
	 * user inputs
	 */
	private static void createNewProject(VCSystem vc, Scanner in) {
		String projMng = in.next();
		String type = in.next();
		String projName = in.nextLine().trim();
		int numKeyWords = in.nextInt();
		List<String> keyWords = new ArrayList<>(numKeyWords);
		for(int i = 0; i< numKeyWords; i++) {
			keyWords.add(in.next());
		}
		in.nextLine();
		try {
			vc.checkProjType(type);
			switch(type) {
			case INHOUSE: createInHouse(vc, projMng, projName, keyWords, in);   break;
			case OUTSRC:  createOutSourced(vc, projMng, projName, keyWords, in);break;
			}
		}
		catch(UnknownProjectTypeException e){
			in.nextLine();
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * This method creates a new OutSourced
	 * project
	 * @param vc, allows the method to access
	 * the class VCSystemClass so it can
	 * execute the commands
	 * @param projMng, name of the project
	 * manager
	 * @param projName, name of the project
	 * @param keyWords, keywords of the
	 * project
	 * @param in, allows the method to
	 * use the scanner so it can read the
	 * user inputs
	 */
	private static void createOutSourced(VCSystem vc, String projMng, String projName, List<String> keyWords, Scanner in) {
		try {
			String companyName = in.nextLine().trim();
			vc.createNewOutSourcedProj(projMng, projName, keyWords, companyName);
			System.out.printf(WAS_CREATED, projName);
		}
		catch(ManagerDoesNotExistException | ProjectNameAlreadyExistsException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * This method creates a new InHouse
	 * project
	 * @param vc, allows the method to access
	 * the class VCSystemClass so it can
	 * execute the commands
	 * @param projMng, name of the project
	 * manager
	 * @param projName, name of the project
	 * @param keyWords, keywords of the
	 * project
	 * @param in, allows the method to
	 * use the scanner so it can read the
	 * user inputs
	 */
	private static void createInHouse(VCSystem vc, String projMng, String projName, List<String> keyWords, Scanner in) {
		try {
			int confLvl = in.nextInt();
			vc.createNewInHouseProj(projMng, projName, keyWords, confLvl);
			System.out.printf(WAS_CREATED, projName);
		}
		catch(ManagerDoesNotExistException | ManagerInsufficientClearanceLevelException |
				ProjectNameAlreadyExistsException e) {
			System.out.println(e.getMessage());
		}
		in.nextLine();
	}

	/**
	 * This method lists all
	 * projects
	 * @param vc, allows the method to access
	 * the class VCSystemClass so it can
	 * execute the commands
	 */
	private static void getAllProjects(VCSystem vc) {
		Iterator<Projects> it = vc.getAllProjects();
		if(!it.hasNext()) {
			System.out.println(NO_PROJECTS);
		}
		else {
			System.out.println(ALL_PROJECTS);
			while(it.hasNext()) {
				Projects u = it.next();
				if(u instanceof InHouse) {
					System.out.printf(INHOUSE_REG, u.getProjName(), u.getManager().getName(), ((InHouse) u).getConfLvl(),
												   ((InHouse) u).getNumDevs(), ((InHouse) u).getNumArtefacts(),
												   ((InHouse) u).getNumRevisions());
				}
				else {
					System.out.printf(OUTSOURCED_REG, u.getProjName(), u.getManager().getName(), ((OutSourced)u).getCompany());
				}
			}
		}
	}

	/**
	 * This method creates a new project
	 * @param vc, allows the method to access
	 * the class VCSystemClass so it can
	 * execute the commands
	 * @param in, allows the method to
	 * use the scanner so it can read the
	 * user inputs
	 */
	private static void addTeamMembers(VCSystem vc, Scanner in) {
		String mngName = in.next();
		String projectName = in.nextLine().trim();
		int numUsers = in.nextInt();
		List<String> names = new ArrayList<>(numUsers);
		for(int i = 0; i < numUsers; i++) {
			names.add(in.next());
		}
		try {
			vc.checkProjAndMng(mngName, projectName);
			System.out.println(LATEST_MEMBERS);
			for(int j = 0; j < numUsers; j++) {
				try {
					vc.addUserToProj(projectName, names.get(j));
					System.out.printf(ADDED_TO_TEAM, names.get(j));
				}
				catch (UsernameDoesNotExistException | AlreadyTeamMemberException | InsufficientClearanceLevelException e) {
					System.out.println(e.getMessage());
				}
			}
		}
		catch(ManagerDoesNotExistException | ProjectNameDoesNotExistsException | ProjectNotManagedByUserException e){
			System.out.println(e.getMessage());
		}
		in.nextLine();
	}

	/**
	 * This method adds artefacts
	 * to a project
	 * @param vc, allows the method to access
	 * the class VCSystemClass so it can
	 * execute the commands
	 * @param in, allows the method to
	 * use the scanner so it can read the
	 * user inputs
	 */
	//TODO REVER COMANDO
	private static void addArtefact(VCSystem vc, Scanner in) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_FORMAT);
		
		String userName = in.next();
		String projectName = in.nextLine().trim();
		String date = in.nextLine();
		LocalDate artefactDate = LocalDate.parse(date, format);
		int num = in.nextInt();
		in.nextLine();
		List<Artefacts> artefactsToAdd = new ArrayList<>();
		
		for(int i = 0; i < num; i++) {
			String artefactName = in.next();
			int confidentialityLevel = in.nextInt();
			String description = in.nextLine().trim();
			artefactsToAdd.add(new ArtefactsClass(artefactName, confidentialityLevel, description, artefactDate));//TODO duvida
		}
		
		try {
			vc.checkUserAndProj(userName, projectName);
			System.out.println(ARTEFACT_MSG);
			for(int i = 0; i < num; i++) {
				try {
					vc.addArtefact(artefactsToAdd.get(i), projectName);
					System.out.printf(ARTEFACT_ADDED_PROJECT, artefactsToAdd.get(i).getName());
				}catch(ArtefactAlreadyInProjectException | ExceedsProjectConfidentialityLevelException e) {
					System.out.println(e.getMessage());
				}
			}
		}catch(UserDoesNotExistException | ProjectNameDoesNotExistsException | UserDoesNotBelongToTeamException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * This method shows the detailed
	 * information of an in-house
	 * project
	 * @param vc, allows the method to access
	 * the class VCSystemClass so it can
	 * execute the commands
	 * @param in, allows the method to
	 * use the scanner so it can read the
	 * user inputs
	 */
	private static void getInHouseDetails(VCSystem vc, Scanner in) {
		String projName = in.nextLine().trim();
		try {
			InHouse p = vc.getInHouseProj(projName);
			Iterator<User> itUsers = vc.getAllProjUsers(p);
			Iterator<Artefacts>itArtefacts = vc.getAllProjArtefacts(p);
			System.out.printf(IN_HOUSE_DETAILS, projName, p.getConfLvl(), p.getManager().getName(),
												p.getManager().getClearanceLvl() );
			while(itUsers.hasNext()) {
				User u = itUsers.next();
				System.out.printf(USER_DETAILS, u.getName(), u.getClearanceLvl());
			}
			while(itArtefacts.hasNext()) {
				Artefacts a = itArtefacts.next();
				System.out.printf(ART_DETAILS, a.getName(), a.getConfidentialityLevel());
				Iterator<Revision> itRev = a.getAllRevision();
				printRevisions(itRev);
			}
		}
		catch(ProjectNameDoesNotExistsException | ProjectIsOutsourcedException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * This method prints all the
	 * revisions of an artefact
	 * @param itRev, iterator of
	 * all the revisions from an
	 * artefact
	 */
	private static void printRevisions(Iterator<Revision> itRev) {
		while(itRev.hasNext()) {
			Revision rev = itRev.next();
			System.out.printf(REV_DETAILS, rev.getNum(), rev.getAuthor(), rev.getDate(), rev.getComment());
		}
	}

	/**
	 * This method revises an
	 * artefact
	 * @param vc, allows the method to access
	 * the class VCSystemClass so it can
	 * execute the commands
	 * @param in, allows the method to
	 * use the scanner so it can read the
	 * user inputs
	 */
	private static void reviseArtefact(VCSystem vc, Scanner in) {
		String userName = in.next();
		String projectName = in.nextLine().trim();
		String artefactName = in.next();
		String date = in.next();
		DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_FORMAT);
		LocalDate revisionDate = LocalDate.parse(date, format);
		String comment = in.nextLine().trim();
		try {
			Revision r = vc.reviseArtefact(userName, projectName, artefactName, revisionDate, comment);
			System.out.printf(REVISION_DONE, r.getNum(), r.getArtefact().getName());
		}
		catch(UserDoesNotExistException | ProjectNameDoesNotExistsException | ArtefactDoesNotExistsException |
			  UserDoesNotBelongToTeamException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * This method shows the detailed
	 * information about the developers
	 * supervised by a given manager
	 * @param vc, allows the method to access
	 * the class VCSystemClass so it can
	 * execute the commands
	 * @param in, allows the method to
	 * use the scanner so it can read the
	 * user inputs
	 */
	private static void getAllManaged(VCSystem vc, Scanner in) {
			String managerName = in.nextLine().trim();
			try {
				Iterator<User> it = vc.getAllManagerUsers(managerName);
				System.out.printf(MANAGER, managerName);
				while(it.hasNext()) {
					User u = it.next();
					System.out.println(u.getName());
					Iterator<Revision> itRev = u.getAllRevisions();
					printUserRevisions(itRev);
				}
			}
			catch (ManagerDoesNotExistException e) {
				System.out.println(e.getMessage());
			}
		
	}
	
	/**
	 * This method prints all the
	 * revisions done by a user
	 * @param itRev, iterator of
	 * all the revisions done by
	 * a user
	 */
	private static void printUserRevisions(Iterator<Revision> itRev) {
		while(itRev.hasNext()) {
			Revision r = itRev.next();
			System.out.printf(REVISION_DETAILS, r.getProjName(), r.getArtefact().getName(), r.getNum(), r.getDate(), r.getComment());
		}
	}

	/**
	 * This method filters projects
	 * by keywords
	 * @param vc, allows the method to access
	 * the class VCSystemClass so it can
	 * execute the commands
	 * @param in, allows the method to
	 * use the scanner so it can read the
	 * user inputs
	 */
	private static void filterByKeyword(VCSystem vc, Scanner in) {
		String keyWord = in.next();
		Iterator<Projects> it = vc.getProjsByKeyword(keyWord);
		if(!it.hasNext()) {
			System.out.printf(NO_PROJS_KW, keyWord);
		}
		else {
			System.out.println(ALL_PROJS_WITH_KW);
			while(it.hasNext()) {
				Projects p = it.next();
				if(p instanceof InHouse) {
					System.out.printf(INHOUSE_BY_KW, p.getProjName(), p.getManager().getName(), ((InHouse) p).getConfLvl(),
													 ((InHouse) p).getNumDevs(), ((InHouse) p).getNumArtefacts(),
													 ((InHouse) p).getNumRevisions(), ((InHouse) p).getLastRevisionDate());
				}
				else {
					System.out.printf(OUTSRC_BY_KW, p.getProjName(), p.getManager().getName(), ((OutSourced) p).getCompany() );
				}
			}
		}
		
	}

	/**
	 * This method filters in-house
	 * projects by confidentiality level
	 * @param vc, allows the method to access
	 * the class VCSystemClass so it can
	 * execute the commands
	 * @param in, allows the method to
	 * use the scanner so it can read the
	 * user inputs
	 */
	private static void filterByConfidentiality(VCSystem vc, Scanner in) {
		int lowerLimit = in.nextInt();
		int upperLimit = in.nextInt();
		Iterator<InHouse> it = vc.getProjsWithIn(lowerLimit, upperLimit);
		if(!it.hasNext()) {
			System.out.printf(NO_PROJS_WITHIN, lowerLimit, upperLimit);
		}
		else {
			while(it.hasNext()) {
				InHouse p = it.next();
				System.out.printf(PROJ_INFO, p.getProjName(), p.getConfLvl(), p.getManager().getName());
				Iterator<String> itKW = p.getKeyWords();
				printProjectKeywords(itKW);
			}
		}
	}
	
	/**
	 * This method prints all the
	 * keywords of a project
	 * @param itKW, iterator of
	 * all the keywords from a
	 * project
	 */
	private static final void printProjectKeywords(Iterator<String> itKW) {
		String answer = "";
		int i = 0;
		while(itKW.hasNext()) {
			if(i < 1) {
				answer += itKW.next();
				i++;
			}
			else {
				answer += SEPARATOR + itKW.next();
			}
		}
		System.out.println(answer + TERMINATOR);
	}

    /**
     * This method determines the 3
     * employees with more artefacts
     * updates
     * @param vc, allows the method to access
	 * the class VCSystemClass so it can
	 * execute the commands
     * @param in, allows the method to
	 * use the scanner so it can read the
	 * user inputs
     */
	private static void getWorkaholics(VCSystem vc) {
		Iterator<User> it = vc.getWorkaholics();
		if(!it.hasNext()) {
			System.out.println(NO_WORKAHOLICS);
		}
		else {
			while(it.hasNext()) {
				User u = it.next();
				System.out.printf(WORKAHOLICS, u.getName(), u.getNumUpdates() ,u.getNumProjsAsDev(), u.getLastUpdateDone());
			}
		}
		
	}
	
	/**
	 * This method determines the two employees
	 * that have more projects in common
	 * @param vc, allows the method to access
	 * the class VCSystemClass so it can
	 * execute the commands
     * @param in, allows the method to
	 * use the scanner so it can read the
	 * user inputs
	 */
	private static void moreProjectsInCommon(VCSystem vc) {
		Iterator<Common> it = vc.getCommonUsers();
		if(!it.hasNext()) {
			System.out.println(NO_COMMON_PROJS);
		}
		else {
			while(it.hasNext()) {
				
			}
		}
	}
	
	/**
	 * This method shows the available commands
	 */
	private static void help() {
		System.out.println(AVAILABLE_COMM);
		for(Command c: Command.values()) {
			if(c != Command.UNKNOWN) {
				System.out.printf(FORMAT_HELP, c.comm, c.desc);
			}
		}
	}
	
	/**
	 * This method prints a message that warns
	 * the user that the command he wrote is unknown
	 */
	private static void unknownCom() {
		System.out.println(UNKNOWN_COMMAND);
	}
	
	/**
	 * This method terminates the execution of the
	 * program with the message: <code>Bye!</code>
	 */
	private static void exit() {
		System.out.println(BYE);
	}
	
	/**
	 * This method reads the commands,
	 * wrote by the user
	 * @param in, allows the method to
	 * use the scanner so it can read the
	 * user inputs
	 * @return the command, if it
	 * matches an existing on or
	 * the command <code>UNKNOWN</code>
	 * if not
	 */
	private static Command getCom(Scanner in) {
		String comm = in.next().toUpperCase();
		try {
			return Command.valueOf(comm);
		} catch (IllegalArgumentException e) {
			unknownCom();
			return Command.UNKNOWN;
		}
	}
}

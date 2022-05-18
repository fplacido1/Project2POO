import java.util.*;
import VCSystem.*;
import Exceptions.*;

public class Main {
	
	private static final String BYE = "Bye!";
	private static final String UNKNOWN_COMMAND = "Unknown command. Type help to see available commands.";
	private static final String FORMAT_HELP = "%s - %s\n";
	private static final String ADDED_DEV = "User %s was registered as software developer with clearance level %d.\n";
	private static final String ADDED_MNG = "User %s was registered as project manager with clearance level %d.\n";
	private static final String DEV = "developer";
	private static final String MNG = "manager";
	private static final String NO_USERS = "No users registered.";
	private static final String ALL_USERS = "All registered users:";
	private static final String DEV_REG = "developer %s is managed by %s [%d]\n";
	private static final String MNG_REG = "manager %s [%d ,%d , %d]\n";
	private static final String INHOUSE = "inhouse";
	private static final String OUTSRC = "outsourced";
	private static final String ADDED_TO_TEAM = "%s: added to team.\n";
	
	
	
	private enum Command{
		
		REGISTER("register","adds a new user"), USERS("users","lists all registered users"), CREATE("create","creates a new project"),
		PROJECTS("projects","lists all projects"), TEAM("team","adds team members to a project"),
		ARTEFACTS("artefacts","adds artefacts to a project"), PROJECT("project","shows detailed project information"),
		REVISION("revision","revises an artefact"), MANAGED("managed","lists developers of a manager"),
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


	private static void chooseCommand(Command command, Scanner in, VCSystem vc) {
		switch(command) {
		case REGISTER:        registerUser(vc, in);           break;
		case USERS:           in.nextLine(); getAllUsers(vc); break;//TODO
		case CREATE:          createNewProject(vc, in);       break;//TODO
		case PROJECTS:        getAllProjects(vc, in);         break;//TODO
		case TEAM:            addTeamMembers(vc, in);         break;//TODO
		case ARTEFACTS:       addArtefact(vc, in);            break;//TODO
		case PROJECT:         getInHouseDetails(vc, in);      break;//TODO
		case REVISION:        reviseArtefact(vc, in);         break;//TODO
		case MANAGED:         getAllManaged(vc, in);          break;//TODO
		case KEYWORD:         filterByKeyword(vc, in);        break;//TODO
		case CONFIDENTIALITY: filterByConfidentiality(vc, in);break;//TODO
		case WORKAHOLICS:     getWorkaholics(vc, in);         break;//TODO
		case COMMON:          moreProjectsInCommon(vc, in);   break;//TODO
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
			User u = vc.addManager(name, clearanceLvl);
			System.out.printf(ADDED_MNG, u.getName(), u.getClearanceLvl());
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
	private static void addDeveloper(String name, String mng, int clearanceLvl,VCSystem vc) {
		try {
			User u = vc.addDeveloper(name, mng, clearanceLvl);
			System.out.printf(ADDED_DEV, u.getName(), u.getClearanceLvl());
		}
		catch(UserAlreadyExistsException | ManagerDoesNotExistException e) {
			System.out.println(e.getMessage());
		}
	}
	
	//TODO: D�VIDA RELATIVA AO FACTO DE OS MANAGERS TAMBEM SEREM DEVS DE PROJETOS(getNumProjsAsDev).
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
							                 u.getNumProjs(), ((Manager) u).getNumProjsAsDev() );
				}
				else {
					System.out.printf(DEV_REG, u.getName(), ((Developer) u).getManager(), ((Developer) u).getNumProjs());
				}
			}
		}
	}

	
	
	
	private static void createNewProject(VCSystem vc, Scanner in) {
		String projMng = in.next();
		String type = in.next();
		String projName = in.nextLine();
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
			System.out.println(e.getMessage());
		}
		finally {
			in.nextLine();
		}	
	}


	private static void createOutSourced(VCSystem vc, String projMng, String projName, List<String> keyWords, Scanner in) {
		String companyName = in.nextLine().trim();
		vc.createNewOutSourcedProj(projMng, projName, keyWords, companyName);
	}


	private static void createInHouse(VCSystem vc, String projMng, String projName, List<String> keyWords, Scanner in) {
		int confLvl = in.nextInt();
		vc.createNewInHouseProj(projMng, projName, keyWords, confLvl);
	}

	
	
	

	private static void getAllProjects(VCSystem vc, Scanner in) {
		// TODO Auto-generated method stub
		
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
			for(int j = 0; j < numUsers; j++) {
				try {
					vc.addUserToProj(mngName, projectName, names.get(j));
					System.out.printf(ADDED_TO_TEAM, names.get(j));
				}
				catch (UserDoesNotExistException | AlreadyTeamMemberException | InsufficientClearanceLevelException e) {
					System.out.printf(e.getMessage(), names.get(j));
				}
			}
		}
		catch(ManagerDoesNotExistException | ProjectNameDoesNotExistsException | ProjectNotManagedByUserException e){
			System.out.println(e.getMessage());
		}
		in.nextLine();
	}



	private static void addArtefact(VCSystem vc, Scanner in) {
		// TODO Auto-generated method stub
		
	}


	private static void getInHouseDetails(VCSystem vc, Scanner in) {
		// TODO Auto-generated method stub
		
	}
	




	private static void reviseArtefact(VCSystem vc, Scanner in) {
		// TODO Auto-generated method stub
		
	}


	private static void getAllManaged(VCSystem vc, Scanner in) {
		// TODO Auto-generated method stub
		
	}


	private static void filterByKeyword(VCSystem vc, Scanner in) {
		// TODO Auto-generated method stub
		
	}



	private static void filterByConfidentiality(VCSystem vc, Scanner in) {
		// TODO Auto-generated method stub
		
	}


	private static void getWorkaholics(VCSystem vc, Scanner in) {
		// TODO Auto-generated method stub
		
	}
	


	private static void moreProjectsInCommon(VCSystem vc, Scanner in) {
		// TODO Auto-generated method stub
		
	}
	

	private static void help() {
		for(Command c: Command.values()) {
			if(c != Command.UNKNOWN) {
				System.out.printf(FORMAT_HELP, c.comm, c.desc);
			}
		}
	}
	
	private static void unknownCom() {
		System.out.println(UNKNOWN_COMMAND);
	}
	
	private static void exit() {
		System.out.println(BYE);
	}

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

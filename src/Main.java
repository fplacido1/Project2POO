import java.util.*;
import VCSystem.*;
import Exceptions.*;

import System.VCSystem;
import System.VCSystemClass;

public class Main {
	
	private static final String BYE = "Bye!";
	private static final String UNKNOWN_COMMAND = "Unknown command. Type help to see available commands.";
	private static final String FORMAT_HELP = "%s - %s\n";
	private static final String ADDED_USER = "User %s was registered as %s %s with clearance level %d.\n";
	private static final String DEV = "developer";
	private static final String MNG = "manager";
	private static final String NO_USERS = "No users registered.";
	private static final String INHOUSE = "inhouse";
	private static final String OUTSRC = "outsourced";
	
	
	
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
		case REGISTER: registerUser(vc, in); break;
		case USERS: in.nextLine(); getAllUsers(vc); break;
		case CREATE: createNewProject(vc, in); break;
		case PROJECTS: getAllProjects(vc, in); break;
		case TEAM: addTeamMembers(vc, in); break;
		case ARTEFACTS: addArtefact(vc, in); break;
		case PROJECT: getInHouseDetails(vc, in); break;
		case REVISION: reviseArtefact(vc, in); break;
		case MANAGED: getAllManaged(vc, in); break;
		case KEYWORD: filterByKeyword(vc, in); break;
		case CONFIDENTIALITY: filterByConfidentiality(vc, in); break;
		case WORKAHOLICS: getWorkaholics(vc, in); break;
		case COMMON: moreProjectsInCommon(vc, in); break;
		case HELP: help(); break;
		default: break;
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
		int clearanceLvl = in.nextInt();
		try {
			vc.addUser(jobPosition, name, clearanceLvl);
			if(jobPosition.equals(DEV)) {
				System.out.printf(ADDED_USER, name, DEV, jobPosition, clearanceLvl);
			}
			else {
				System.out.printf(ADDED_USER, name, MNG, jobPosition, clearanceLvl);
			}
		}
		catch(UnknowJobPositionException e) {
			System.out.println(e.getMessage());
		}
		catch(UserAlreadyExistsException | ProjectManagerDoesNotExistsException e) {
			System.out.printf(e.getMessage(), name);
		}
		finally {
			in.nextLine();
		}
	}
	
	
	//TODO dúvida try catch
	private static void getAllUsers(VCSystem vc) {
		if(vc.numUsers() == 0) {
			System.out.println(NO_USERS);
		}
		else {
			Iterator<Users> it = vc.getAllUsers();
		//TODO ciclo while
	}

	//TODO dúvida project Type
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
	
	}


	private static void getAllProjects(VCSystem vc, Scanner in) {
		// TODO Auto-generated method stub
		
	}


	private static void addTeamMembers(VCSystem vc, Scanner in) {
		// TODO Auto-generated method stub
		
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

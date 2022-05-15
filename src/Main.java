import java.util.*;

import System.VCSystem;
import System.VCSystemClass;

public class Main {
	
	private static final String BYE = "Bye!";
	private static final String UNKNOWN_COMMAND = "Unknown command. Type help to see available commands.";
	private static final String FORMAT_HELP = "%s - %s\n";
	
	
	
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
		
		private Command(String desc, String comm) {
			this.desc = desc;
			this.comm = comm;
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


	private static void chooseCommand(Main.Command command, Scanner in, VCSystem vc) {
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
		String comm = in.next();
		try {
			return Command.valueOf(comm);
		} catch (IllegalArgumentException e) {
			unknownCom();
			return Command.UNKNOWN;
		}
	}
}

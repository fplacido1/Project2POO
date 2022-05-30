package VCSystem;
import java.util.*;

/**
 * 
 * @author João Norberto (62685) & Francisco Plácido (62674)
 * 
 * Date of last update: 30 of may of 2022
 */
public class ManagerClass extends AbstractUser implements Manager {

	/**
	 * TreeMap of all the managed users
	 */
	private Map<String, User> managedUsers;
	
	/**
	 * HashMap of all the managed projects
	 */
	private Map<String, Projects> managedProjs;
	
	public ManagerClass(String name, int clearanceLvl) {
		super(name, clearanceLvl);
		managedUsers = new TreeMap<>();
		managedProjs = new HashMap<>();
	}

	@Override
	public int getNumManagedDevs() {
		return managedUsers.size();
	}

	@Override
	public void addManagedDev(Developer d) {
		managedUsers.put(d.getName(), d);
	}

	@Override
	public int getNumManagedProjs() {
		return managedProjs.size();
	}

	@Override
	public void addToManagedProjs(Projects proj) {
		managedProjs.put(proj.getProjName(), proj);
	}

	@Override
	public Iterator<User> getAllUsers() {
		return managedUsers.values().iterator();
	}

}

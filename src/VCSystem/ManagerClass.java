package VCSystem;
import java.util.*;

public class ManagerClass extends AbstractUser implements Manager {

	private Map<String, User> managedUsers;
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
	public void addManagedDev(String name, Developer d) {
		managedUsers.put(name, d);
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

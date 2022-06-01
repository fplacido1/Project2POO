package VCSystem;
import java.util.*;

/**
 * 
 * @author Joao Norberto (62685) & Francisco Placido (62674)
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

	@Override
	public int getManagedProjsCommon(User u2) {
		int projsCommon = 0;
		Iterator<Projects> it = managedProjs.values().iterator();
		while(it.hasNext()) {
			String name = it.next().getProjName();
			Iterator<InHouse> projs2 = u2.getAllProjs();
			while(projs2.hasNext()) {
				String name2 = projs2.next().getProjName();
				if(name.equals(name2)) {
					projsCommon++;
				}
			}
		}
		return projsCommon;
	}

}

package VCSystem;
import java.util.*;

public class ManagerClass extends AbstractUser implements Manager {

	private Map<String, Developer> managedUsers;
	
	public ManagerClass(String name, int clearanceLvl) {
		super(name, clearanceLvl);
		managedUsers = new HashMap<>();
	}

	@Override
	public int getNumProjsAsDev() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNumManagedDevs() {
		return managedUsers.size();
	}

	@Override
	public void addManagedDev(String name, Developer d) {
		managedUsers.put(name, d);
	}

}

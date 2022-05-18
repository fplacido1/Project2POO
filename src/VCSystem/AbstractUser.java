package VCSystem;
import java.util.*;

public abstract class AbstractUser implements User {

	private Map<String, Projects> projects;
	private int clearanceLvl;
	private String name;
	
	public AbstractUser(String name, int clearanceLvl) {
		this.name = name;
		this.clearanceLvl = clearanceLvl;
		projects = new HashMap<>();
	}
	
	public String getName() {
		return name;
	}
	
	public int getClearanceLvl() {
		return clearanceLvl;
	}
	
	public int getNumProjs() {
		return projects.size();
	}
}

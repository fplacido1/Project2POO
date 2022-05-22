package VCSystem;
import java.util.*;

public abstract class AbstractUser implements User {

	protected Map<String, InHouse> projects;
	private int numArtRevised;
	private int clearanceLvl;
	private String name;
	
	public AbstractUser(String name, int clearanceLvl) {
		this.name = name;
		this.clearanceLvl = clearanceLvl;
		projects = new HashMap<>();
		numArtRevised = 0;
	}
	
	public String getName() {
		return name;
	}
	
	public int getClearanceLvl() {
		return clearanceLvl;
	}
	
	public int getNumProjsAsDev() {
		return projects.size();
	}
	
	public void addProj(InHouse p) {
		projects.put(p.getProjName(), p);
	}
	
	public void incArtefactsRevised() {
		numArtRevised++;
	}

}

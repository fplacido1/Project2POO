package VCSystem;
import java.util.*;

public abstract class AbstractUser implements User {

	protected Map<String, InHouse> projects;
	private SortedSet<Revision> revisions;
	private int numArtRevised;
	private int clearanceLvl;
	private String name;
	
	public AbstractUser(String name, int clearanceLvl) {
		this.name = name;
		this.clearanceLvl = clearanceLvl;
		projects = new HashMap<>();
		ComparatorRevision c1 = new ComparatorRevision();
		revisions = new TreeSet<>(c1);
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
	
	public void addArtefactRevised(Revision r) {
		revisions.add(r);
		numArtRevised++;
	}
	
	public Iterator<Revision> getAllRevisions() {
		return revisions.iterator();
	}

}

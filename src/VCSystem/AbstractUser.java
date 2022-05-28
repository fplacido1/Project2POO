package VCSystem;
import java.time.LocalDate;
import java.util.*;

public abstract class AbstractUser implements User {

	protected Map<String, InHouse> projects;
	private List<Revision> revisions;
	private int numArtRevised;
	private int clearanceLvl;
	private String name;
	
	public AbstractUser(String name, int clearanceLvl) {
		this.name = name;
		this.clearanceLvl = clearanceLvl;
		projects = new HashMap<>();
		revisions = new ArrayList<>();
		numArtRevised = 0;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public int getClearanceLvl() {
		return clearanceLvl;
	}
	
	@Override
	public int getNumProjsAsDev() {
		return projects.size();
	}
	
	@Override
	public void addProj(InHouse p) {
		projects.put(p.getProjName(), p);
	}
	
	@Override
	public void addArtefactRevised(Revision r) {
		revisions.add(r);
		numArtRevised++;
	}
	
	@Override
	public Iterator<Revision> getAllRevisions() {
		ComparatorRevision c1 = new ComparatorRevision();
		SortedSet<Revision> rev = new TreeSet<>(c1);
		for(int i = 0; i < revisions.size(); i++) {
			rev.add(revisions.get(i));
		}
		return rev.iterator();
	}
	
	@Override
	public int getNumUpdates() {
		return numArtRevised;
	}
	
	@Override
	public LocalDate getLastUpdateDone() {
		Revision r = revisions.get(0);
		return r.getDate();
	}
	
	@Override
	public int compareTo(User other) {
		int result = getNumProjsAsDev() - other.getNumProjsAsDev(); //TODO DUVIDA
		if(result != 0) {
			return result;
		}
		else {
			result = getLastUpdateDone().compareTo(other.getLastUpdateDone());
			if(result != 0) {
				return result;
			}
			else {
				result = name.compareTo(other.getName());
			}
		}
		return result;
	}

}

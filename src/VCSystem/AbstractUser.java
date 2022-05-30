package VCSystem;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public abstract class AbstractUser implements User {

	private Map<String, InHouse> projects;
	private List<Revision> revisions;
	private LocalDate lastUpdateDone;
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
	public int getNumProjs() {
		if(this instanceof Manager) {
			return projects.size() + ((Manager) this).getNumManagedProjs();
		}
		return projects.size();
	}
	
	@Override
	public void addProj(InHouse p) {
		projects.put(p.getProjName(), p);
	}
	
	@Override
	public void addArtefactRevised(Revision r) {
		revisions.add(r);
		if(lastUpdateDone == null) {
			lastUpdateDone = r.getDate();
		}
		else if(r.getDate().compareTo(lastUpdateDone) > 0) {
			lastUpdateDone = r.getDate();
		}
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
		return lastUpdateDone;
	}
	
	@Override
	public int compareTo(User other) {
		int result = numArtRevised - other.getNumUpdates();
		if(result != 0) {
			return result;
		}
		else {
			result = getNumProjs() - other.getNumProjs();
			if(result != 0) {
				return result;
			}
			else {
				result = (int)ChronoUnit.DAYS.between(other.getLastUpdateDone(), lastUpdateDone);
				if(result != 0) {
					return result;
				}
				else {
					result = other.getName().compareTo(name);
				}
			}
		}
		return result;
	}
	
	public Iterator<InHouse> getAllProjs(){
		return projects.values().iterator();
	}
	
	public int getProjsInCommon(User u2) {
		int projsInCommon = 0;
		Iterator<InHouse> projs1 = projects.values().iterator();
		while(projs1.hasNext()) {
			String name = projs1.next().getProjName();
			Iterator<InHouse> projs2 = u2.getAllProjs();
			while(projs2.hasNext()) {
				String name2 = projs2.next().getProjName();
				if(name.equals(name2)) {
					projsInCommon++;
				}
			}
		}
		return projsInCommon;
	}

}

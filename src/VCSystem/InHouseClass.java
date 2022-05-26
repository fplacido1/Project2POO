package VCSystem;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import VCSystem.exceptions.*;

public class InHouseClass extends AbstractProject implements InHouse{
	
	private Map<String, Artefacts> artefacts;
	private int confLvl;
	private int numRevisions;
    private Map<String, User> devs;

	
	public InHouseClass(Manager mng, String projName, int confLvl, List<String> keywords) {
		super(mng, projName, keywords);
		this.confLvl = confLvl;
		artefacts = new HashMap<>();
		numRevisions = 0;
		this.devs = new LinkedHashMap<>();
	}

	@Override
	public int getConfLvl() {
		return confLvl;
	}

	@Override
	public void addArtefact(Artefacts e) throws ArtefactAlreadyInProjectException, ExceedsProjectConfidentialityLevelException{
		if(artefacts.containsValue(e)) {
			throw new ArtefactAlreadyInProjectException(e.getName());
		}
		else if(confLvl < e.getConfidentialityLevel()) {
			throw new ExceedsProjectConfidentialityLevelException(e.getName());
		}
		else {
			artefacts.put(e.getName(), e);
		}
	}

	@Override
	public int getNumArtefacts() {
		return artefacts.size();
	}

	@Override
	public int getNumRevisions() {
		return numRevisions;
	}

	@Override
	public LocalTime getLastRevisionDate() {
		return null;
	}
	
	@Override
	public int getNumDevs() {
		return devs.size();
	}
	
	@Override
	public void addUser(User u) throws AlreadyTeamMemberException, InsufficientClearanceLevelException {
		if(devs.containsKey(u.getName()) || u.getName().equals(mng.getName())) {
			throw new AlreadyTeamMemberException(u.getName());
		}
		else if(u.getClearanceLvl() < confLvl) {
			throw new InsufficientClearanceLevelException(u.getName());
		}
		else {
			devs.put(u.getName(), u);
		}
	}

	@Override
	public Iterator<User> getAllUsers() {
		return devs.values().iterator();
	}

	@Override
	public Iterator<Artefacts> getAllArtefacts() {
		ComparatorArtefact c1 = new ComparatorArtefact();
		SortedSet<Artefacts> sortedArts = new TreeSet<>(c1);
		for(Artefacts a : artefacts.values()) {
			sortedArts.add(a);
		}
		return sortedArts.iterator();
	}

	@Override
	public Revision reviseArtefact(User u, String artefactName, LocalDate revisionDate, String comment)
			throws ArtefactDoesNotExistsException, UserDoesNotBelongToTeamException {
		Artefacts a = artefacts.get(artefactName);
		if(a == null) {
			throw new ArtefactDoesNotExistsException();
		}
		else if(!devs.containsValue(u) && !u.getName().equals(mng.getName())) {
			throw new UserDoesNotBelongToTeamException(u.getName(), projName);
		}
		else {
			numRevisions++;
			Revision r = new RevisionClass(u, a, revisionDate, comment, numRevisions, projName);
			a.revise(r);
			return r;
		}
	}

	@Override
	public boolean containsUser(String user) {
		return devs.containsKey(user) || user.equals(mng.getName());
	}
		
	


}

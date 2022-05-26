package VCSystem;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import VCSystem.exceptions.*;

public class InHouseClass extends AbstractProject implements InHouse{
	
	private List<Artefacts> artefacts;
	private int confLvl;
	private int numRevisions;
    private Map<String, User> devs;

	
	public InHouseClass(Manager mng, String projName, int confLvl, List<String> keywords) {
		super(mng, projName, keywords);
		this.confLvl = confLvl;
		artefacts = new ArrayList<>();
		numRevisions = 0;
		this.devs = new LinkedHashMap<>();
	}

	@Override
	public int getConfLvl() {
		return confLvl;
	}

	@Override
	public void addArtefact(Artefacts e) throws ArtefactAlreadyInProjectException, ExceedsProjectConfidentialityLevelException{
		if(getArtefactIndex(e.getName()) != -1) {
			throw new ArtefactAlreadyInProjectException(e.getName());
		}
		else if(confLvl < e.getConfidentialityLevel()) {
			throw new ExceedsProjectConfidentialityLevelException(e.getName());
		}
		else {
			artefacts.add(e);
		}
	}

	@Override
	public boolean containsArtefact(Artefacts e) {
		return artefacts.contains(e);
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
		artefacts.sort(c1);
		return artefacts.iterator();
	}

	@Override
	public Revision reviseArtefact(User u, String artefactName, LocalDate revisionDate, String comment)
			throws ArtefactDoesNotExistsException, UserDoesNotBelongToTeamException {
		int artefactIndex = getArtefactIndex(artefactName);
		if(artefactIndex == -1) {
			throw new ArtefactDoesNotExistsException();
		}
		else if(!devs.containsValue(u) && !u.getName().equals(mng.getName())) {
			throw new UserDoesNotBelongToTeamException(u.getName(), projName);
		}
		else {
			numRevisions++;
			Artefacts a = artefacts.get(artefactIndex);
			Revision r = new RevisionClass(u, a, revisionDate, comment, numRevisions, projName);
			a.revise(r);
			return r;
		}
	}

	private int getArtefactIndex(String artefactName) {
		int index = -1;
		for(int i = 0; i < artefacts.size() && index != -1; i++) {
			if(artefacts.get(i).getName().equals(artefactName)) {
				index = i;
			}
		}
		return index;
	}

	@Override
	public boolean containsUser(String user) {
		return devs.containsKey(user) || user.equals(mng.getName());
	}
		
	


}

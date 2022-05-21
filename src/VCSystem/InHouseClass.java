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
		artefacts = new TreeMap<>();
		numRevisions = 0;
		this.devs = new HashMap<>();
	}

	@Override
	public int getConfLvl() {
		return confLvl;
	}

	@Override
	public void addArtefact(Artefacts e) {
		artefacts.put(e.getName(), e);
	}

	@Override
	public boolean containsArtefact(Artefacts e) {
		return artefacts.containsValue(e);
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
		if(devs.containsKey(u.getName())) {
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
		return artefacts.values().iterator();
	}

	@Override
	public Revision reviseArtefact(User u, String artefactName, LocalDate revisionDate, String comment)
			throws ArtefactDoesNotExistsException, UserDoesNotBelongToTeamException {
		Artefacts a = artefacts.get(artefactName);
		if(a == null) {
			throw new ArtefactDoesNotExistsException();
		}
		else if(!devs.containsValue(u)) {
			throw new UserDoesNotBelongToTeamException(u.getName(), projName);
		}
		else {
			numRevisions++;
			Revision r = new RevisionClass(u, a, revisionDate, comment, numRevisions);
			a.revise(r);
			return r;
		}
	}
		
	


}

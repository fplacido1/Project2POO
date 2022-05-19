package VCSystem;

import java.time.LocalTime;
import java.util.*;

public class InHouseClass extends AbstractProject implements InHouse{
	
	private List<Artefacts> artefacts;
	private int confLvl;
	private int numRevisions;
	
	public InHouseClass(Manager mng, String projName, int confLvl, List<String> keywords) {
		super(mng, projName, keywords);
		this.confLvl = confLvl;
		artefacts = new LinkedList<>();
		numRevisions = 0;
	}

	@Override
	public int getConfLvl() {
		return confLvl;
	}

	@Override
	public void addArtefact(Artefacts e) {
		artefacts.add(e);
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
	
	


}

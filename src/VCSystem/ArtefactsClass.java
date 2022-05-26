package VCSystem;

import java.time.LocalDate;
import java.util.*;

public class ArtefactsClass implements Artefacts {
	
	private List<Revision> revisions;
	private String name;
	private int confidentialityLevel;
	private String description;
	private LocalDate revisionDate;
	
	public ArtefactsClass(String name, int confidentialityLevel, String description, LocalDate date) {
		this.name = name;
		this.confidentialityLevel = confidentialityLevel;
		this.description = description;
		revisionDate = date;
		revisions = new LinkedList<>();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getConfidentialityLevel() {
		return confidentialityLevel;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public Iterator<Revision> getAllRevision() {
		return revisions.iterator();
	}

	@Override
	public LocalDate getLastRevDate() {
		return revisionDate;
	}

	@Override
	public void revise(Revision r) {
		revisions.add(0, r);
		revisionDate = r.getDate();
	}
}

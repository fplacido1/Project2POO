package VCSystem;

import java.time.LocalDate;
import java.util.*;

/**
 * 
 * @author Joao Norberto (62685) & Francisco Placido (62674)
 * 
 * Date of last update: 30 of may of 2022
 */
public class ArtefactsClass implements Artefacts {
	
	/**
	 * Array of all the revisions done to the artefact
	 */
	private List<Revision> revisions;
	
	/**
	 * Name of the artefact
	 */
	private String name;
	
	/**
	 * Confidentiality level of the artefact
	 */
	private int confidentialityLevel;
	
	/**
	 * Description of the artefact
	 */
	private String description;
	
	/**
	 * Date of the last update done to the artefact
	 */
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

	@Override
	public int getNumRevised() {
		return revisions.size();
	}
}

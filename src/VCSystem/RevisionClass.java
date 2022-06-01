package VCSystem;

import java.time.LocalDate;

/**
 * 
 * @author Joao Norberto (62685) & Francisco Placido (62674)
 * 
 * Date of last update: 30 of may of 2022
 */
public class RevisionClass implements Revision {
	
	/**
	 * Author of the revision
	 */
	private User author;
	
	/**
	 * Artefact of the revision
	 */
	private Artefacts artefact;
	
	/**
	 * Date of the revision
	 */
	private LocalDate revisionDate;
	
	/**
	 * Comment of the revision
	 */
	private String comment;
	
	/**
	 * Name of the project were the revision
	 * was done
	 */
	private String projName;
	
	/**
	 * Number of the revision
	 */
	private int revisionNum;

	public RevisionClass(User u, Artefacts a, LocalDate revisionDate, String comment, int numRevisions, String projName) {
		author = u;
		artefact = a;
		this.revisionDate = revisionDate;
		this.comment = comment;
		revisionNum = numRevisions;
		this.projName = projName;
	}

	@Override
	public int getNum() {
		return revisionNum;
	}

	@Override
	public User getAuthor() {
		return author;
	}

	@Override
	public LocalDate getDate() {
		return revisionDate;
	}

	@Override
	public String getComment() {
		return comment;
	}

	@Override
	public Artefacts getArtefact() {
		return artefact;
	}

	@Override
	public String getProjName() {
		return projName;
	}
}

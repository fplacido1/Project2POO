package VCSystem;

import java.time.LocalDate;

public class RevisionClass implements Revision {
	
	private User author;
	private Artefacts artefact;
	private LocalDate revisionDate;
	private String comment;
	private String projName;
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
	public String getAuthor() {
		return author.getName();
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

package VCSystem;

import java.time.LocalDate;

public interface Revision {

	/**
	 * This method returns the number of
	 * the revision
	 * @return
	 */
	int getNum();

	/**
	 * This method returns the author
	 * of the revision
	 * @return author of the revision
	 */
	User getAuthor();

	/**
	 * This method returns the date
	 * of the revision
	 * @return date of the revision
	 */
	LocalDate getDate();

	/**
	 * This method returns the comment
	 * from the revision
	 * @return comment of the revision
	 */
	String getComment();

	/**
	 * This method returns the artefact
	 * where the revision was done
	 * @return artefact of the revision
	 */
	Artefacts getArtefact();

	/**
	 * This method returns the name of the
	 * project the artefact revision was
	 * done
	 * @return name of the project the revision
	 * was done
	 */
	String getProjName();
}

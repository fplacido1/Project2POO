package VCSystem;

import java.time.LocalDate;
import java.util.Iterator;

/**
 * 
 * @author João Norberto (62685) & Francisco Plácido (62674)
 * 
 * Date of last update: 30 of may of 2022
 */
public interface Artefacts {
	
	/**
	 * This method returns the name
	 * of the artefact
	 * @return name of the artefact
	 */
	String getName();
	
	/**
	 * This method returns the confidentiality
	 * level of the artefact
	 * @return
	 */
	int getConfidentialityLevel();
	
	/**
	 * This method returns the description
	 * of the artefact
	 * @return description of the artefact
	 */
	String getDescription();
	
	/**
	 * This method returns all the revisions
	 * done to the artefact
	 * @return iterator of all the revisions
	 * done to the artefact
	 */
	Iterator<Revision> getAllRevision();

	/**
	 * This method returns the date of
	 * the last update done to the artefact
	 * @return date of the last update
	 */
	LocalDate getLastRevDate();

	/**
	 * This method adds a revision to
	 * an artefact
	 * @param r , revision to add
	 */
	void revise(Revision r);

	/**
	 * This method returns the number
	 * of revisions done to the artefact
	 * @return number of revisions done
	 */
	int getNumRevised();

}

package VCSystem;

import java.time.LocalDate;
import java.util.Iterator;

public interface Artefacts extends Comparable<Artefacts> {
	
	String getName();
	
	int getConfidentialityLevel();
	
	String getDescription();
	
	Iterator<Revision> getAllRevision();

	LocalDate getLastRevDate();

	void revise(Revision r);

}

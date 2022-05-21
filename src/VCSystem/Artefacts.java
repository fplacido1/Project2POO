package VCSystem;

import java.time.LocalDate;
import java.util.Iterator;

public interface Artefacts extends Comparable<Artefacts> {
	
	public String getName();
	
	public int getConfidentialityLevel();
	
	public String getDescription();
	
	public Iterator<Revision> getAllRevision();

	public LocalDate getLastRevDate();

}

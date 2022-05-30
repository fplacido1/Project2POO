package VCSystem;

import java.time.LocalDate;
import java.util.Iterator;

public interface User extends Comparable<User> {

	String getName();

	int getClearanceLvl();
	
	int getNumProjsAsDev();

	void addProj(InHouse p);

	void addArtefactRevised(Revision r);

	Iterator<Revision> getAllRevisions();
	
	int getNumUpdates();
	
	LocalDate getLastUpdateDone();

	int getProjsInCommon(User u2);

}

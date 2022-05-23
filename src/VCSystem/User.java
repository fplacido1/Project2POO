package VCSystem;

import java.util.Iterator;

public interface User {

	String getName();

	int getClearanceLvl();
	
	int getNumProjsAsDev();

	void addProj(InHouse p);

	void addArtefactRevised(Revision r);

	Iterator<Revision> getAllRevisions();

}

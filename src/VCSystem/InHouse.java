package VCSystem;
import java.time.*;
import java.util.Iterator;

import VCSystem.exceptions.*;

public interface InHouse extends Projects {
	
	int getConfLvl();
	
	void addArtefact(Artefacts e) throws ArtefactAlreadyInProjectException, ExceedsProjectConfidentialityLevelException;
		
	int getNumArtefacts();
	
	int getNumRevisions();

	LocalDate getLastRevisionDate();
	
	int getNumDevs();

	void addUser(User u) throws AlreadyTeamMemberException, InsufficientClearanceLevelException;

	Iterator<User> getAllUsers();

	Iterator<Artefacts> getAllArtefacts();

	Revision reviseArtefact(User u, String artefactName, LocalDate revisionDate, String comment);

	boolean containsUser(String user);
	
	boolean containsArtefact(String artefactName);

}

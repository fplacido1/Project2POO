package VCSystem;
import java.time.*;
import java.util.Iterator;

import VCSystem.exceptions.*;

public interface InHouse extends Projects {
	
	int getConfLvl();
	
	void addArtefact(Artefacts e);
	
	boolean containsArtefact(Artefacts e);
	
	int getNumArtefacts();
	
	int getNumRevisions();

	LocalTime getLastRevisionDate();
	
	int getNumDevs();

	void addUser(User u) throws AlreadyTeamMemberException, InsufficientClearanceLevelException;

	Iterator<User> getAllUsers();

	Iterator<Artefacts> getAllArtefacts();

}

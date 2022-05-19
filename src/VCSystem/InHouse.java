package VCSystem;
import java.time.*;
import VCSystem.exceptions.*;

public interface InHouse {
	
	int getConfLvl();
	
	void addArtefact(Artefacts e);
	
	boolean containsArtefact(Artefacts e);
	
	int getNumArtefacts();
	
	int getNumRevisions();

	LocalTime getLastRevisionDate();
	
	int getNumDevs();

	void addUser(User u) throws AlreadyTeamMemberException, InsufficientClearanceLevelException;

}

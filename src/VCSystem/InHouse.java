package VCSystem;

public interface InHouse {
	
	int getConfLvl();
	
	void addArtefact(Artefacts e);
	
	boolean containsArtefact(Artefacts e);
	
	int getNumArtefacts();
	
	int getNumRevisions();

}

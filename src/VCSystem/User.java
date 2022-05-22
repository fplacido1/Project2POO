package VCSystem;

public interface User {

	String getName();

	int getClearanceLvl();
	
	int getNumProjsAsDev();

	void addProj(InHouse p);

	void incArtefactsRevised();

}

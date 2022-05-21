package VCSystem;

public interface User {

	String getName();

	int getClearanceLvl();
	
	int getNumProjs();

	void addProj(InHouse p);

	void incArtefactsRevised();

}

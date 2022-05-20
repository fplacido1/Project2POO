package VCSystem;

public interface Manager extends User {

	int getNumProjsAsDev();

	int getNumManagedDevs();
	
	int getNumManagedProjs();

	void addManagedDev(String name,Developer d);

	void addToManagedProjs(Projects proj);

}

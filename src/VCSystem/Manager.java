package VCSystem;

public interface Manager extends User {

	int getNumManagedDevs();
	
	int getNumManagedProjs();

	void addManagedDev(String name,Developer d);

	void addToManagedProjs(Projects proj);

}

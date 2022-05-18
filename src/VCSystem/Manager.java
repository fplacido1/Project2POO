package VCSystem;

public interface Manager extends User {

	int getNumProjsAsDev();

	int getNumManagedDevs();

	void addManagedDev(String name,Developer d);

}

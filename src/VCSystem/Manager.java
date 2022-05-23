package VCSystem;

import java.util.Iterator;

public interface Manager extends User {

	int getNumManagedDevs();
	
	int getNumManagedProjs();

	void addManagedDev(String name,Developer d);

	void addToManagedProjs(Projects proj);

	Iterator<User> getAllUsers();

}

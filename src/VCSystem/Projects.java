package VCSystem;

import java.util.Iterator;

public interface Projects {
	
	String getProjName();
	
	Manager getManager();
	
	Iterator<String> getKeyWords();
}

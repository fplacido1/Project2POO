package VCSystem;

import java.util.*;

public abstract class AbstractProject implements Projects {
	
	private Manager mng;
    private String projName;
    private List<String> keyWords;
    private Map<String, User> devs;

	public AbstractProject(Manager mng, String projName, List<String> keyWords) {
		this.mng = mng;
		this.projName = projName;
		this.keyWords = keyWords;
		this.devs = new HashMap<>();
	}
	
	public String getProjName() {
		return projName;
	}
	
	public Manager getManager() {
		return mng;
	}
	
	public int getNumDevs() {
		return devs.size();
	}
	
}

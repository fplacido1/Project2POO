package VCSystem;

import java.util.List;

public abstract class AbstractProject implements Projects {
	
	private Manager mng;
    private String projName;
    private List<String> keyWords;

	public AbstractProject(Manager mng, String projName, List<String> keyWords) {
		this.mng = mng;
		this.projName = projName;
		this.keyWords = keyWords;
	}
	
}

package VCSystem;
import java.util.*;

public abstract class AbstractProject implements Projects {
	
	protected Manager mng;
    protected String projName;
    private List<String> keyWords;

	public AbstractProject(Manager mng, String projName, List<String> keyWords) {
		this.mng = mng;
		this.projName = projName;
		this.keyWords = keyWords;
	}
	
	public String getProjName() {
		return projName;
	}
	
	public Manager getManager() {
		return mng;
	}
}

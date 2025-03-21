package VCSystem;
import java.util.*;

/**
 * 
 * @author Joao Norberto (62685) & Francisco Placido (62674)
 * 
 * Date of last update: 30 of may of 2022
 */
public abstract class AbstractProject implements Projects {
	
	/**
	 * Manager of the project
	 */
	private Manager mng;
	
	/**
	 * Name of the project
	 */
    private String projName;
    
    /**
     * List of keywords from the project
     */
    private List<String> keyWords;

	public AbstractProject(Manager mng, String projName, List<String> keyWords) {
		this.mng = mng;
		this.projName = projName;
		this.keyWords = keyWords;
	}
	
	@Override
	public String getProjName() {
		return projName;
	}
	
	@Override
	public Manager getManager() {
		return mng;
	}
	
	@Override
	public Iterator<String> getKeyWords(){
		return keyWords.iterator();
	}
	
	
}

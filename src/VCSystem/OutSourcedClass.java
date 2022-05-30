package VCSystem;

import java.util.*;

/**
 * 
 * @author João Norberto (62685) & Francisco Plácido (62674)
 * 
 * Date of last update: 30 of may of 2022
 */
public class OutSourcedClass extends AbstractProject implements OutSourced{
	
	/**
	 * Name of the company
	 */
	private String companyName;

	
	public OutSourcedClass(Manager mng, String projName, String companyName, List<String> keyWords) {
		super(mng, projName, keyWords);
		this.companyName = companyName;
	}

	@Override
	public String getCompany() {
		return companyName;
	}

}

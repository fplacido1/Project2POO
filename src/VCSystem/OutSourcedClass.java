package VCSystem;

import java.util.List;

public class OutSourcedClass extends AbstractProject implements OutSourced{
	
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

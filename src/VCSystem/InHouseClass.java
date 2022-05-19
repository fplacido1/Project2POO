package VCSystem;

import java.util.*;

public class InHouseClass extends AbstractProject implements InHouse{
	
	private List<Artefacts> artefacts;
	private int confLvl;
	
	public InHouseClass(Manager mng, String projName, int confLvl, List<String> keywords) {
		super(mng, projName, keywords);
		this.confLvl = confLvl;
		artefacts = new LinkedList<>();
	}

}

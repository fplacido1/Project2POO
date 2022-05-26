package VCSystem;

import java.util.Comparator;

public class ComparatorArtefact implements Comparator<Artefacts> {

	@Override
	public int compare(Artefacts o1, Artefacts o2) {
		int result = o1.getLastRevDate().compareTo(o2.getLastRevDate());
		if(result != 0) {
			return result;
		}
		else {
			result = o1.getName().compareTo(o2.getName());
		}
		return result;
	}

}

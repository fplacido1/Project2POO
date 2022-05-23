package VCSystem;

import java.util.Comparator;

public class ComparatorRevision implements Comparator<Revision> {

	@Override
	public int compare(Revision o1, Revision o2) {
		int result = o1.getDate().compareTo(o2.getDate());
		if(result != 0) {
			return result;
		}
		else {
			result = o1.getNum() - o2.getNum();
			if(result != 0) {
				return result;
			}
			else {
				result = o1.getProjName().compareTo(o2.getProjName());
			}
		}
		return result;
	}

}

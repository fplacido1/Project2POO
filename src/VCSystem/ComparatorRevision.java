package VCSystem;

import java.time.temporal.ChronoUnit;
import java.util.Comparator;

public class ComparatorRevision implements Comparator<Revision> {

	@Override
	public int compare(Revision o1, Revision o2) {
		int result = (int)ChronoUnit.DAYS.between(o1.getDate(), o2.getDate());
		if(result != 0) {
			return result;
		}
		else {
			result = o2.getNum() - o1.getNum();
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

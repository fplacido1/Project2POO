package VCSystem;

import java.time.temporal.ChronoUnit;
import java.util.Comparator;

/**
 * 
 * @author João Norberto (62685) & Francisco Plácido (62674)
 * 
 * Date of last update: 30 of may of 2022
 */
public class ComparatorProjectInHouse implements Comparator<InHouse> {

	@Override
	public int compare(InHouse o1, InHouse o2) {
		int result = (int)ChronoUnit.DAYS.between(o1.getLastRevisionDate(), o2.getLastRevisionDate());
		if(result != 0) {
			return result;
		}
		else {
			result = o1.getNumRevisions() - o2.getNumRevisions();
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

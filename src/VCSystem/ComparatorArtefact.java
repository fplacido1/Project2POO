package VCSystem;

import java.util.*;
import java.time.temporal.ChronoUnit;

/**
 * 
 * @author João Norberto (62685) & Francisco Plácido (62674)
 * 
 * Date of last update: 30 of may of 2022
 */
public class ComparatorArtefact implements Comparator<Artefacts> {

	@Override
	public int compare(Artefacts o1, Artefacts o2) {
		int result =(int)ChronoUnit.DAYS.between(o1.getLastRevDate(), o2.getLastRevDate());
		if(result != 0) {
			return result;
		}
		else {
			result = o1.getName().compareTo(o2.getName());
		}
		return result;
	}

}

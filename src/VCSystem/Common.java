package VCSystem;

public interface Common extends Comparable<Common> {

	/**
	 * This method returns the number
	 * of projects in common between the
	 * first and second user
	 * @return number of projects in common
	 */
	int getNumProjsInCommon();
	
	/**
	 * This method returns the first
	 * user
	 * @return first user
	 */
	User getFirstUser();
	
	/**
	 * This method returns the second
	 * user
	 * @return second user
	 */
	User getSecondUser();
	
}

package VCSystem;

/**
 * 
 * @author Joao Norberto (62685) & Francisco Placido (62674)
 * 
 * Date of last update: 30 of may of 2022
 */
public class CommonClass implements Common {

	/**
	 * First user
	 */
	private User firstUser;
	
	/**
	 * Second user
	 */
	private User secondUser;
	
	/**
	 * Number of projects in common between the users
	 */
	private int numProjsInCommon;
	
	public CommonClass(User u1, User u2, int numProjsInCommon) {
		firstUser = u1;
		secondUser = u2;
		this.numProjsInCommon = numProjsInCommon;
	}

	@Override
	public int getNumProjsInCommon() {
		return numProjsInCommon;
	}

	@Override
	public User getFirstUser() {
		return firstUser;
	}

	@Override
	public User getSecondUser() {
		return secondUser;
	}

	@Override
	public int compareTo(Common o) {
		int result = o.getFirstUser().getName().compareTo(firstUser.getName());
		if(result != 0) {
			return result;
		}
		else {
			result = o.getSecondUser().getName().compareTo(secondUser.getName());
		}
		return result;
	}
}

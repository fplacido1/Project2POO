package VCSystem;

public class CommonClass implements Common {

	private User firstUser;
	private User secondUser;
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

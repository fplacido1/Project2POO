package VCSystem;

public class DeveloperClass extends AbstractUser implements Developer {
	
	private String mng;
	
	public DeveloperClass(String name, String mng, int clearanceLvl) {
		super(name, clearanceLvl);
		this.mng = mng;
	}

	@Override
	public String getManager() {
		return mng;
	}
}

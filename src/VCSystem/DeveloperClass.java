package VCSystem;

public class DeveloperClass extends AbstractUser implements Developer {
	
	private Manager mng;
	
	public DeveloperClass(String name, Manager m, int clearanceLvl) {
		super(name, clearanceLvl);
		mng = m;
	}

	@Override
	public String getManager() {
		return mng.getName();
	}
}

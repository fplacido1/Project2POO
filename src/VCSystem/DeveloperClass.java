package VCSystem;

/**
 * 
 * @author João Norberto (62685) & Francisco Plácido (62674)
 * 
 * Date of last update: 30 of may of 2022
 */
public class DeveloperClass extends AbstractUser implements Developer {
	
	/**
	 * Manager of the developer
	 */
	private Manager mng;
	
	public DeveloperClass(String name, Manager m, int clearanceLvl) {
		super(name, clearanceLvl);
		mng = m;
	}

	@Override
	public Manager getManager() {
		return mng;
	}
}

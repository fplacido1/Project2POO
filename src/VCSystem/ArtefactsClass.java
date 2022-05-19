package VCSystem;

public class ArtefactsClass implements Artefacts{
	
	private String name;
	private int confidentialityLevel;
	private String description;
	
	public ArtefactsClass(String name, int confidentialityLevel, String description) {
		this.name = name;
		this.confidentialityLevel = confidentialityLevel;
		this.description = description;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getConfidentialityLevel() {
		return confidentialityLevel;
	}

	@Override
	public String getDescription() {
		return description;
	}

}

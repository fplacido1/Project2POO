package VCSystem.exceptions;

@SuppressWarnings("serial")
public class ExceedsProjectConfidentialityLevelException extends Exception{

	private static final String MESSAGE = "%s: exceeds project confidentiality level.\n";
		
		public ExceedsProjectConfidentialityLevelException(String param) {
			super(String.format(MESSAGE, param));
		}
}

package State_Census_Analyser;

public class CensusAnalyserException extends Exception {
	public enum ExceptionType {
		NO_FILE, INCORRECT_FILE, UNABLE_TO_PARSE
	}

	public ExceptionType type;

	public CensusAnalyserException(String message, ExceptionType type) {
		super(message);
		this.type = type;
	}
}
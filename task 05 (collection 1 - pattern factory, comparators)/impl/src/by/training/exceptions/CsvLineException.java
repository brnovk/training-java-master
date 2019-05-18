package by.training.exceptions;

/**
 * A custom class of exceptions for processing error rows csv-file.
 * Inherits from "IllegalArgumentException".
 * @see java.lang.IllegalArgumentException
 * @author BaranauViktar
 */
public class CsvLineException extends IllegalStateException {
	
	private static final long serialVersionUID = 1449948879549684992L;
	private static final String MSG_STRING = "Error string: ";
	private static final String MSG_EXPLANATION = "Cause of error: ";
	
	/**
	 *  A string containing the error.
	 */
	private final String stringError;
	
	/**
	 * Mark the type of error.
	 */
	private final String explanationError;
	
	/**
	 * 
	 * @param stringError A string containing the error.
	 * @param explanationError Mark the type of error.
	 */
	public CsvLineException(String stringError,
			String explanationError) {
		this.stringError = stringError;
		this.explanationError = explanationError;
	}
	
	/**
	 * Gets a message indicating the line with the error and the error.
	 */
	@Override
	public String getMessage() {
		return MSG_STRING + stringError + "\n" + 
				MSG_EXPLANATION + explanationError + "\n";
	}

	@Override
	public String toString() {
		return "IncorrectLineFileException [stringError=" + stringError
				+ ", explanationError=" + explanationError + "]";
	}
}

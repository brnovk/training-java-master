package by.training.exceptions;

/**
 * A custom exception class, intended to store information field of 
 * a class which is not properly initialized.
 * Inherits from "IllegalArgumentException".
 * @see java.lang.IllegalArgumentException
 * @author BaranauViktar
 */
public class NonpositiveArgumentException extends IllegalArgumentException {

	private static final long serialVersionUID = 8878443516971233068L;
	
	/**
	 * Name mistaken fields
	 */
    private final String mistakenField;
    
    /**
     * The value of the field, which resulted in an error.
     */
    private final int nonpositiveValue;
    
    /**
     * @param nonpositiveValue the value of the field, which resulted in an error.
     * @param mistakenField name mistaken fields.
     */
    public NonpositiveArgumentException(int nonpositiveValue, 
    									String mistakenField) {
            this.nonpositiveValue = nonpositiveValue;
            this.mistakenField = mistakenField;
    }
    
    @Override
    public String getMessage() {
            return ("Non-positive value " + nonpositiveValue + 
            		" in the field \"" + mistakenField + "\"");
    }
}

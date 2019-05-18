import java.util.Enumeration;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Implementation of Task 2 in the theme Strings.
 * @author BaranauViktar
 */
public class Runner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// Search expression: "indexI=J" (without checking "I" and "J" for natural number).
        final String REGEX_INDEX = "^index(.*)=(.*)$";
		// Expression to find the natural number (positive integer without leading zeros).
        final String REGEX_NATURAL_NUMBER = "^[1-9]+[0-9]*$";

        final Pattern PATTERN_INDEX = Pattern.compile(REGEX_INDEX);
        final Pattern PATTERN_NATURAL_NUMBER = Pattern.compile(REGEX_NATURAL_NUMBER);

        // the name of the properties file (without extension)
        final String PROPERTY_FILE = "in"; 
        final String VALUE = "value";

        int countErrorLine = 0;
        double resultSumming = 0D;

        try {
            ResourceBundle rb = ResourceBundle.getBundle(PROPERTY_FILE);
            Enumeration<String> keys = rb.getKeys();
            String key;

            while (keys.hasMoreElements()) {
                key = keys.nextElement();

                String keyWithValue = key + "=" + rb.getString(key);
                Matcher matcherIndex = PATTERN_INDEX.matcher(keyWithValue);

                if (matcherIndex.matches()) {

                	// Get the value of "I".
                    String iNumber = matcherIndex.group(1);

                	// Get the value of "J".
                    String jNumber = matcherIndex.group(2);

                    // Check on the natural numbers "I" and "J"
                    boolean isNaturalI = PATTERN_NATURAL_NUMBER.matcher(iNumber).matches();
                    boolean isNaturalJ = PATTERN_NATURAL_NUMBER.matcher(jNumber).matches();

                    if (isNaturalI && isNaturalJ) {

                    	// Get the string: "valueIJ"
                        String valueIJ = VALUE + iNumber + jNumber;
                        try {
                            resultSumming += Double.parseDouble(rb.getString(valueIJ));

                          // "RuntimeException" - nearest superclass of an exception for 
                          // "MissingResourceException" and "NumberFormatException".
                        } catch (RuntimeException ex) {
                            countErrorLine++;
                        }
                    } else {
                        countErrorLine++;
                    }
                }
            }

            System.out.printf("sum = %04.3f\n", resultSumming);
            System.out.println("error-lines = " + countErrorLine);

        } catch (MissingResourceException ex) {
            System.err.println("Properties file \"" + PROPERTY_FILE + "\" does not exist");
            System.exit(1);
        }
	}
}

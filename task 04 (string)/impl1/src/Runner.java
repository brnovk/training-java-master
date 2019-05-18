import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Implementation of Task 1 in the theme Strings.
 * @author BaranauViktar
 */
public class Runner {

    public static void main(String[] args) {

        final char OPENING_PARENTHESIS = '(';
        final char CLOSING_PARENTHESIS = ')';
        final String EQUAL_SIGN = " = ";
        final String MINUS_SIGN = " - ";
        final String PLUS_SIGN = " + ";
        final String INPUT_CSV = "in.csv";

        int countErrorLine = 0;
        StringBuilder resultLine = new StringBuilder();
        double resultExpression = 0D;
        Scanner scr = null;
        try {
            scr = new Scanner(new InputStreamReader(
            		Runner.class.getResourceAsStream("/" + INPUT_CSV)));
            while (scr.hasNext()) {

                String currentLine = scr.nextLine(); 
                String[] dataset = currentLine.split(";");

                try {
                    // Read zero element indicating the desired index.
                    int currentIndex = Integer.parseInt(dataset[0].trim());
                    double currentValue = Double.parseDouble(dataset[currentIndex]);

                    // Add to the total amount.
                    resultExpression += currentValue;

                    // Select the octothorpe
                    String octothorpe = (currentValue >= 0) ? PLUS_SIGN : MINUS_SIGN;
                    resultLine.append(octothorpe);
                    resultLine.append(Math.abs(currentValue));

                  // "RuntimeException" - nearest superclass of an exception for 
                  // "ArrayIndexOutOfBoundsException" and "NumberFormatException".
                } catch (RuntimeException exception) {
                    countErrorLine++; // increment the error count
                }
            }

            // Correct the value of the first element
            if (resultLine.length() > 0) {
                String firstOctothorpe = resultLine.substring(0, 3);
                resultLine.replace(0, 3, (firstOctothorpe.equals(MINUS_SIGN)) ? "-" : "");
            }

        } catch (NullPointerException ex) {
            System.out.println(INPUT_CSV + " file does not exist.");
            System.exit(1);
        } finally {
            if (scr != null)
            scr.close();
        }

        // Insert the first part of result string "result("
        resultLine.insert(0, "result" + OPENING_PARENTHESIS);

        // Add the last part of result string ") = result"
        resultLine.append(CLOSING_PARENTHESIS).append(EQUAL_SIGN).append(resultExpression);

        System.out.println(resultLine);
        System.out.println("error-lines" + EQUAL_SIGN + countErrorLine);
    }
}

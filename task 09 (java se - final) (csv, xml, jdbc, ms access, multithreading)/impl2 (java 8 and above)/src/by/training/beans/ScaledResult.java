package by.training.beans;

import static by.training.Constants.*;
import java.sql.Date;

/**
 * This class is an alternative description of the essence of "result". 
 * Designed to store the result of the XML-file - that is score represented
 * a real number with one digit after the decimal point 
 * (for example, "6.8" or "9.4").
 * 
 * @see Result
 * @see by.training.factories.ScaledResultFactory
 * @see by.training.factories.ResultFactory
 * 
 * @author BaranauViktar
 */
public class ScaledResult extends Result {

	public ScaledResult() {
		super();
	}

	public ScaledResult(String login, String test, Date date, int mark) {
		super(login, test, date, mark);
	}

	@Override
	public String getStringMark() {
		int mark = getMark();
		StringBuilder result = new StringBuilder();
		result.append(mark / DECIMAL_DIVISOR);
		result.append(DECIMAL_SEPARATOR);
		result.append(mark % DECIMAL_DIVISOR);
		return result.toString();
	}

	@Override
	public void setMark(String mark) {
		try {
			double tmp = Double.parseDouble(mark);
			setMark((int) Math.round(tmp * DECIMAL_DIVISOR));
		} catch (NumberFormatException ex) {
			throw new IllegalArgumentException(mark, ex);
		}
	}
}

package by.training.beans;

import static by.training.Constants.*;
import java.sql.Date;

/**
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

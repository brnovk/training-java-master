package by.training.factories;

import java.sql.Date;

import by.training.Constants;
import by.training.beans.*;

/**
 * @author BaranauViktar
 */
public class ScaledResultFactory extends ResultFactory {

	@Override
	public Result getClassFromFactory() {
		return new ScaledResult();
	}

	@Override
	public Result getClassFromFactory(
			String login, String test, Date date,int mark) {
		return new ScaledResult(login, test, date, mark);
	}

	@Override
	public double castingMark(double likeMark) {
		return likeMark / Constants.DECIMAL_DIVISOR;
	}
}

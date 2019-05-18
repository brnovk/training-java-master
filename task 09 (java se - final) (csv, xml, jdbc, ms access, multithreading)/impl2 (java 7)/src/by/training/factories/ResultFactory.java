package by.training.factories;

import java.sql.Date;
import by.training.beans.Result;

/**
 * @author BaranauViktar
 */
public class ResultFactory {

	public Result getClassFromFactory(){
		return new Result();
	}

	public Result getClassFromFactory(
			String login, String test, Date date, int mark) {
		return new Result(login, test, date, mark);
	}

	public double castingMark(double likeMark) {
		return likeMark;
	}
}

package by.training.beans;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author BaranauViktar
 */
public class Result {

	private final int DECIMAL_DIVISOR = 10;
	private String login;
	private String test;
	private Date date;
	private int mark;

	public Result() {
		super();
	}

	public Result(String login, String test, Date date, int mark) {
		super();
		this.login = login;
		this.test = test;
		this.date = date;
		this.mark = mark;
	}

	// getters

	public String getLogin() {
		return login;
	}

	public String getTest() {
		return test;
	}

	public Date getDate() {
		return date;
	}

	public String getStringDate() {
		final DateFormat outputDateFormat = new SimpleDateFormat("dd.MM.yyyy");
		return outputDateFormat.format(date);
	}

	public int getMark() {
		return mark;
	}

	public String getStringMark() {
		final char DECIMAL_SEPARATOR = '.';
		StringBuilder result = new StringBuilder();
		result.append(mark / DECIMAL_DIVISOR);
		result.append(DECIMAL_SEPARATOR);
		result.append(mark % DECIMAL_DIVISOR);
		return result.toString();
	}

	// setters

	public void setLogin(String login) {
		this.login = login;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setDate(String date) {
		final DateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		inputDateFormat.setLenient(false);
		try {
			this.date = inputDateFormat.parse(date);
		} catch (ParseException ex) {
			throw new IllegalArgumentException(ex);
		}
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	public void setMark(String mark) {
		try {
			double tmp = Double.parseDouble(mark);
			this.mark = (int) Math.round(tmp * DECIMAL_DIVISOR);
		} catch (NumberFormatException ex) {
			throw new IllegalArgumentException(ex);
		}
	}

	@Override
	public String toString() {
		final char CSV_SEPARATOR = ';';
		StringBuilder result = new StringBuilder();
		result.append(login).append(CSV_SEPARATOR);
		result.append(test).append(CSV_SEPARATOR);
		result.append(getStringDate()).append(CSV_SEPARATOR);
		result.append(getStringMark());
		return result.toString();
	}
}

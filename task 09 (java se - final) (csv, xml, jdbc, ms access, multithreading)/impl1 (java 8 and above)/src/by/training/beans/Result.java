package by.training.beans;

import static by.training.Constants.*;

import java.sql.Date;
import java.text.*;

/**
 * @author BaranauViktar
 */
public class Result {
	
	private final static DateFormat DATE_FORMAT = 
			new SimpleDateFormat(DATE_PATTERN);

	private String login;
	private String test;
	private Date date; 
	private int mark;
	
	public Result() {
	}

	public Result(String login, String test, Date date, int mark) {
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
		return DATE_FORMAT.format(date);
	}

	public int getMark() {
		return mark;
	}
	
	public String getStringMark() {
		return Integer.toString(mark);
	}
	
	// setters
	
	public void setLogin(String login) {
		if ("".equals(login)) {
			throw new IllegalArgumentException(login);
		}
		this.login = login;
	}
	
	public void setTest(String test) {
		if ("".equals(test)) {
			throw new IllegalArgumentException(test);
		}
		this.test = test;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public void setDate(String date) {
		try {
			java.util.Date intermediate = DATE_FORMAT.parse(date);
			this.date = new Date(intermediate.getTime());
		} catch (ParseException ex) {
			throw new IllegalArgumentException(date, ex);
		}
	}
	
	public void setMark(int mark) {
		this.mark = mark;
	}
	
	public void setMark(String mark) {
		try {
			this.mark = Integer.parseInt(mark);
		} catch (NumberFormatException ex) {
			throw new IllegalArgumentException(mark, ex);
		}
	}

	@Override
	public String toString() {
		return login + CSV_DELIMETER+ test + CSV_DELIMETER
				+ getStringDate() + CSV_DELIMETER + getStringMark();
	}
}

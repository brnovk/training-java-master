package by.training;

/**
 * @author BaranauViktar
 */
public class Constants {
	
	public final static String CSV_FILENAME = "results.csv";
	public final static String XML_FILENAME = "results.xml";
	
	public final static String DB_CLASS = "net.ucanaccess.jdbc.UcanaccessDriver";
	public final static String DB_URL = "jdbc:ucanaccess://db/results.mdb";
	public final static String DB_LOGIN = "";
	public final static String DB_PASSWORD = "";
	
	public final static int INDEX_LOGIN = 1;
	public final static int INDEX_TEST = 2;
	public final static int INDEX_DATE = 3;
	public final static int INDEX_MARK = 4;
	
	public final static int INDEX_AVG_MARK = 1;
	public final static int INDEX_AVG_LOGIN = 2;
	public final static String AVG_OUT_FORMAT = "%.1f: %s\n";
	
	public final static String DATE_PATTERN = "yyyy-MM-dd";

	public final static String CSV_DELIMETER = ";";
	
	public final static int DECIMAL_DIVISOR = 10;
	public final static char DECIMAL_SEPARATOR = '.';
	
	public final static String WARNING_EMPTY_CURRENT_MOUNT = 
			"No results for current month";
}

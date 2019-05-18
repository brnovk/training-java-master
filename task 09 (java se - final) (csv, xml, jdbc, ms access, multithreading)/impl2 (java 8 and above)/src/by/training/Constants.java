package by.training;

/**
 * @author BaranauViktar
 */
public class Constants {

	// The names of the source files

	public final static String CSV_PATH_STRING = "results.csv";
	public final static String XML_PATH_STRING = "results.xml";


	// Debug information.
	
	public final static String LABEL_DEBUG_GET = "\tget";
	public final static String LABEL_DEBUG_SET = "SET";
	public final static String LABEL_DEBUG_DELIMETER = " > ";


	// The source data for the connection to the database.

	public final static String DB_CLASS = "net.ucanaccess.jdbc.UcanaccessDriver";
	public final static String DB_URL = "jdbc:ucanaccess://db/results.mdb";
	public final static String DB_LOGIN = "";
	public final static String DB_PASSWORD = "";


	// Indexes datasets for entity "Result" and "ScaledResult".
	// Used methods: "loadResults()" and "run()".

	public final static int INDEX_LOGIN = 1;
	public final static int INDEX_TEST = 2;
	public final static int INDEX_DATE = 3;
	public final static int INDEX_MARK = 4;


	// Indexes in the result dataset of the subtask 3.

	public final static int INDEX_AVG_MARK = 1;
	public final static int INDEX_AVG_LOGIN = 2;

	/**
	 * The number of milliseconds to pause the main thread.
	 */
	public final static int DELAY_WAITING_THREAD = 100;

	/**
	 * The format string for output subtask 3.
	 */
	public final static String AVG_OUT_FORMAT = "%.1f: %s\n";

	/**
	 * Date pattern for output and parsing dates to SQL, CSV and XML files.
	 */
	public final static String DATE_PATTERN = "yyyy-MM-dd";

	/**
	 * Data separator in the CSV file and output the data in csv-format.
	 */
	public final static String CSV_DELIMETER = ";";

	public final static int DECIMAL_DIVISOR = 10;

	public final static char DECIMAL_SEPARATOR = '.';

	public final static String WARNING_EMPTY_CURRENT_MOUNT = 
			"No results for current month";
}

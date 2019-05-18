package by.training.database;

/**
 * @author BaranauViktar
 */
public class SQLQueries {

	public final static String CLEAR_LOGINS = 
			"DELETE * "
		  + "FROM logins";

	public final static String CLEAR_TESTS = 
			"DELETE * "
		  + "FROM tests";

	public final static String CLEAR_RESULTS = 
			"DELETE * "
		  + "FROM results";

	public final static String PS_SELECT_LOGIN = 
			"SELECT idLogin "
		  + "FROM logins "
		  + "WHERE name = ?";

	public final static String PS_SELECT_TEST = 
			"SELECT idTest "
		  + "FROM tests "
		  + "WHERE name = ?";

	public final static String PS_INSERT_LOGIN = 
			"INSERT INTO logins(name) VALUES(?)";

	public final static String PS_INSERT_TEST = 
			"INSERT INTO tests(name) VALUES(?)";

	public final static String PS_INSERT_RESULT = 
			"INSERT INTO results(loginId, testId, dat, mark) VALUES(?,?,?,?)";

	public final static String SELECT_AVG_MARK = 
			"SELECT avg(mark), name "
		  + "FROM results, logins "
		  + "WHERE loginId = idLogin "
		  + "GROUP BY loginId, name "
		  + "ORDER BY 1 DESC";

	public final static String SELECT_CURRENT_MOUNT = 
			"SELECT lgn.name, tst.name, dat, mark "
		  + "FROM ((results rsl "
		  + "INNER JOIN logins lgn ON rsl.loginId = lgn.idLogin) "
		  + "INNER JOIN tests tst ON rsl.testId = tst.idTest) "
		  + "WHERE Month(dat) = Month(Date()) "
		  + "AND Year(dat) = Year(Date())"
		  + "ORDER BY 3";
}

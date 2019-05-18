package by.training;

import static by.training.Constants.*;

import java.sql.*;
import java.util.*;

import by.training.DAOReaders.IResultDAO;
import by.training.beans.Result;
import by.training.database.*;
import by.training.factories.ResultFactory;

/**
 * Application business logic class.
 * @author BaranauViktar
 */
public class RunnerLogic {

	@SuppressWarnings("resource")
	public static void run(IResultDAO reader, ResultFactory resultFactory) {

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = ConnectionControl.getConnection();
			statement = connection.createStatement();
		    ResultsLoader resultsLoader = new ResultsLoader(reader, connection); 

		    // Subtask 2
		    resultsLoader.loadResults();
		    System.out.println();

		    // Subtask 3
		    Formatter fmt = new Formatter();
		    resultSet = statement.executeQuery(SQLQueries.SELECT_AVG_MARK);
		    while (resultSet.next()) {
		    	fmt.format(AVG_OUT_FORMAT, resultFactory.castingMark(
		    			resultSet.getFloat(INDEX_AVG_MARK)), 
		    			resultSet.getString(INDEX_AVG_LOGIN));
		    }
		    System.out.println(fmt);

		    // Subtask 4
		    List<Result> results = new ArrayList<>();
			resultSet = statement.executeQuery(SQLQueries.SELECT_CURRENT_MOUNT);
			while (resultSet.next()) {
				Result currentResult = resultFactory.getClassFromFactory(
						resultSet.getString(INDEX_LOGIN),
						resultSet.getString(INDEX_TEST),
						resultSet.getDate(INDEX_DATE),
						resultSet.getInt(INDEX_MARK));
				results.add(currentResult);
				System.out.println(currentResult);
			}
			System.out.println();

			// Subtask 5
			final int MINIMAL_COUNT = 0;
			int lastedIndex = results.size();
			if (lastedIndex > MINIMAL_COUNT) {
				java.sql.Date lastedDate = results.get(lastedIndex-1).getDate();
				ListIterator<Result> listIterator = results.listIterator(lastedIndex);
				while (listIterator.hasPrevious()) {
					Result otherResult = listIterator.previous();
					if (!lastedDate.equals(otherResult.getDate())) {
						break;
					}
					System.out.println(otherResult);
				}
			} else {
				System.out.println(WARNING_EMPTY_CURRENT_MOUNT);
			}
		} catch (SQLException | ClassNotFoundException ex) {
			System.err.println(ex);

		} finally {
			ConnectionControl.close(resultSet);
			ConnectionControl.close(statement);
			ConnectionControl.close();
		}
	}
}

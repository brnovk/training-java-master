package by.training.database;

import static by.training.database.SQLQueries.*;
import java.sql.*;

import by.training.Constants;
import by.training.DAOReaders.IResultDAO;
import by.training.beans.Result;

/**
 * @author BaranauViktar
 */
public class ResultsLoader {

	private final int INVALID_VALUE = -1;
	private final int INDEX_NAME = 1;

	private IResultDAO reader; 
	private Connection connection; 

	public ResultsLoader(IResultDAO reader, Connection connection) {
		this.reader = reader;
		this.connection = connection;
	}

	private void clearTables(Statement statement) throws SQLException {
		statement.execute(CLEAR_LOGINS);
		statement.execute(CLEAR_TESTS);
		statement.execute(CLEAR_RESULTS);
	}

	private int findId(String findValue, PreparedStatement psSelectQuery) 
													throws SQLException {
		final int INDEX_ID = 1;
		int currentID = INVALID_VALUE;
        psSelectQuery.setString(INDEX_NAME, findValue);
        ResultSet resultSet = psSelectQuery.executeQuery();
        if (resultSet.next()) {
        	currentID = resultSet.getInt(INDEX_ID);
        }
        ConnectionControl.close(resultSet);   // FIXME Should be in the block finally 
        return currentID;
	}

	private int getId(String fieldValue, PreparedStatement psSelectQuery, 
					PreparedStatement psInsertQuery) throws SQLException {
        int currentID;
        currentID = findId(fieldValue, psSelectQuery);
        if (currentID > INVALID_VALUE) {
                return currentID;
        }
        psInsertQuery.setString(INDEX_NAME, fieldValue);
        psInsertQuery.executeUpdate();
        return findId(fieldValue, psSelectQuery);
	}

	public void loadResults() throws SQLException {
		Statement statement = null;
		PreparedStatement psSelectLogin = null;
		PreparedStatement psSelectTest = null;
		PreparedStatement psInsertLogin = null;
		PreparedStatement psInsertTest = null;
		PreparedStatement psInsertResult = null;

		try {
			statement = connection.createStatement();
			clearTables(statement);
			psSelectLogin = connection.prepareStatement(PS_SELECT_LOGIN);
			psSelectTest = connection.prepareStatement(PS_SELECT_TEST);
			psInsertLogin = connection.prepareStatement(PS_INSERT_LOGIN);
			psInsertTest = connection.prepareStatement(PS_INSERT_TEST);
			psInsertResult = connection.prepareStatement(PS_INSERT_RESULT);

			while(reader.hasResult()) { 

				Result result = reader.nextResult(); 
				String login = result.getLogin();
				String test = result.getTest(); 
				int idLogin = getId(login, psSelectLogin, psInsertLogin); 
			    int idTest = getId(test, psSelectTest, psInsertTest); 
			    
			    psInsertResult.setInt(Constants.INDEX_LOGIN, idLogin);
			    psInsertResult.setInt(Constants.INDEX_TEST, idTest);
			    psInsertResult.setDate(Constants.INDEX_DATE, result.getDate());
			    psInsertResult.setInt(Constants.INDEX_MARK, result.getMark());
			    psInsertResult.executeUpdate();
			}
		} finally {
			reader.closeReader();
			ConnectionControl.close(psInsertResult,
									psInsertTest,
									psInsertLogin,
									psSelectTest,
									psSelectLogin);
			ConnectionControl.close(statement);
		}
	}
}

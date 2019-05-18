package by.training.database;

import java.sql.*;
import by.training.Constants;

/**
 * @author BaranauViktar
 */
public class ConnectionControl {
	
    private static Connection connection;

	private ConnectionControl() {
	}

	private static void initializeConnection() throws ClassNotFoundException, 
																SQLException {
		Class.forName(Constants.DB_CLASS);
		connection = DriverManager.getConnection(
				Constants.DB_URL, 
				Constants.DB_LOGIN, 
				Constants.DB_PASSWORD);
	}
	
	public static Connection getConnection() throws ClassNotFoundException, 
																SQLException {
		if ((connection == null) || connection.isClosed()) {
			initializeConnection();
		}
		return connection;
	}

    public static void close() {
    	close(connection);
    }

    public static void close(Connection connection) {
    	try {
    		if (connection != null) {
    			connection.close();
    		}
    	} catch (SQLException e) {
    		System.err.println(e);
    	}
    }

    public static void close(Statement... statements) {
    	for (Statement currentStatement : statements) {
            try {
            	if (currentStatement != null) {
            		currentStatement.close();
            	}
            } catch (SQLException e) {
            	System.err.println(e);
            }
    	}
    }

    public static void close(ResultSet... resultSets) {
    	for (ResultSet currentResultSet : resultSets) {
            try {
            	if (currentResultSet != null) {
            		currentResultSet.close();
            	}
            } catch (SQLException e) {
            	System.err.println(e);
            }
    	}
    }
    
    public static void close(PreparedStatement...statements) {
    	for (PreparedStatement currentStatement : statements) {
    		try {
    			if (currentStatement != null) {
    				currentStatement.close();
    			}
    		} catch (SQLException e) {
    			System.err.println(e);
    		}
    	}
    }
}

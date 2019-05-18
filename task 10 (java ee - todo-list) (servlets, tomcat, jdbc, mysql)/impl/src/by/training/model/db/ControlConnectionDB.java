package by.training.model.db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

import by.training.constans.JDBCPropertiesPath;

public class ControlConnectionDB {

    private Connection initializeConnection() throws ClassNotFoundException,
            SQLException, FileNotFoundException, IOException {
    	
    	final String JDBC_PROPERTY_FILENAME = "jdbc.properties";
    	
    	Properties properties = new Properties();
    	FileInputStream inputStream = null;
    	try {
    		inputStream = new FileInputStream(
				JDBCPropertiesPath.getPathJDBCPropertiesFolder() + JDBC_PROPERTY_FILENAME);
        	properties.load(inputStream);

            Class.forName(properties.getProperty("jdbc.driverClassName"));
            return DriverManager.getConnection(
        		properties.getProperty("jdbc.databaseurl"),
        		properties.getProperty("jdbc.username"),
        		properties.getProperty("jdbc.password")
    		);
    	} finally {
    		if (inputStream != null) {
    			inputStream.close();
    		}
    	}
    }

    public Connection getConnection() throws ClassNotFoundException,
            SQLException, FileNotFoundException, IOException {
        return initializeConnection();
    }

    public void close(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void close(Statement... statements) {
        for (Statement currentStatement : statements) {
            try {
                if (currentStatement != null) {
                    currentStatement.close();
                }
            } catch (SQLException ex) {
            	ex.printStackTrace();
            }
        }
    }

    public void close(ResultSet... resultSets) {
        for (ResultSet currentResultSet : resultSets) {
            try {
                if (currentResultSet != null) {
                    currentResultSet.close();
                }
            } catch (SQLException ex) {
            	ex.printStackTrace();
            }
        }
    }
}

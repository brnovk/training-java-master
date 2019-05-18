package by.training.model.impls;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import by.training.constans.Constants;
import by.training.constans.ConstantsQueries;
import by.training.constans.ErrorMessage;
import by.training.exceptions.DaoException;
import by.training.ifaces.IFileDAO;
import by.training.model.beans.File;
import by.training.model.db.ControlConnectionDB;

public class DBFileImpl implements IFileDAO {

    private final ControlConnectionDB controlConnect = new ControlConnectionDB();

    @Override
    public File setFile(String fileName) {

        final int INDEX_GENERATED_KEY = 1;
        final int INDEX_FILENAME = 1;

        Connection connection = null;
        PreparedStatement psInsertFile = null;
        ResultSet generatedKeys = null;
        try {
            int fileId = Constants.EMPTY_ID;
            connection = controlConnect.getConnection();

            psInsertFile = connection.prepareStatement(
                    ConstantsQueries.PS_INSERT_FILE,
                    Statement.RETURN_GENERATED_KEYS);
            psInsertFile.setString(INDEX_FILENAME, fileName);
            psInsertFile.executeUpdate();
            generatedKeys = psInsertFile.getGeneratedKeys();

            if (generatedKeys.next()) {
                fileId = generatedKeys.getInt(INDEX_GENERATED_KEY);
            }
            return new File(fileId, fileName);
        } catch (ClassNotFoundException | IOException ex) {
            throw new DaoException(ErrorMessage.UNABLE_CONNECT_DB_ERROR, ex);
        } catch (SQLException ex) {
            throw new DaoException(ErrorMessage.UNKNOWN_QUERY_ERROR, ex);
        } finally {
            controlConnect.close(generatedKeys);
            controlConnect.close(psInsertFile);
            controlConnect.close(connection);
        }
    }

    @Override
    public void removeFile(int idFile) {
        final int INDEX_ID = 1;
        Connection connection = null;
        PreparedStatement psDeleteFile = null;
        try {
            connection = controlConnect.getConnection();
            psDeleteFile = connection.prepareStatement(
                    ConstantsQueries.PS_DELETE_FILE);
            psDeleteFile.setInt(INDEX_ID, idFile);
            psDeleteFile.executeUpdate();

        } catch (ClassNotFoundException | IOException ex) {
            throw new DaoException(ErrorMessage.UNABLE_CONNECT_DB_ERROR, ex);
        } catch (SQLException ex) {
            throw new DaoException(ErrorMessage.UNKNOWN_QUERY_ERROR, ex);
        } finally {
            controlConnect.close(psDeleteFile);
            controlConnect.close(connection);
        }
    }
}

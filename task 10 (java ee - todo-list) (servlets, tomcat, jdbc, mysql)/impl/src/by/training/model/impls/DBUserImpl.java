package by.training.model.impls;

import java.io.IOException;
import java.sql.*;

import by.training.constans.Constants;
import by.training.constans.ConstantsQueries;
import by.training.constans.ErrorMessage;
import by.training.exceptions.DaoException;
import by.training.ifaces.IUserDAO;
import by.training.model.beans.User;
import by.training.model.db.ControlConnectionDB;

public class DBUserImpl implements IUserDAO {

    private final ControlConnectionDB controlConnect = new ControlConnectionDB();
    private static final Object blockAddition = new Object();

    @Override
    public User getUser(String login, String password) {

        final int REQUEST_INDEX_LOGIN = 1;
        final int REQUEST_INDEX_PASSWORD = 2;

        final int RESPONSE_INDEX_ID = 1;
        final int RESPONSE_INDEX_LOGIN = 2;
        final int RESPONSE_INDEX_PASSWORD = 3;
        final int RESPONSE_INDEX_EMAIL = 4;

        Connection connection = null;
        PreparedStatement psSelectUser = null;
        ResultSet resultSet = null;
        
        try {
            connection = controlConnect.getConnection();
            psSelectUser = connection.prepareStatement(
                    ConstantsQueries.PS_SELECT_USER);
            psSelectUser.setString(REQUEST_INDEX_LOGIN, login);
            psSelectUser.setString(REQUEST_INDEX_PASSWORD, password);
            resultSet = psSelectUser.executeQuery();
            if (resultSet.next()) {
                User resultUser = new User();
                resultUser.setId(resultSet.getInt(RESPONSE_INDEX_ID));
                resultUser.setLogin(resultSet.getString(RESPONSE_INDEX_LOGIN));
                resultUser.setPassword(resultSet.getString(RESPONSE_INDEX_PASSWORD));
                resultUser.setEmail(resultSet.getString(RESPONSE_INDEX_EMAIL));
                return resultUser;
            } else {
                throw new DaoException(
                        ErrorMessage.LOGIN_OR_PASSWORD_INCORRECT_ERROR);
            }
        } catch (ClassNotFoundException | IOException ex) {
            throw new DaoException(ErrorMessage.UNABLE_CONNECT_DB_ERROR, ex);
        } catch (SQLException ex) {
            throw new DaoException(ErrorMessage.UNKNOWN_QUERY_ERROR, ex);
        } finally {
            controlConnect.close(resultSet);
            controlConnect.close(psSelectUser);
            controlConnect.close(connection);
        }
    }

    @Override
    public User setUser(String login, String password, String email) {

        final int INDEX_GENERATED_KEY = 1;

        final int INDEX_LOGIN = 1;
        final int INDEX_PASSWORD = 2;
        final int INDEX_EMAIL = 3;

        if (Constants.VALUE_USER_GUEST.toLowerCase().equals(
                login.toLowerCase())) {
            throw new DaoException(ErrorMessage.LOGIN_TAKEN_ERROR);
        }
        Connection connection = null;
        PreparedStatement psSelectLogin = null;
        PreparedStatement psInsertUser = null;
        ResultSet resultSet = null;
        ResultSet generatedKeys = null;
        try {
            int userId = Constants.EMPTY_ID;
            connection = controlConnect.getConnection();
            psSelectLogin = connection.prepareStatement(
                    ConstantsQueries.PS_SELECT_LOGIN_USER);
            psSelectLogin.setString(INDEX_LOGIN, login);
            synchronized (blockAddition) {
                resultSet = psSelectLogin.executeQuery();
                if (resultSet.next()) {
                    throw new DaoException(ErrorMessage.LOGIN_TAKEN_ERROR);
                }
                psInsertUser = connection.prepareStatement(
                    ConstantsQueries.PS_INSERT_USER, Statement.RETURN_GENERATED_KEYS);
                psInsertUser.setString(INDEX_LOGIN, login);
                psInsertUser.setString(INDEX_PASSWORD, password);
                psInsertUser.setString(INDEX_EMAIL, email);
                psInsertUser.executeUpdate();
                generatedKeys = psInsertUser.getGeneratedKeys();
            }
            if (generatedKeys.next()) {
                userId = generatedKeys.getInt(INDEX_GENERATED_KEY);
            }
            return new User(userId, login, password, email);
        } catch (ClassNotFoundException | IOException ex) {
            throw new DaoException(ErrorMessage.UNABLE_CONNECT_DB_ERROR, ex);
        } catch (SQLException ex) {
            throw new DaoException(ErrorMessage.UNKNOWN_QUERY_ERROR, ex);
        } finally {
            controlConnect.close(generatedKeys);
            controlConnect.close(psInsertUser);
            controlConnect.close(resultSet);
            controlConnect.close(psSelectLogin);
            controlConnect.close(connection);
        }
    }
}

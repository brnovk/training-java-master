package by.training.model.impls;

import java.util.*;

import by.training.constans.Constants;
import by.training.constans.ErrorMessage;
import by.training.exceptions.DaoException;
import by.training.ifaces.IUserDAO;
import by.training.model.beans.User;

public class MemoryUserImpl implements IUserDAO {
	
	private static int orderId = 1;
    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User(orderId++, "admin", "admin", "admin@examle.org"));
        users.add(new User(orderId++, "qwerty", "qwerty", "qwerty@examle.org"));
        users.add(new User(orderId++, "some", "some", "some@examle.org"));
    }

    @Override
    public User getUser(String login, String password) throws DaoException {
        User resultUser = null;
        for (User currentUser : users) {
            if (currentUser.getLogin().equals(login)) {
                if (currentUser.getPassword().equals(password)) {
                    resultUser = currentUser;
                    break;
                }
            }
        }
        return resultUser;
    }

    @Override
    public User setUser(String login, String password, String email) {
        if (Constants.VALUE_USER_GUEST.toLowerCase().equals(login.toLowerCase())) {
            throw new DaoException(ErrorMessage.LOGIN_TAKEN_ERROR);
        }
        for (User currentUser : users) {
            if (currentUser.getLogin().equals(login)) {
            	throw new DaoException(ErrorMessage.LOGIN_TAKEN_ERROR);
            }
        }
        User resultUser = new User(orderId++, login, password, email);
        users.add(resultUser);
        return resultUser;
    }
}

package by.training.ifaces;

import by.training.model.beans.User;

public interface IUserDAO {

    public User getUser(String name, String password);
    public User setUser(String name, String password, String email);
}

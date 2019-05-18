package by.training.model.factories;

import by.training.ifaces.IUserDAO;
import by.training.model.impls.*;

public class UserFactory {

    public static IUserDAO getClassFromFactory() {
        return new DBUserImpl();
//		return new MemoryUserImpl();
    }
}

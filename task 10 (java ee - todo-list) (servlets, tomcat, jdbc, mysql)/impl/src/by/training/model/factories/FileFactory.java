package by.training.model.factories;

import by.training.ifaces.IFileDAO;
import by.training.model.impls.DBFileImpl;

public class FileFactory {
    public static IFileDAO getClassFromFactory() {
    	return new DBFileImpl();
    }
}

package by.training.model.factories;

import by.training.ifaces.INoteDAO;
import by.training.model.impls.*;

public class NoteFactory {
    public static INoteDAO getClassFromFactory() {
    	return new DBNoteImpl();
//		return new MemoryNodeImpl();
    }
}

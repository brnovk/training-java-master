package by.training.DAOReaders;

import by.training.beans.Result;

/**
 * @author BaranauViktar
 */
public interface IResultDAO {

	boolean hasResult();
	Result nextResult();
	void closeReader();
}

package by.training.DAOReaders;

import by.training.beans.Result;

/**
 * @author BaranauViktar
 */
public interface IConcurentResultDAO extends IResultDAO {                     

	void setResult(Result result);
	public void threadStart(Runnable producer);
}

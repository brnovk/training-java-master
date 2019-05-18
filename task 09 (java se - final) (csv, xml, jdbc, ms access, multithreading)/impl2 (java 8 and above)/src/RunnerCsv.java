import java.io.IOException;

import by.training.*;
import by.training.DAOReaders.IResultDAO;
import by.training.DAOReaders.CSVReader.ResultImplCSV;
import by.training.factories.ResultFactory;

/**
 * Single-threaded version of the application started. 
 * Designed to work with the file "src\result.csv" and the database 
 * MS Access "results.mdb".
 * 
 * @see RunnerXml RunnerXml - Single-threaded version;
 * @see RunnerConcurrentXml RunnerConcurrentXml - Multithreaded version;
 * @see RunnerConcurrentCsv RunnerConcurrentCsv - Multithreaded version;
 * 
 * @author BaranauViktar
 */
public class RunnerCsv {

	public static void main(String[] args) {
		try {
			ResultFactory resultFactory = new ResultFactory();
			IResultDAO reader = new ResultImplCSV(
					Constants.CSV_PATH_STRING, resultFactory);
			RunnerLogic.run(reader, resultFactory);
		} catch (IOException | RuntimeException e) {
			System.err.println(e);
		}

	}
}

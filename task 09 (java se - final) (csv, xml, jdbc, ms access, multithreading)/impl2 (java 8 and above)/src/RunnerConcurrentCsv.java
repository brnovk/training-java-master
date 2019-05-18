import java.io.IOException;

import by.training.*;
import by.training.DAOReaders.*;
import by.training.DAOReaders.CSVReader.ConcurrentCSVReader;
import by.training.factories.ResultFactory;

/**
 * Multithreaded version of the application started. 
 * Designed to work with the file "src\result.csv" and the database 
 * MS Access "results.mdb".
 * 
 * @see RunnerConcurrentXml RunnerConcurrentXml - Multithreaded version;
 * @see RunnerXml RunnerXml - Single-threaded version;
 * @see RunnerCsv RunnerCsv - Single-threaded version;
 * 
 * @author BaranauViktar
 */
public class RunnerConcurrentCsv {

	public static void main(String[] args) {
		try {
			ResultFactory resultFactory = new ResultFactory();
			IConcurentResultDAO reader = new ConcurrentResultBuffer();
			Runnable csvProducer = new ConcurrentCSVReader(
					reader, Constants.CSV_PATH_STRING, resultFactory);
			reader.threadStart(csvProducer);
			RunnerLogic.run(reader, resultFactory);
		} catch (IOException | RuntimeException ex) {
			System.err.println(ex);
		}
	}
}

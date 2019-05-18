import java.io.IOException;

import by.training.*;
import by.training.DAOReaders.IResultDAO;
import by.training.DAOReaders.CSVReader.ResultImplCSV;
import by.training.factories.ResultFactory;

public class RunnerCsv {

	public static void main(String[] args) {
		try {
			ResultFactory resultFactory = new ResultFactory();
			IResultDAO reader = new ResultImplCSV(Constants.CSV_FILENAME, resultFactory);
			RunnerLogic.run(reader, resultFactory);
		} catch (IOException | RuntimeException e) {
			System.err.println(e);
		}
	}
}

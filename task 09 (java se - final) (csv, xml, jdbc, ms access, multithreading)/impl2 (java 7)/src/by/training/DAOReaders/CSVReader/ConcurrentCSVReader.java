package by.training.DAOReaders.CSVReader;

import java.io.*;
import java.util.Scanner;

import by.training.Constants;
import by.training.DAOReaders.IConcurentResultDAO;
import by.training.beans.Result;
import by.training.factories.ResultFactory;

/**
 * The multithreaded version of the csv reader.
 * @author BaranauViktar
 */
public class ConcurrentCSVReader implements Runnable {

	private Scanner scanner;
	private final IConcurentResultDAO buffer;
	private final ResultFactory resultFactory;

	public ConcurrentCSVReader(IConcurentResultDAO buffer,
			String csvPathString, ResultFactory resultFactory)
			throws FileNotFoundException {
		scanner = new Scanner(new FileReader(csvPathString));
		this.buffer = buffer;
		this.resultFactory = resultFactory;
	}

	@Override
	public void run() {
		final int INDEX_LOGIN = 0;
		final int INDEX_TEST = 1;
		final int INDEX_DATE = 2;
		final int INDEX_MARK = 3;

		while (scanner.hasNext()) {
			String currentLine = scanner.nextLine();
			String[] dataset = currentLine.split(Constants.CSV_DELIMETER);
			Result currentResult = resultFactory.getClassFromFactory();
			currentResult.setLogin(dataset[INDEX_LOGIN]);
			currentResult.setTest(dataset[INDEX_TEST]);
			currentResult.setDate(dataset[INDEX_DATE]);
			currentResult.setMark(dataset[INDEX_MARK]);
            buffer.setResult(currentResult);
		}
		if (scanner != null) {
			scanner.close();
		}
	}
}

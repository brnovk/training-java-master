package by.training.DAOReaders.CSVReader;

import java.io.*;
import java.util.Scanner;

import by.training.Constants;
import by.training.DAOReaders.IResultDAO;
import by.training.beans.Result;
import by.training.factories.ResultFactory;

/**
 * Single-threaded version of the csv reader.
 * @author BaranauViktar
 */
public class ResultImplCSV implements IResultDAO {

	private Scanner scanner = null;
	private ResultFactory resultFactory;

	public ResultImplCSV(String csvFileName, ResultFactory resultFactory)
			throws FileNotFoundException {
		scanner = new Scanner(new FileReader(csvFileName));
		this.resultFactory = resultFactory;
	}

	@Override
	public boolean hasResult() {
		return scanner.hasNext();
	}

	@Override
	public Result nextResult() {
		final int INDEX_LOGIN = 0;
		final int INDEX_TEST = 1;
		final int INDEX_DATE = 2;
		final int INDEX_MARK = 3;

		String currentLine = scanner.nextLine();
		String[] dataset = currentLine.split(Constants.CSV_DELIMETER);
		Result currentResult = resultFactory.getClassFromFactory();
		currentResult.setLogin(dataset[INDEX_LOGIN]);
		currentResult.setTest(dataset[INDEX_TEST]);
		currentResult.setDate(dataset[INDEX_DATE]);
		currentResult.setMark(dataset[INDEX_MARK]);
		return currentResult;
	}

	@Override
	public void closeReader() {
		if (scanner!=null) {
			scanner.close();
		}
	}
}

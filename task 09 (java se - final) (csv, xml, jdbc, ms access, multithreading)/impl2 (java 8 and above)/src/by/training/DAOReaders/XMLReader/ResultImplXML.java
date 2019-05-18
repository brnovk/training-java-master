package by.training.DAOReaders.XMLReader;

import java.util.*;
import java.io.IOException;

import org.xml.sax.*;
import org.xml.sax.helpers.XMLReaderFactory;

import by.training.DAOReaders.IResultDAO;
import by.training.beans.Result;
import by.training.factories.ResultFactory;

/**
 * @author BaranauViktar
 */
public class ResultImplXML implements IResultDAO {

	private Queue<Result> results = new LinkedList<>();

	public ResultImplXML(String fileName, ResultFactory resultFactory) 
			throws SAXException, IOException {
		XMLReader reader = null;
		reader = XMLReaderFactory.createXMLReader();
		ResultHandler resultHandler = new ResultHandler(resultFactory);
		reader.setContentHandler(resultHandler);
		reader.parse(fileName);
		results.addAll(resultHandler.getEntities());
	}

	@Override
	public boolean hasResult() {
		return !results.isEmpty();
	}

	@Override
	public Result nextResult() {
		return results.poll();
	}

	@Override
	public void closeReader() {
		// An implementation is not required
	}
}

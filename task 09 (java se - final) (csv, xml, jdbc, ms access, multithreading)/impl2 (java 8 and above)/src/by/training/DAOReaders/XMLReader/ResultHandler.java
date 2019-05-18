package by.training.DAOReaders.XMLReader;

import java.util.*;

import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

import by.training.beans.Result;
import by.training.factories.ResultFactory;

/**
 * @author BaranauViktar
 */
public class ResultHandler extends DefaultHandler {

	/**
	 * All the tags in the xml-file "results.xml".
	 */
	private static enum TagEnum {
		STUDENT, LOGIN, TESTS, TEST, RESULTS;
	}

	private final List<Result> entities = new LinkedList<>();
	private final ResultFactory resultFactory;
	private String currentLogin = null;
	private TagEnum currentTag = null;

	public ResultHandler(ResultFactory resultFactory) {
		this.resultFactory = resultFactory;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException, RuntimeException {
		super.startElement(uri, localName, qName, attributes);

		// Check the tag. If incorrect, an exception is thrown.
		try {
			currentTag = TagEnum.valueOf(localName.toUpperCase());
		} catch(IllegalArgumentException ex) {
			throw new XmlIllegalElementException(localName, ex);
		}
		if (currentTag == TagEnum.TEST) {
	        final int TEST_INDEX = 0, DATE_INDEX = 1, MARK_INDEX = 2;
	        Result currentEntity = resultFactory.getClassFromFactory();
	        currentEntity.setLogin(currentLogin);
	        currentEntity.setTest(attributes.getValue(TEST_INDEX));
			currentEntity.setDate(attributes.getValue(DATE_INDEX));
			currentEntity.setMark(attributes.getValue(MARK_INDEX));
			entities.add(currentEntity);
	    }
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
		if (currentTag == TagEnum.LOGIN) {
			currentLogin = new String(ch, start, length);
		}
		currentTag = null;
	}

	public List<Result> getEntities() {
		return entities;
	}
}

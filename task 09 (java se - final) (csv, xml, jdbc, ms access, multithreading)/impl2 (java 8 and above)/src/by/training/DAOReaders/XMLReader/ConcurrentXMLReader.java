package by.training.DAOReaders.XMLReader;

import java.io.*;
import java.nio.file.*;

import org.xml.sax.*;
import org.xml.sax.helpers.*;

import by.training.DAOReaders.IConcurentResultDAO;
import by.training.beans.Result;
import by.training.factories.ResultFactory;

/**
 * Multi-threaded version of xml-reader.
 * @author BaranauViktar
 */
public class ConcurrentXMLReader extends DefaultHandler implements Runnable {

    /**
     * All the tags in the xml-file "results.xml".
     */
    private static enum TagEnum {
        STUDENT, LOGIN, TESTS, TEST, RESULTS;
    }

    private final ResultFactory resultFactory;
    private final IConcurentResultDAO buffer;
    private final String xmlPathString;
    private final XMLReader reader;
    private TagEnum currentTag = null;
    private String currentLogin = null;

	public ConcurrentXMLReader(IConcurentResultDAO buffer,
			String xmlPathString, ResultFactory resultFactory) 
					throws SAXException, FileNotFoundException {
        this.resultFactory = resultFactory;
        this.buffer = buffer;
        if (!sourceFileExist(xmlPathString)) {
        	throw new FileNotFoundException(xmlPathString);
        }
        this.xmlPathString = xmlPathString;
        reader = XMLReaderFactory.createXMLReader();
        reader.setContentHandler(this);
	}

    @Override
    public void startElement(String uri, String localName, String qName,
            Attributes attributes) throws SAXException, RuntimeException {
        super.startElement(uri, localName, qName, attributes);

        // Check the tag. If incorrect, an exception is thrown.
        try {
            currentTag = TagEnum.valueOf(localName.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new XmlIllegalElementException(localName, ex);
        }
        if (currentTag == TagEnum.TEST) {
            final int TEST_INDEX = 0, DATE_INDEX = 1, MARK_INDEX = 2;
            Result currentEntity = resultFactory.getClassFromFactory();
            currentEntity.setLogin(currentLogin);
            currentEntity.setTest(attributes.getValue(TEST_INDEX));
            currentEntity.setDate(attributes.getValue(DATE_INDEX));
            currentEntity.setMark(attributes.getValue(MARK_INDEX));
            buffer.setResult(currentEntity);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) 
    		throws SAXException {
        super.characters(ch, start, length);
        if (currentTag == TagEnum.LOGIN) {
            currentLogin = new String(ch, start, length);
        }
        currentTag = null;
    }

    @Override
    public void run() {
        try {
            reader.parse(xmlPathString);
        } catch (IOException | SAXException ex) {
            System.err.println(ex);
        }
    }

    /**
     * Verifies the existence of a source file.
     */
    private boolean sourceFileExist(String filePathString) {
    	return Files.isRegularFile(Paths.get(filePathString));
    }
}

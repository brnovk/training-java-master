import java.io.IOException;
import java.util.List;

import org.xml.sax.XMLReader;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.XMLReaderFactory;

import by.training.ResultHandler;
import by.training.beans.Result;

public class Runner {

	public static void main(String[] args) {
		
		final String XML_FILE_NAME = "results.xml";
		List<Result> entities = null;
		XMLReader reader = null;
		
		try {
			reader = XMLReaderFactory.createXMLReader();
			ResultHandler resultHandler = new ResultHandler();
			reader.setContentHandler(resultHandler);
			reader.parse(XML_FILE_NAME);
			entities = resultHandler.getEntities();
			for (Result currentEntity : entities) {
				System.out.println(currentEntity);
			}
		} catch (IOException | SAXException | RuntimeException ex) {
			System.err.println(ex);
		}
	}
}

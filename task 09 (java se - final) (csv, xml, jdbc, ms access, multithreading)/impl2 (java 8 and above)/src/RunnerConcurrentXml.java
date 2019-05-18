import java.io.IOException;
import org.xml.sax.SAXException;

import by.training.*;
import by.training.DAOReaders.*;
import by.training.DAOReaders.XMLReader.ConcurrentXMLReader;
import by.training.factories.*;

/**
 * Multithreaded version of the application started. 
 * Designed to work with the file "src\result.xml" and the database 
 * MS Access "results.mdb".
 * 
 * @see RunnerConcurrentCsv RunnerConcurrentCsv - Multithreaded version;
 * @see RunnerXml RunnerXml - Single-threaded version;
 * @see RunnerCsv RunnerCsv - Single-threaded version;
 * 
 * @author BaranauViktar
 */
public class RunnerConcurrentXml {

	public static void main(String[] args) {
		try {
			ResultFactory resultFactory = new ScaledResultFactory();
			IConcurentResultDAO reader = new ConcurrentResultBuffer();
			Runnable xmlProducer = new ConcurrentXMLReader(
					reader, Constants.XML_PATH_STRING, resultFactory);
			reader.threadStart(xmlProducer);
			RunnerLogic.run(reader, resultFactory);
		} catch (SAXException | RuntimeException | IOException ex) {
			System.err.println(ex);
		}
	}
}

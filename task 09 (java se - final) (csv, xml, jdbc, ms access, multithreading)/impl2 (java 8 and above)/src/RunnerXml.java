import java.io.IOException;
import org.xml.sax.SAXException;

import by.training.*;
import by.training.DAOReaders.IResultDAO;
import by.training.DAOReaders.XMLReader.ResultImplXML;
import by.training.factories.*;

/**
 * Single-threaded version of the application started. 
 * Designed to work with the file "src\result.xml" and the database 
 * MS Access "results.mdb".
 * 
 * @see RunnerCsv RunnerCsv - Single-threaded version;
 * @see RunnerConcurrentXml RunnerConcurrentXml - Multithreaded version;
 * @see RunnerConcurrentCsv RunnerConcurrentCsv - Multithreaded version;
 * 
 * @author BaranauViktar
 */
public class RunnerXml {

	public static void main(String[] args) {
		try {
			ResultFactory resultFactory = new ScaledResultFactory();
			IResultDAO reader = new ResultImplXML(
					Constants.XML_PATH_STRING, resultFactory);
			RunnerLogic.run(reader, resultFactory);
		} catch (SAXException | IOException | RuntimeException e) {
			System.err.println(e);
		}
	}
}

import java.io.IOException;
import org.xml.sax.SAXException;

import by.training.*;
import by.training.DAOReaders.IResultDAO;
import by.training.DAOReaders.XMLReader.ResultImplXML;
import by.training.factories.*;

public class RunnerXml {

	public static void main(String[] args) {
		try {
			ResultFactory resultFactory = new ScaledResultFactory();
			IResultDAO reader = new ResultImplXML(Constants.XML_FILENAME, resultFactory);
			RunnerLogic.run(reader, resultFactory);
		} catch (SAXException | IOException | RuntimeException e) {
			System.err.println(e);
		}
	}
}

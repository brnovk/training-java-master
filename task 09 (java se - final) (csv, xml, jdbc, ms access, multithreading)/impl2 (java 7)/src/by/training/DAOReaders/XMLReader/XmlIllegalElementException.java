package by.training.DAOReaders.XMLReader;

/**
 * @author BaranauViktar
 */
public class XmlIllegalElementException extends IllegalStateException {

	private static final long serialVersionUID = -1584845183435945634L;

	private final String detailMessage;

	@SuppressWarnings("unused")
	private final Throwable cause;

	public XmlIllegalElementException(String message, Throwable cause) {
		detailMessage = message;
		this.cause = cause;
	}

	@Override
	public String getMessage() {
		return detailMessage;
	}
}

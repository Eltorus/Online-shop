package by.epam.shop.dao.connectionpool;

public class ConnectionPoolException extends Exception {
	private static final long serialVersionUID = 2522466629926593348L;

	public ConnectionPoolException() {
		super();
	}

	public ConnectionPoolException(Exception e) {
		super(e);
	}

	public ConnectionPoolException(String message) {
		super(message);
	}

	public ConnectionPoolException(String message, Exception e) {
		super(message, e);
	}
}

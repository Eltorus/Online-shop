package by.epam.shop.service.exception;

public class ServiceException extends Exception {

	private static final long serialVersionUID = 1797312916102244833L;

	public ServiceException() {
		super();
	}
	
	public ServiceException(String message) {
		super(message);
	}
	
	public ServiceException(Exception e) {
		super(e);
	}
	
	public ServiceException(String message, Exception e) {
		super(message, e);
	}
}
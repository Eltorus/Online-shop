package _java._ee._02._dao.exception;

public class DAOException extends Exception{

	private static final long serialVersionUID = 1717649535770763836L;

	public DAOException() {
		super();
	}
	
	public DAOException(String message) {
		super(message);
	}
	
	public DAOException(Exception e) {
		super(e);
	}
	
	public DAOException(String message, Exception e) {
		super(message, e);
	}
}

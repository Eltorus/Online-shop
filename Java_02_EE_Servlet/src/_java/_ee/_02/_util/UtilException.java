package _java._ee._02._util;

public class UtilException extends Exception{
	private static final long serialVersionUID = -8171333232079479356L;

	UtilException(){
		super();
	}
	
	UtilException(String message) {
		super(message);
	}
	
	UtilException(Exception e) {
		super(e);
	}
	
	UtilException(String message, Exception e) {
		super(message, e);
	}
	
}

package _java._ee._02._service.validation;

public final class Validation {
	private Validation() {}
	
	public static boolean isStringEmpty(String line) {
		if(line == null || line.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isBytesNull(byte[] bytes) {
		if(bytes == null) {
			return true;
		} else {
			return false;
		}
	}
}

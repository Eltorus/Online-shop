package by.epam.shop.util;

public final class MessageGenerator {
    private MessageGenerator() {}
    
    public static String generateError(int code) {
	return "?error="+code;
    }
    
    public static String generateSuccess(int code) {
	return "?success="+code;
    }
}

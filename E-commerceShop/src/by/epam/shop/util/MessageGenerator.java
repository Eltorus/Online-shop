package by.epam.shop.util;

public final class MessageGenerator {
    private MessageGenerator() {}
    
    public static String generateError(String code) {
	return "?error="+code;
    }
    
    public static String generateSuccess(String code) {
	return "?success="+code;
    }
}

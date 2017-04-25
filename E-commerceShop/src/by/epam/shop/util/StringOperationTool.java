package by.epam.shop.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringOperationTool {
    private StringOperationTool() {
    }

    public static boolean isStringValid(String line){
	if(line == null || line.trim().isEmpty()) {
	    return false;
	}
	Pattern pattern = Pattern.compile("\\s");
	Matcher matcher = pattern.matcher(line.trim());
	
	return !matcher.find();
    }
}

package by.epam.shop.util;

public class NumberOperationTool {
    private NumberOperationTool() {
    }

    /*public static double getRoundedDouble(double d) {
	return Math.round(d * 100.0) / 100.0;
    }*/

    public static int getIntFromString(String line) {
	if (line == null || line.trim().isEmpty()) {
	    return 0;
	}

	try {
	    int number = Integer.parseInt(line);
	    return number;
	} catch (NumberFormatException e) {
	    return 0;
	}
    }
    
    public static double getDoubleFromString(String line) throws UtilException {
	if (line == null || line.isEmpty() || line.trim().isEmpty()) {
	    return 0;
	}
	try {
	    double number = Double.parseDouble(line);
	    return number;
	} catch (NumberFormatException e) {
	    throw new UtilException("Cannot parse double");
	}
    }
    
}

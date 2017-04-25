package by.epam.shop.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateFormatter {
    private DateFormatter() {
    }

    public static String convertDateToString(Date date) {
	if (date == null) {
	    return null;
	}

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	return sdf.format(date);
    }

    public static Date convertStringToDate(String line) throws UtilException {
	if(line == null || line.trim().isEmpty()) {
	    return null;
	}

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	try {
	    return sdf.parse(line);
	} catch (ParseException e) {
	    throw new UtilException(e);
	}
    }

    public static Date getDateRusFormat(String line) throws UtilException {
	if(!StringOperationTool.isStringValid(line)) {
	    return null;
	}
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
	try {
	    return sdf.parse(line);
	} catch (ParseException e) {
	    throw new UtilException(e);
	}
    }
}

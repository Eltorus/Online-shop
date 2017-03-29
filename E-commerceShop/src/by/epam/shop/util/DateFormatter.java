package by.epam.shop.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateFormatter {
	private DateFormatter() {}

	public static Timestamp getTimeStamp(String s) throws UtilException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		Timestamp ts = null;
		
		try {
			Date date = sdf.parse(s);
			ts = new Timestamp(date.getTime());
		} catch (ParseException e) {
			throw new UtilException(e);
		}
		
		return ts;
	}
}

package by.epam.shop.util.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import by.epam.shop.util.DateFormatter;
import by.epam.shop.util.UtilException;

public class DateFormatterTest {
    @Test
    public void convertStringToDateTest() {
	String line = "1994-12-01 12:02:22";
	try {
	    assertTrue(DateFormatter.convertStringToDate(line) != null);
	} catch (UtilException e) {
	    fail("Appropriate value test failed");
	}
    }

    @Test
    public void convertStringToDateNullTest() {
	try {
	    assertNull(DateFormatter.convertStringToDate(null));
	} catch (UtilException e) {
	    fail("Null test failed");
	}
    }

    @Test(expected = UtilException.class)
    public void convertStringToDateInapropValueTest() throws UtilException {
	String line = "aaaa";
	DateFormatter.convertStringToDate(line);
    }

    @Test
    public void convertDateToStringTest() {
	Date date = new Date();
	String line = DateFormatter.convertDateToString(date);
	assertTrue(line != null && !line.isEmpty());
    }

    @Test
    public void convertDateToStringNullTest() {
	Date date = null;
	assertNull(DateFormatter.convertDateToString(date));
    }

    @Test
    public void convertStringToDateRusTest() {
	String line = "10.12.2006";
	try {
	    assertTrue(DateFormatter.getDateRusFormat(line) != null);
	} catch (UtilException e) {
	    fail();
	}
    }

    @Test
    public void convertStringToDateRusNullTest() {
	try {
	    assertNull(DateFormatter.getDateRusFormat(null));
	} catch (UtilException e) {
	    fail();
	}
    }

    @Test(expected = UtilException.class)
    public void convertStringToDateRusInapropValueTest() throws UtilException {
	String line = "aaaa";
	DateFormatter.getDateRusFormat(line);
	
    }
}

package by.epam.shop.util.test;

import static org.junit.Assert.*;

import org.junit.Test;

import by.epam.shop.util.StringOperationTool;

public class StringOperationToolTest {
    
    @Test
    public void checkNullInput() {
	assertFalse(StringOperationTool.isStringValid(null));
    }
    
    @Test
    public void checkInvalidInput() {
	assertFalse(StringOperationTool.isStringValid("f   g"));
    }
    
    @Test
    public void checkValidForTrimInput() {
	assertTrue(StringOperationTool.isStringValid(" fg  "));
    }
    
    @Test
    public void checkEmptyInput() {
	assertFalse(StringOperationTool.isStringValid(""));
    }
    
    @Test
    public void checkValidInput() {
	assertTrue(StringOperationTool.isStringValid("Hello"));
    }
}

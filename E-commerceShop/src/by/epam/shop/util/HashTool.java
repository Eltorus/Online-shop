package by.epam.shop.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class HashTool {
    private HashTool() {
    }

    /*
     * Compute hash (SHA-256) of String object
     * 
     * @param String
     * 
     * @return String 
     */
    public static String hashLine(String line) throws UtilException{
	StringBuffer hexString = null;

	try {
	    MessageDigest digest = MessageDigest.getInstance("SHA-256");
	    byte[] hash = digest.digest(line.getBytes("UTF-8"));

	    hexString = new StringBuffer();
	    for (byte x : hash) {
		String hex = Integer.toHexString(0xff & x);

		if (hex.length() == 1) {
		    hexString.append('0');
		}

		hexString.append(hex);
	    }

	} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
	    throw new UtilException(e);
	}
	return hexString.toString();
    }
}

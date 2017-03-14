package _java._ee._02._util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class HashTool {
	private HashTool() {
	}

	public static String hashLine(String line) throws UtilException {
		StringBuffer hexString = null;
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(line.getBytes("UTF-8"));
			hexString = new StringBuffer();
			for(byte x: hash) {
				String hex = Integer.toHexString(0xff & x);
				if(hex.length() == 1) {
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

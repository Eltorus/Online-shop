package by.epam.shop.util;

public class RoundDouble {
	private RoundDouble() {}
	
	public static double getRoundedDouble(double d) {
		return Math.round(d * 100.0) / 100.0;
	}
}

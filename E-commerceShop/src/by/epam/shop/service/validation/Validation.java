package by.epam.shop.service.validation;

import by.epam.shop.bean.User;

public final class Validation {
	private Validation() {}
	
	public static boolean isStringEmpty(String line) {
		if(line == null || line.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isBytesNull(byte[] bytes) {
		if(bytes == null) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean userIsValid(User user) {
		///Конкретизировать, возможно добавить проверку по всем полям
		boolean result = true;
		if(user == null) {
			result =  false;
		}
		if(Validation.isStringEmpty(user.getEmail()) || Validation.isStringEmpty(user.getPasswordHash())) {
			result = false;
		}
		return result;
	}
	
	public static boolean checkDate(String date) {
		////
		return false;
	}

}

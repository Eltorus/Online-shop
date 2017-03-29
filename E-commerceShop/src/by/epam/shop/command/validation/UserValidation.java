package by.epam.shop.command.validation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.shop.bean.User;
import by.epam.shop.command.AttributeList;

public class UserValidation {
	public static boolean isUserLoged(HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute(AttributeList.ATTR_USER);
		if(user == null) {
		    return false;
		}
		if(user.getEmail() == null || user.getEmail().isEmpty() || user.getPasswordHash() == null || user.getPasswordHash().isEmpty()) {
		    return false;
		}
		return true;
	}
	
	public static boolean isUserAdmin(HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute(AttributeList.ATTR_USER);
		return user.isIs_admin();
	}
}

package by.epam.shop.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.shop.bean.User;

public class UserValidation {
	public static boolean isUserLoged(HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute(AttributeList.ATTR_USER);
		if(user !=null) {
			return true;
		}
		return false;
	}
	
	public static boolean isUserAdmin(HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute(AttributeList.ATTR_USER);
		return user.isIs_admin();
	}
}

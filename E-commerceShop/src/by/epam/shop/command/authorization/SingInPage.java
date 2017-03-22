package by.epam.shop.command.authorization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.shop.bean.User;
import by.epam.shop.command.AttributeList;
import by.epam.shop.command.Command;
import by.epam.shop.command.exception.CommandException;
import by.epam.shop.controller.PageList;

public class SingInPage implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		User user = (User) request.getSession().getAttribute(AttributeList.ATTR_USER);
		if(user == null) {
			return PageList.PG_SIGNIN;
		} else {
			return PageList.PG_INDEX;
		}
	}

}

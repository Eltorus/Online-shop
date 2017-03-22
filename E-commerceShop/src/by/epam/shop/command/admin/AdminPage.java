package by.epam.shop.command.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.shop.bean.User;
import by.epam.shop.command.AttributeList;
import by.epam.shop.command.Command;
import by.epam.shop.command.exception.CommandException;
import by.epam.shop.controller.PageList;

public class AdminPage implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String page = null;
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(AttributeList.ATTR_USER);
		if(user != null) {
			if(user.isIs_admin()) {
				page = PageList.PG_ADMIN;
			}
		}
		return page;
	}

}

	package by.epam.shop.command.authorization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.shop.command.Command;
import by.epam.shop.command.exception.CommandException;
import by.epam.shop.controller.PageList;

public class LogOut implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		HttpSession session = request.getSession();
		session.invalidate();

		return PageList.PG_PROFILE;
	}

}

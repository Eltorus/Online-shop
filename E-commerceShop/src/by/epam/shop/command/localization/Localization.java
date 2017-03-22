package by.epam.shop.command.localization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.shop.command.AttributeList;
import by.epam.shop.command.Command;
import by.epam.shop.command.ParameterList;
import by.epam.shop.command.exception.CommandException;
import by.epam.shop.controller.PageList;

public class Localization implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String language = request.getParameter(ParameterList.CMD_LOCAL);
		request.getSession(true).setAttribute(AttributeList.ATTR_LOCAL, language);
		return PageList.PG_INDEX;	
	}

}

package by.epam.shop.command.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.shop.command.Command;
import by.epam.shop.command.UserValidation;
import by.epam.shop.command.exception.CommandException;
import by.epam.shop.controller.PageList;

public class AdminPage implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		if(!UserValidation.isUserLoged(request, response)) {
			return PageList.PG_SIGNIN;
		}
		if(!UserValidation.isUserAdmin(request, response)) {
			throw new CommandException("Wrong command");
		}
		//дописать adminmainpage
		return null;
	}

}

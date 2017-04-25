package by.epam.shop.command.authorization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.shop.command.Command;
import by.epam.shop.command.exception.CommandException;
import by.epam.shop.util.PageList;

public class LogOut implements Command {

    /* Invalidate session for deleting cart and user objects
     * @param javax.servlet.http.HttpServletRequest
     * @param javax.servlet.http.HttpServletResponse
     * @throws by.epam.shop.command.exception.CommandException
     * @return String page, which will be passed to client
     * */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
	request.getSession().invalidate();

	return PageList.PG_PROFILE;
    }

}

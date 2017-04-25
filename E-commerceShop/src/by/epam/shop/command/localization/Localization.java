package by.epam.shop.command.localization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.shop.command.AttributeList;
import by.epam.shop.command.Command;
import by.epam.shop.command.ParameterList;
import by.epam.shop.command.exception.CommandException;
import by.epam.shop.util.PageList;

public class Localization implements Command {
    
    /* Get requested locale , and set as an attribute in session
     * @param javax.servlet.http.HttpServletRequest
     * @param javax.servlet.http.HttpServletResponse
     * @throws by.epam.shop.command.exception.CommandException
     * @return String page, which will be passed to client
     * */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
	String language = request.getParameter(ParameterList.LOCAL);
	request.getSession(true).setAttribute(AttributeList.ATTR_LOCAL, language);
	return PageList.PG_INDEX;
    }

}

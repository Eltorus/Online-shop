package _java._ee._02._command.localization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import _java._ee._02._command.AttributeList;
import _java._ee._02._command.Command;
import _java._ee._02._command.ParameterList;
import _java._ee._02._command.exception.CommandException;
import _java._ee._02._controller.PageList;

public class Localization implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String language = request.getParameter(ParameterList.CMD_LOCAL);
		request.getSession(true).setAttribute(AttributeList.ATTR_LOCAL, language);
		return PageList.PG_INDEX;	
	}

}

package _java._ee._02._command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import _java._ee._02._command.exception.CommandException;

public interface Command {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException;
}

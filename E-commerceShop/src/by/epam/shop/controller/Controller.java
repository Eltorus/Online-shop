package by.epam.shop.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.epam.shop.command.Command;
import by.epam.shop.command.CommandList;
import by.epam.shop.command.CommandProvider;
import by.epam.shop.command.exception.CommandException;
import by.epam.shop.util.PageList;

public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final static Logger logger = Logger.getLogger(Controller.class);

    public Controller() {
	super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	RequestDispatcher rd = getServletContext().getRequestDispatcher(doProcess(request, response));
	rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	response.sendRedirect(request.getContextPath() + doProcess(request, response));
    }

    private String doProcess(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	CommandProvider provider = CommandProvider.getInstance();
	Command command = null;
	String cmdName = request.getParameter(CommandList.CMD);
	String page = null;
	try {
	    command = provider.getCommand(cmdName);
	    page = command.execute(request, response);
	} catch (CommandException e) {
	    logger.error(e);
	    return PageList.PG_ERROR;
	}
	return page;
    }
}

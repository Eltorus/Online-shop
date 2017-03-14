package _java._ee._02._controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import _java._ee._02._command.Command;
import _java._ee._02._command.ParameterList;
import _java._ee._02._command.CommandProvider;
import _java._ee._02._command.exception.CommandException;

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public Controller() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		String page = doProcess(request, response);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(page);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		String page = doProcess(request, response);
		response.sendRedirect(request.getContextPath()+page);
	}
	
	private String doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CommandProvider provider = CommandProvider.getInstance();
		Command command = null;
		String cmdName = request.getParameter(ParameterList.CMD);
		String page = null;
		try { 
			command = provider.getCommand(cmdName);
			page = command.execute(request, response);
		} catch(CommandException e) {
			page = PageList.PG_ERROR;
		}
		return page;
	}
}

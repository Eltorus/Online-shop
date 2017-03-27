package by.epam.shop.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import by.epam.shop.command.Command;
import by.epam.shop.command.CommandProvider;
import by.epam.shop.command.ParameterList;
import by.epam.shop.command.exception.CommandException;

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
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
		response.sendRedirect(request.getContextPath()+doProcess(request, response));
	}
	
	private String doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		CommandProvider provider = CommandProvider.getInstance();
		Command command = null;
		String cmdName = request.getParameter(ParameterList.CMD);
		String page = null;
		try { 
			command = provider.getCommand(cmdName);
			page = command.execute(request, response);
		} catch(CommandException e) {
			e.printStackTrace();
			request.getSession().setAttribute("error", e.getMessage());
			//Logger.getLogger(Controller.class).error(e);
			//request.getHeader("referer");
			return PageList.PG_INDEX;
		}
		return page;
	}
}

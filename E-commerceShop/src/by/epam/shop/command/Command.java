package by.epam.shop.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.shop.command.exception.CommandException;

public interface Command {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException;
}

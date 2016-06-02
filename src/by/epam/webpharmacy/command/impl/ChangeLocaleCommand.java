package by.epam.webpharmacy.command.impl;

import by.epam.webpharmacy.command.Command;
import by.epam.webpharmacy.command.CommandException;
import by.epam.webpharmacy.command.util.Parameter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Class {@code ChangeLocaleCommand} is an implementation of {@see Command}
 * for changing locale for the session
 */
public class ChangeLocaleCommand implements Command {
    /**
     * Handles request to the servlet by changing the locale for the session
     * @param request request from the servlet, containing the desired locale
     * @throws CommandException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String locale = request.getParameter(Parameter.LOCALE.getName());
        HttpSession session = request.getSession();
        session.setAttribute(Parameter.LOCALE.getName(),locale);
        try {
            response.sendRedirect(request.getHeader(Parameter.REFERER.getName()));
        } catch (IOException e) {
            throw new CommandException("Can't get referer header from request", e);
        }
    }
}

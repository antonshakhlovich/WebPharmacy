package by.epam.webpharmacy.command.impl;

import by.epam.webpharmacy.command.Command;
import by.epam.webpharmacy.command.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Class {@code ChangeLocaleCommand} is an implementation of {@see Command}
 * for changing locale for the session
 */
public class ChangeLocaleCommand implements Command {
    private static final String PARAM_LOCALE = "locale";

    /**
     * Handles request to the servlet by changing the locale for the session
     * @param request request from the servlet, containing the desired locale
     * @return null
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String locale = request.getParameter(PARAM_LOCALE);
        System.out.println(locale);
        HttpSession session = request.getSession();
        session.setAttribute(PARAM_LOCALE,locale);
        return request.getParameter("from");
    }
}

package by.epam.webpharmacy.command.impl;

import by.epam.webpharmacy.command.Command;
import by.epam.webpharmacy.command.CommandException;
import by.epam.webpharmacy.util.Parameter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Class {@code LoginCommand} is an authorized-only implementation of {@see Command}
 * for logging out any user
 */
public class LogoutCommand implements Command{

    /**
     * Handles request to the servlet by trying to logout a user
     *
     * @param request request from the servlet, containing user's login and password
     * @return path to the same page, and set user parameter to null
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        session.setAttribute(Parameter.USER.getName(),null);
        return "/index.jsp";
    }
}

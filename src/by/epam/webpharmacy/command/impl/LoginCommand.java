package by.epam.webpharmacy.command.impl;

import by.epam.webpharmacy.command.Command;
import by.epam.webpharmacy.command.CommandException;
import by.epam.webpharmacy.entity.User;
import by.epam.webpharmacy.service.ServiceException;
import by.epam.webpharmacy.service.user.UserService;
import by.epam.webpharmacy.service.user.UserServiceFactory;
import by.epam.webpharmacy.service.user.UserServiceName;
import by.epam.webpharmacy.command.util.Parameter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Class {@code LoginCommand} is a guest-only implementation of {@see Command}
 * for signing in a user with given credentials. It also put a Shopping cart to session scope
 * if login was successful.
 */
public class LoginCommand implements Command {

    private static UserService userService = UserServiceFactory.getInstance().getService(UserServiceName.USER_SERVICE);

    /**
     * Handles request to the servlet by trying to log in a user with given credentials
     *
     * @param request request from the servlet, containing user's login and password
     * @return path to the same page, and set login parameters to the current session
     * @throws CommandException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        User user;
        String login = request.getParameter(Parameter.LOGIN.getName());
        String password = request.getParameter(Parameter.PASSWORD.getName());

        try {
            user = userService.loginUser(login, password);
        } catch (ServiceException e) {
            throw new CommandException("Can't get user from UserService layer", e);
        }
        HttpSession session = request.getSession();
        if (user != null) {
            session.setAttribute(Parameter.USER.getName(), user);
            session.setAttribute(Parameter.LOGIN_FAILED.getName(), Boolean.FALSE);
        } else {
            session.setAttribute(Parameter.LOGIN_FAILED.getName(), Boolean.TRUE);
        }
        try {
            response.sendRedirect(request.getHeader(Parameter.REFERER.getName()));
        } catch (IOException e) {
            throw new CommandException(e);
        }

    }
}

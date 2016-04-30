package by.epam.webpharmacy.command.impl;

import by.epam.webpharmacy.command.Command;
import by.epam.webpharmacy.command.CommandException;
import by.epam.webpharmacy.entity.User;
import by.epam.webpharmacy.service.ServiceException;
import by.epam.webpharmacy.service.UserService;
import by.epam.webpharmacy.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Class {@code LoginCommand} is a guest-only implementation of {@see Command}
 * for signing in a user with given credentials
 */
public class LoginCommand implements Command {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static final String PARAM_NAME_USER = "user";

    private static UserService userService = UserServiceImpl.getInstance();

    /**
     * Handles request to the servlet by trying to log in a user with given credentials
     *
     * @param request request from the servlet, containing user's login and password
     * @return path to the same page, and set login parameters to the current session
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        User user;
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);
        try {
            user = userService.loginUser(login, password);
        } catch (ServiceException e) {
            throw new CommandException("Can't get user from UserService layer", e);
        }
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute(PARAM_NAME_USER, user);

        }
        return "";
    }
}

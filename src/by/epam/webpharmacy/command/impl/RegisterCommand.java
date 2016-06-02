package by.epam.webpharmacy.command.impl;

import by.epam.webpharmacy.command.Command;
import by.epam.webpharmacy.command.CommandException;
import by.epam.webpharmacy.command.util.JspPage;
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
 * Class {@code RegisterCommand} is a guest-only implementation of {@see Command}
 * for registration of a new user
 */
public class RegisterCommand implements Command{

    private static UserService userService = UserServiceFactory.getInstance().getService(UserServiceName.USER_SERVICE);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        boolean result;
        User user = new User();
        user.setLogin(request.getParameter(Parameter.LOGIN.getName()));
        user.setPassword(request.getParameter(Parameter.PASSWORD.getName()));
        user.setEmail(request.getParameter(Parameter.EMAIL.getName()));
        user.setFirstName(request.getParameter(Parameter.FIRST_NAME.getName()));
        user.setLastName(request.getParameter(Parameter.LAST_NAME.getName()));
        user.setPhoneNumber(request.getParameter(Parameter.PHONE_NUMBER.getName()));
        user.setCity(request.getParameter(Parameter.CITY.getName()));
        user.setAddress(request.getParameter(Parameter.ADDRESS.getName()));
        try {
            result = userService.registerUser(user);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        HttpSession session = request.getSession();
        if (result) {
            try {
                User registeredUser = userService.loginUser(user.getLogin(), user.getPassword());
                User currentUser = (User) session.getAttribute(Parameter.USER.getName());
                if (currentUser == null) {
                    session.setAttribute(Parameter.USER.getName(), registeredUser);
                    try {
                        response.sendRedirect(JspPage.INDEX.getPath());
                    } catch (IOException e) {
                        throw new CommandException(e);
                    }
                } else {
                    session.setAttribute(Parameter.SUCCESS_MESSAGE.getName(),Boolean.TRUE);
                    session.setAttribute(Parameter.USER_NAME.getName(),registeredUser.getLogin());
                }
            } catch (ServiceException e) {
                throw new CommandException(e);
            }
        } else {
            session.setAttribute(Parameter.ERROR_MESSAGE.getName(),Boolean.TRUE);
        }
        try {
            response.sendRedirect(JspPage.REGISTER.getPath());
        } catch (IOException e) {
            throw new CommandException(e);
        }
    }
}

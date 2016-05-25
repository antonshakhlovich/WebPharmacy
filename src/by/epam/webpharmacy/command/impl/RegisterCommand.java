package by.epam.webpharmacy.command.impl;

import by.epam.webpharmacy.command.Command;
import by.epam.webpharmacy.command.CommandException;
import by.epam.webpharmacy.entity.User;
import by.epam.webpharmacy.service.ServiceException;
import by.epam.webpharmacy.service.UserService;
import by.epam.webpharmacy.service.impl.UserServiceImpl;
import by.epam.webpharmacy.util.JspPage;
import by.epam.webpharmacy.util.Parameter;

import javax.servlet.http.HttpServletRequest;

/**
 * Class {@code RegisterCommand} is a guest-only implementation of {@see Command}
 * for registration of a new user
 */
public class RegisterCommand implements Command{

    private static UserService userService = UserServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
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
        if (result) {
            try {
                request.getSession().setAttribute(Parameter.USER.getName(),userService.loginUser(user.getLogin(),user.getPassword()));
            } catch (ServiceException e) {
                throw new CommandException(e);
            }
            return JspPage.ROOT.getPath();
        } else {
            request.getSession().setAttribute(Parameter.ERROR_MESSAGE.getName(),Boolean.TRUE);
            return JspPage.REGISTER.getPath();
        }
    }
}

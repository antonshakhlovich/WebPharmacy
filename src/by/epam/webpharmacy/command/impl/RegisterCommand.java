package by.epam.webpharmacy.command.impl;

import by.epam.webpharmacy.command.Command;
import by.epam.webpharmacy.command.CommandException;
import by.epam.webpharmacy.service.ServiceException;
import by.epam.webpharmacy.service.UserService;
import by.epam.webpharmacy.service.impl.UserServiceImpl;
import by.epam.webpharmacy.util.Parameter;

import javax.servlet.http.HttpServletRequest;

/**
 * Class {@code RegisterCommand} is a guest-only implementation of {@see Command}
 * for registration of a new user
 */
public class RegisterCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String login = request.getParameter(Parameter.LOGIN.getName());
        String password = request.getParameter(Parameter.PASSWORD.getName());
        String email = request.getParameter(Parameter.EMAIL.getName());
        String firstName = request.getParameter(Parameter.FIRST_NAME.getName());
        String lastName = request.getParameter(Parameter.LAST_NAME.getName());
        String phoneNumber = request.getParameter(Parameter.PHONE_NUMBER.getName());
        String city = request.getParameter(Parameter.CITY.getName());
        String address = request.getParameter(Parameter.ADDRESS.getName());
        UserService userService = UserServiceImpl.getInstance();
        try {
            userService.registerUser(login, password, email, firstName, lastName, phoneNumber, city, address);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return "/index.jsp";
    }
}

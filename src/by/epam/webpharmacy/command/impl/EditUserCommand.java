package by.epam.webpharmacy.command.impl;

import by.epam.webpharmacy.command.Command;
import by.epam.webpharmacy.command.CommandException;
import by.epam.webpharmacy.command.util.Parameter;
import by.epam.webpharmacy.entity.User;
import by.epam.webpharmacy.service.ServiceException;
import by.epam.webpharmacy.service.user.UserService;
import by.epam.webpharmacy.service.user.UserServiceFactory;
import by.epam.webpharmacy.service.user.UserServiceName;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class {@code EditUserCommand} is an admin and manager only implementation of {@see Command}
 * for editing given item from catalog
 */
public class EditUserCommand implements Command {

    private static final UserService userService = UserServiceFactory.getInstance().getService(UserServiceName.USER_SERVICE);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        Long userId = Long.parseLong(request.getParameter(Parameter.USER_ID.getName()));
        User user = (User) request.getSession().getAttribute(Parameter.USER.getName());
        try {
            User editedUser = userService.selectUserById(user, userId);
            editedUser.setPassword(request.getParameter(Parameter.PASSWORD.getName()));
            editedUser.setEmail(request.getParameter(Parameter.EMAIL.getName()));
            editedUser.setFirstName(request.getParameter(Parameter.FIRST_NAME.getName()));
            editedUser.setLastName(request.getParameter(Parameter.LAST_NAME.getName()));
            editedUser.setPhoneNumber(request.getParameter(Parameter.PHONE_NUMBER.getName()));
            editedUser.setCity(request.getParameter(Parameter.CITY.getName()));
            editedUser.setAddress(request.getParameter(Parameter.ADDRESS.getName()));
            if (userService.updateUser(editedUser)){
                request.getSession().setAttribute(Parameter.SUCCESS_MESSAGE.getName(),Boolean.TRUE);
            } else {
                request.getSession().setAttribute(Parameter.ERROR_MESSAGE.getName(),Boolean.TRUE);
            }
            response.sendRedirect(request.getHeader(Parameter.REFERER.getName()));

        } catch (ServiceException | IOException e) {
            throw new CommandException(e);
        }


    }
}

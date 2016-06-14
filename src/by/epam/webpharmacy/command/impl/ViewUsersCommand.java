package by.epam.webpharmacy.command.impl;

import by.epam.webpharmacy.command.Command;
import by.epam.webpharmacy.command.CommandException;
import by.epam.webpharmacy.command.util.JspPage;
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
import java.util.List;

/**
 * Class {@code ViewUsersCommand} is an implementation of {@see Command}
 * for viewing all users or just filtered by login, email or id
 */
public class ViewUsersCommand implements Command {

    private static UserService userService = UserServiceFactory.getInstance().getService(UserServiceName.USER_SERVICE);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        int limit = Integer.parseInt(request.getParameter(Parameter.LIMIT.getName()));
        int offset = (Integer.parseInt(request.getParameter(Parameter.PAGE_NUMBER.getName())) - 1) * limit;
        try {
            int count = userService.countAllUsers();
            if (userService.countAllUsers() > 0) {
                List<User> userList = userService.selectAllUsers(offset, limit);
                request.setAttribute(Parameter.USERS.getName(), userList);
                request.setAttribute(Parameter.NUMBER_OF_USERS.getName(), count);
                request.setAttribute(Parameter.SUCCESS_MESSAGE.getName(), Boolean.TRUE);
            } else {
                request.setAttribute(Parameter.ERROR_MESSAGE.getName(), Boolean.TRUE);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        try {
            request.getRequestDispatcher(JspPage.VIEW_ALL_USERS.getPath()).forward(request, response);
        } catch (ServletException | IOException e) {
            throw new CommandException(e);
        }
    }
}

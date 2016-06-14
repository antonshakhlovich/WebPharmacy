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

/**
 * Class {@code ViewEditUserCommand} is an implementation of {@see Command}
 * for viewing page, where user profile can be edited
 */
public class ViewEditUserCommand implements Command {

    private static final UserService userService = UserServiceFactory.getInstance().getService(UserServiceName.USER_SERVICE);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        Long userId = Long.parseLong(request.getParameter(Parameter.USER_ID.getName()));
        User user = (User) request.getSession().getAttribute(Parameter.USER.getName());
        try {
            User editedUser = userService.selectUserById(user, userId);
            if (editedUser != null) {
                request.setAttribute(Parameter.USER.getName(),editedUser);
                request.getRequestDispatcher(JspPage.EDIT_USER.getPath()).forward(request,response);
            } else {
                response.sendRedirect(request.getHeader(Parameter.REFERER.getName()));
            }

        } catch (ServiceException | ServletException | IOException e) {
            e.printStackTrace();
        }

    }
}

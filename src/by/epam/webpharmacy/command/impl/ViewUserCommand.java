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
import javax.servlet.http.HttpSession;
import javax.swing.text.StringContent;
import java.io.IOException;

/**
 * Class {@code ViewUserCommand} is an admin and manager only implementation of {@see Command}
 * for searching users.
 */
public class ViewUserCommand implements Command {

    private static UserService userService = UserServiceFactory.getInstance().getService(UserServiceName.USER_SERVICE);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String email = request.getParameter(Parameter.EMAIL.getName());
        String login = request.getParameter(Parameter.LOGIN.getName());
        String stringUserId = request.getParameter(Parameter.USER_ID.getName());
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute(Parameter.USER.getName());
        User requestedUser = null;
        try {
            if (stringUserId != null) {
                long userId = Long.parseLong(stringUserId);
                requestedUser = userService.selectUserById(currentUser, userId);
            } else if (email != null) {
                requestedUser = userService.selectUserByEmail(currentUser, email);
            } else if (login != null) {
                requestedUser = userService.selectUserByLogin(currentUser, login);
            }
            if (requestedUser != null){
                request.setAttribute(Parameter.USER.getName(),requestedUser);
                request.getRequestDispatcher(JspPage.VIEW_USER.getPath()).forward(request,response);
            } else {
                response.sendRedirect(JspPage.INDEX.getPath());
            }

        } catch (ServiceException | ServletException | IOException e) {
            throw new CommandException(e);
        }
    }
}

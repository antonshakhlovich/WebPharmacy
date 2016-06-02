package by.epam.webpharmacy.command.impl;

import by.epam.webpharmacy.command.Command;
import by.epam.webpharmacy.command.CommandException;
import by.epam.webpharmacy.service.ServiceException;
import by.epam.webpharmacy.service.user.UserService;
import by.epam.webpharmacy.service.user.UserServiceFactory;
import by.epam.webpharmacy.service.user.UserServiceName;
import by.epam.webpharmacy.command.util.Parameter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class {@code BanUserCommand} is an admin-only implementation of {@see Command}
 * for banning or unbanning users
 */
public class BanUserCommand implements Command {

    private static UserService userService = UserServiceFactory.getInstance().getService(UserServiceName.USER_SERVICE);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        long id = Long.parseLong(request.getParameter(Parameter.ID.getName()));
        boolean banStatus = Boolean.parseBoolean(request.getParameter(Parameter.BAN_STATUS.getName()));
        try {
            userService.changeUserBanStatus(id, banStatus);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}

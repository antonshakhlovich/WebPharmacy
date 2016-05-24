package by.epam.webpharmacy.command.impl;

import by.epam.webpharmacy.command.Command;
import by.epam.webpharmacy.command.CommandException;
import by.epam.webpharmacy.service.ServiceException;
import by.epam.webpharmacy.service.UserService;
import by.epam.webpharmacy.service.impl.UserServiceImpl;
import by.epam.webpharmacy.util.Parameter;

import javax.servlet.http.HttpServletRequest;

/**
 * Class {@code BanUserCommand} is an admin-only implementation of {@see Command}
 * for banning or unbanning users
 */
public class BanUserCommand implements Command {

    private static UserService userService = UserServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        long id = Long.parseLong(request.getParameter(Parameter.ID.getName()));
        boolean banStatus = Boolean.parseBoolean(request.getParameter(Parameter.BAN_STATUS.getName()));
        try {
            userService.changeUserBanStatus(id, banStatus);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return "/";
    }
}

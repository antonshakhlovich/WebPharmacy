package by.epam.webpharmacy.command.impl;

import by.epam.webpharmacy.command.Command;
import by.epam.webpharmacy.command.CommandException;
import by.epam.webpharmacy.service.UserService;
import by.epam.webpharmacy.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;

/**
 * Class {@code ViewAddItemCommand} is an admin-only implementation of {@see Command}
 * for viewing page, where admin can add new items to the catalog, it also requests requisites from database
 */
public class ViewAddItemCommand implements Command {
    private static UserService userService = UserServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        return "/WEB-INF/jsp/add-item.jsp";
    }
}

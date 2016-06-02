package by.epam.webpharmacy.command.impl;

import by.epam.webpharmacy.command.Command;
import by.epam.webpharmacy.command.CommandException;
import by.epam.webpharmacy.service.item.ItemService;
import by.epam.webpharmacy.service.ServiceException;
import by.epam.webpharmacy.service.item.ItemServiceFactory;
import by.epam.webpharmacy.service.item.ItemServiceName;
import by.epam.webpharmacy.command.util.Parameter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class {@code DeleteItemCommand} is an admin and manager only implementation of {@see Command}
 * for deleting given item from catalog
 */
public class DeleteItemCommand implements Command {

    private static final ItemService itemService = ItemServiceFactory.getInstance().getService(ItemServiceName.ITEM_SERVICE);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        long id = Long.parseLong(request.getParameter(Parameter.ID.getName()));
        try {
            if (itemService.deleteItem(id)) {
                request.getSession().setAttribute(Parameter.SUCCESS_MESSAGE_DELETE.getName(), Boolean.TRUE);
            } else {
                request.getSession().setAttribute(Parameter.ERROR_MESSAGE_DELETE.getName(),Boolean.TRUE);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        try {
            response.sendRedirect(request.getHeader(Parameter.REFERER.getName()));
        } catch (IOException e) {
            throw new CommandException("Can't get referer header from request", e);
        }
    }
}

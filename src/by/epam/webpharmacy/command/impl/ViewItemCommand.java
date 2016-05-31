package by.epam.webpharmacy.command.impl;

import by.epam.webpharmacy.command.Command;
import by.epam.webpharmacy.command.CommandException;
import by.epam.webpharmacy.entity.Item;
import by.epam.webpharmacy.service.ItemService;
import by.epam.webpharmacy.service.ServiceException;
import by.epam.webpharmacy.service.impl.ItemServiceImpl;
import by.epam.webpharmacy.util.JspPage;
import by.epam.webpharmacy.util.Parameter;

import javax.servlet.http.HttpServletRequest;

/**
 * Class {@code ViewItemCommand} is an all-users implementation of {@see Command}
 * for viewing an item's info
 */
public class ViewItemCommand implements Command {

    private static ItemService itemService = ItemServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        Item item;
        long itemId = Long.parseLong(request.getParameter(Parameter.ID.getName()));
        try {
            item = itemService.selectItemById(itemId);
            request.setAttribute(Parameter.ITEM.getName(),item);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return ViewPageCommand.VIEW_PAGE + JspPage.VIEW_ITEM.name();
    }
}

package by.epam.webpharmacy.command.impl;

import by.epam.webpharmacy.command.Command;
import by.epam.webpharmacy.command.CommandException;
import by.epam.webpharmacy.command.util.JspPage;
import by.epam.webpharmacy.entity.Item;
import by.epam.webpharmacy.service.item.ItemService;
import by.epam.webpharmacy.service.ServiceException;
import by.epam.webpharmacy.service.item.ItemServiceFactory;
import by.epam.webpharmacy.service.item.ItemServiceName;
import by.epam.webpharmacy.command.util.Parameter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Class {@code ViewCatalogComman} is an registered users-only implementation of {@see Command}
 * for viewing catalog page, where user can buy items
 */
public class ViewCatalogCommand implements Command {

    private static final ItemService itemService = ItemServiceFactory.getInstance().getService(ItemServiceName.ITEM_SERVICE);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        int limit = Integer.parseInt(request.getParameter(Parameter.LIMIT.getName()));
        int offset = (Integer.parseInt(request.getParameter(Parameter.PAGE_NUMBER.getName())) - 1) * limit;
        try {
            List<Item> itemList = itemService.selectAllItems(offset, limit);
            request.setAttribute(Parameter.ITEMS.getName(), itemList);
            request.setAttribute(Parameter.NUMBER_OF_ITEMS.getName(), itemService.countAllItems());
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        try {
            request.getRequestDispatcher(JspPage.VIEW_CATALOG.getPath()).forward(request,response);
        } catch (ServletException | IOException e) {
            throw new CommandException(e);
        }
    }
}

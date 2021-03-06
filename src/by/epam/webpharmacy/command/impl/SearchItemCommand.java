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
 * Class {@code SearchItemCommand} is an all-users, except guests, implementation of {@see Command}
 * for searching items.
 */
public class SearchItemCommand implements Command {
    private static final ItemService itemService = ItemServiceFactory.getInstance().getService(ItemServiceName.ITEM_SERVICE);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        int limit = Integer.parseInt(request.getParameter(Parameter.LIMIT.getName()));
        String pageNumber = request.getParameter(Parameter.PAGE_NUMBER.getName());
        int offset = 0;
        if (!pageNumber.isEmpty()) {
            offset = (Integer.parseInt(pageNumber) - 1) * limit;
        } else {
            offset = 0;
            request.setAttribute(Parameter.PAGE_NUMBER.getName(),1);
        }
        String label = request.getParameter(Parameter.SEARCH.getName());
        try {
            if (label == null || label.length() == 0) {
                request.setAttribute(Parameter.ERROR_MESSAGE.getName(),Boolean.TRUE);
            } else {
                int searchResult = itemService.countItemsByLabel(label);
                if (searchResult == 0) {
                    request.setAttribute(Parameter.ERROR_MESSAGE.getName(), Boolean.TRUE);
                } else {
                    List<Item> itemList = itemService.selectItemsByLabel(label, offset, limit);
                    request.setAttribute(Parameter.SUCCESS_MESSAGE.getName(),Boolean.TRUE);
                    request.setAttribute(Parameter.ITEMS.getName(), itemList);
                    request.setAttribute(Parameter.NUMBER_OF_ITEMS.getName(), searchResult);
                }
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        try {
            request.getRequestDispatcher(JspPage.SEARCH_ITEM.getPath()).forward(request,response);
        } catch (ServletException | IOException e) {
            throw new CommandException(e);
        }
    }
}

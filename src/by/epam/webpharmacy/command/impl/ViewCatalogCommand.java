package by.epam.webpharmacy.command.impl;

import by.epam.webpharmacy.command.Command;
import by.epam.webpharmacy.command.CommandException;
import by.epam.webpharmacy.entity.Item;
import by.epam.webpharmacy.service.ItemService;
import by.epam.webpharmacy.service.ServiceException;
import by.epam.webpharmacy.service.impl.ItemServiceImpl;
import by.epam.webpharmacy.util.JspPage;
import by.epam.webpharmacy.util.Parameter;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Class {@code ViewCatalogComman} is an registered users-only implementation of {@see Command}
 * for viewing catalog page, where user can buy items
 */
public class ViewCatalogCommand implements Command {

    private static ItemService itemService = ItemServiceImpl.getInstance();
    private static final Logger logger = Logger.getLogger(ViewCatalogCommand.class);

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        int limit = Integer.parseInt(request.getParameter(Parameter.LIMIT.getName()));
        int offset = (Integer.parseInt(request.getParameter(Parameter.PAGE_NUMBER.getName())) - 1) * limit;
        try {
            List<Item> itemList = itemService.selectAllItems(offset, limit);
            request.setAttribute(Parameter.ITEMS.getName(), itemList);
            request.setAttribute(Parameter.NUMBER_OF_ITEMS.getName(), itemService.countAllItems());
            logger.debug(itemService.countAllItems());

        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return ViewPageCommand.PATH + JspPage.VIEW_CATALOG;
    }
}

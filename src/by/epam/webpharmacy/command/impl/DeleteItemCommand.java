package by.epam.webpharmacy.command.impl;

import by.epam.webpharmacy.command.Command;
import by.epam.webpharmacy.command.CommandException;
import by.epam.webpharmacy.service.ItemService;
import by.epam.webpharmacy.service.ServiceException;
import by.epam.webpharmacy.service.impl.ItemServiceImpl;
import by.epam.webpharmacy.util.JspPage;
import by.epam.webpharmacy.util.Parameter;

import javax.servlet.http.HttpServletRequest;
/**
 * Class {@code DeleteItemCommand} is an admin and manager only implementation of {@see Command}
 * for deleting given item from catalog
 */
public class DeleteItemCommand implements Command {

    private static final ItemService itemService = ItemServiceImpl.getInstance();
    private static final String VIEW_CATALOG = "/Controller?command=view_catalog&page_number=1&limit=10";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        long id = Long.parseLong(request.getParameter(Parameter.ID.getName()));
        try {
            if (itemService.deleteItem(id)) {
                request.getSession().setAttribute(Parameter.SUCCESS_MESSAGE.getName(), Boolean.TRUE);
            } else {
                request.getSession().setAttribute(Parameter.ERROR_MESSAGE.getName(),Boolean.TRUE);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return VIEW_CATALOG;
    }
}

package by.epam.webpharmacy.command.impl;

import by.epam.webpharmacy.command.Command;
import by.epam.webpharmacy.command.CommandException;
import by.epam.webpharmacy.command.CommandName;
import by.epam.webpharmacy.service.OrderService;
import by.epam.webpharmacy.service.ServiceException;
import by.epam.webpharmacy.service.impl.OrderServiceImpl;
import by.epam.webpharmacy.util.Parameter;

import javax.servlet.http.HttpServletRequest;

/**
 * Class {@code RemoveItemFromOrderCommand} is animplementation of {@see Command}
 * for removing from the current order an earlier added item
 */
public class RemoveItemFromOrderCommand implements Command {

    private static OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        long orderId = Long.parseLong(request.getParameter(Parameter.ORDER_ID.getName()));
        long itemId = Long.parseLong(request.getParameter(Parameter.ITEM_ID.getName()));
        try {
            orderService.removeItemFromOrder(itemId, orderId);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return ViewPageCommand.VIEW_COMMAND + CommandName.VIEW_SHOPPING_CART;


    }
}

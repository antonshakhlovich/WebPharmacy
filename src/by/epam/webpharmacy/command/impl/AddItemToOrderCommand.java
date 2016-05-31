package by.epam.webpharmacy.command.impl;

import by.epam.webpharmacy.command.Command;
import by.epam.webpharmacy.command.CommandException;
import by.epam.webpharmacy.command.CommandName;
import by.epam.webpharmacy.entity.User;
import by.epam.webpharmacy.service.OrderService;
import by.epam.webpharmacy.service.ServiceException;
import by.epam.webpharmacy.service.impl.OrderServiceSQLImpl;
import by.epam.webpharmacy.util.Parameter;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Class {@code AddItemToOrderCommand} is an implementation of {@see Command}
 * for adding a chosen item to current order.
 */
public class AddItemToOrderCommand implements Command {
    private static OrderService orderService = OrderServiceSQLImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        User user = (User) request.getSession().getAttribute(Parameter.USER.getName());
        long itemId = Long.parseLong(request.getParameter(Parameter.ITEM_ID.getName()));
        int quantity = Integer.parseInt(request.getParameter(Parameter.QUANTITY.getName()));
        if (Boolean.parseBoolean(request.getParameter(Parameter.CHANGE_QUANTITY.getName()))) {
            int oldQuantity = Integer.parseInt(request.getParameter(Parameter.OLD_QUANTITY.getName()));
            quantity = quantity - oldQuantity;
        }
        try {
            long orderId = orderService.selectShoppingCart(user.getId()).getId();
            orderService.addItemToOrder(itemId, quantity, orderId);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return ViewPageCommand.VIEW_COMMAND + CommandName.VIEW_SHOPPING_CART;


    }
}

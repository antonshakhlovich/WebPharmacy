package by.epam.webpharmacy.command.impl;

import by.epam.webpharmacy.command.Command;
import by.epam.webpharmacy.command.CommandException;
import by.epam.webpharmacy.entity.User;
import by.epam.webpharmacy.service.order.OrderService;
import by.epam.webpharmacy.service.ServiceException;
import by.epam.webpharmacy.service.order.OrderServiceFactory;
import by.epam.webpharmacy.service.order.OrderServiceName;
import by.epam.webpharmacy.command.util.Parameter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Class {@code AddItemToOrderCommand} is an implementation of {@see Command}
 * for adding a chosen item to current order.
 */
public class AddItemToOrderCommand implements Command {
    private static OrderService orderService = OrderServiceFactory.getInstance().getService(OrderServiceName.ORDER_SERVICE);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        User user = (User) request.getSession().getAttribute(Parameter.USER.getName());
        long itemId = Long.parseLong(request.getParameter(Parameter.ITEM_ID.getName()));
        int quantity = Integer.parseInt(request.getParameter(Parameter.QUANTITY.getName()));
        if (Boolean.parseBoolean(request.getParameter(Parameter.CHANGE_QUANTITY.getName()))) {
            int oldQuantity = Integer.parseInt(request.getParameter(Parameter.OLD_QUANTITY.getName()));
            quantity = quantity - oldQuantity;
        }
        try {
            long orderId = orderService.selectShoppingCart(user.getId()).getId();
            HttpSession session = request.getSession();
            if (orderService.addItemToOrder(itemId, quantity, orderId)) {
                session.setAttribute(Parameter.SUCCESS_MESSAGE.getName(), Boolean.TRUE);
            } else {
                session.setAttribute(Parameter.ERROR_MESSAGE.getName(), Boolean.TRUE);
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

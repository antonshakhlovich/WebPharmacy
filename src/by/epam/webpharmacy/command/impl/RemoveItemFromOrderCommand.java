package by.epam.webpharmacy.command.impl;

import by.epam.webpharmacy.command.Command;
import by.epam.webpharmacy.command.CommandException;
import by.epam.webpharmacy.service.order.OrderService;
import by.epam.webpharmacy.service.ServiceException;
import by.epam.webpharmacy.service.order.OrderServiceFactory;
import by.epam.webpharmacy.service.order.OrderServiceName;
import by.epam.webpharmacy.command.util.Parameter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class {@code RemoveItemFromOrderCommand} is animplementation of {@see Command}
 * for removing from the current order an earlier added item
 */
public class RemoveItemFromOrderCommand implements Command {

    private static OrderService orderService = OrderServiceFactory.getInstance().getService(OrderServiceName.ORDER_SERVICE);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        long orderId = Long.parseLong(request.getParameter(Parameter.ORDER_ID.getName()));
        long itemId = Long.parseLong(request.getParameter(Parameter.ITEM_ID.getName()));
        try {
            orderService.removeItemFromOrder(itemId, orderId);
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

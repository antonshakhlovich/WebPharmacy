package by.epam.webpharmacy.command.impl;

import by.epam.webpharmacy.command.Command;
import by.epam.webpharmacy.command.CommandException;
import by.epam.webpharmacy.command.util.JspPage;
import by.epam.webpharmacy.service.order.OrderService;
import by.epam.webpharmacy.service.ServiceException;
import by.epam.webpharmacy.service.order.OrderServiceFactory;
import by.epam.webpharmacy.service.order.OrderServiceName;
import by.epam.webpharmacy.command.util.Parameter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class {@code SubmitOrderCommand} is an implementation of {@see Command}
 * for submitting the current order
 */
public class SubmitOrderCommand implements Command{

    private static OrderService orderService = OrderServiceFactory.getInstance().getService(OrderServiceName.ORDER_SERVICE);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        long orderId = Long.parseLong(request.getParameter(Parameter.ORDER_ID.getName()));
        try {
            if (orderService.submitOrder(orderId)) {
                request.getSession().setAttribute(Parameter.SUCCESS_MESSAGE.getName(),Boolean.TRUE);
            } else{
                request.getSession().setAttribute(Parameter.ERROR_MESSAGE.getName(),Boolean.TRUE);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        try {
            response.sendRedirect(JspPage.VIEW_ORDERS.getPath());
        } catch (IOException e) {
            throw new CommandException("Can't get referer header from request", e);
        }

    }
}

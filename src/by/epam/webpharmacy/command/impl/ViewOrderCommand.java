package by.epam.webpharmacy.command.impl;

import by.epam.webpharmacy.command.Command;
import by.epam.webpharmacy.command.CommandException;
import by.epam.webpharmacy.command.util.JspPage;
import by.epam.webpharmacy.command.util.Parameter;
import by.epam.webpharmacy.entity.Order;
import by.epam.webpharmacy.entity.OrderStatus;
import by.epam.webpharmacy.entity.User;
import by.epam.webpharmacy.service.ServiceException;
import by.epam.webpharmacy.service.order.OrderService;
import by.epam.webpharmacy.service.order.OrderServiceFactory;
import by.epam.webpharmacy.service.order.OrderServiceName;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Class {@code ViewOrderCommand} is a non-guest implementation of {@see Command}
 * for viewing given order
 */

public class ViewOrderCommand implements Command {

    private static OrderService orderService = OrderServiceFactory.getInstance().getService(OrderServiceName.ORDER_SERVICE);
    private static final String VIEW_ORDERS = "/Controller?command=view-orders&is_canceled=";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        long orderId = Long.parseLong(request.getParameter(Parameter.ID.getName()));
        User user = (User) request.getSession().getAttribute(Parameter.USER.getName());
        String isCanceled = request.getParameter(Parameter.IS_CANCELED.getName());

        if (isCanceled == null) {
            isCanceled = Boolean.FALSE.toString();
        }
        try {
            Order order = orderService.selectOrderByOrderId(orderId, user);
            if (order == null) {
                request.getSession().setAttribute(Parameter.ERROR_MESSAGE.getName(), Boolean.TRUE);
                response.sendRedirect(VIEW_ORDERS + request.getParameter(Parameter.IS_CANCELED.getName()));
            } else if (order.getStatus().equalsIgnoreCase(Parameter.ACCESS_DENIED.getName())) {
                request.getSession().setAttribute(Parameter.ACCESS_DENIED.getName(), Boolean.TRUE);
                response.sendRedirect(VIEW_ORDERS + isCanceled);
            } else {
                request.setAttribute(Parameter.ORDER.getName(),order);
                request.getRequestDispatcher(JspPage.VIEW_ORDER.getPath()).forward(request, response);
            }
        } catch (ServiceException | ServletException | IOException e) {
            throw new CommandException(e);
        }
    }
}

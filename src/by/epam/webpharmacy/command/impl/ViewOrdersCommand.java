package by.epam.webpharmacy.command.impl;

import by.epam.webpharmacy.command.Command;
import by.epam.webpharmacy.command.CommandException;
import by.epam.webpharmacy.command.util.JspPage;
import by.epam.webpharmacy.command.util.Parameter;
import by.epam.webpharmacy.entity.Order;
import by.epam.webpharmacy.entity.User;
import by.epam.webpharmacy.service.ServiceException;
import by.epam.webpharmacy.service.order.OrderService;
import by.epam.webpharmacy.service.order.OrderServiceFactory;
import by.epam.webpharmacy.service.order.OrderServiceName;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class {@code ViewOrdersCommand} is an implementation of {@see Command}
 * for viewing different types of submitted orders for given user
 */
public class ViewOrdersCommand implements Command {

    private static OrderService orderService = OrderServiceFactory.getInstance().getService(OrderServiceName.ORDER_SERVICE);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        User user = (User)request.getSession().getAttribute(Parameter.USER.getName());
        String userIdString = request.getParameter(Parameter.USER_ID.getName());
        long userId;
        if (userIdString == null) {
            userId = user.getId();
        } else {
            userId = Long.parseLong(request.getParameter(Parameter.USER_ID.getName()));
        }
        boolean isCanceled = Boolean.parseBoolean(request.getParameter(Parameter.IS_CANCELED.getName()));
        List<Order> orderList;
        try {
            orderList = orderService.selectOrdersByUserId(user, userId, isCanceled);
            request.setAttribute(Parameter.ORDERS.getName(),orderList);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        try {
            request.getRequestDispatcher(JspPage.VIEW_ORDERS.getPath()).forward(request, response);
        } catch (ServletException | IOException e) {
            throw new CommandException(e);
        }
    }
}

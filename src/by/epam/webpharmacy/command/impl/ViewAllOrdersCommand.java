package by.epam.webpharmacy.command.impl;

import by.epam.webpharmacy.command.Command;
import by.epam.webpharmacy.command.CommandException;
import by.epam.webpharmacy.command.util.JspPage;
import by.epam.webpharmacy.command.util.Parameter;
import by.epam.webpharmacy.entity.Order;
import by.epam.webpharmacy.entity.OrderStatus;
import by.epam.webpharmacy.service.ServiceException;
import by.epam.webpharmacy.service.order.OrderService;
import by.epam.webpharmacy.service.order.OrderServiceFactory;
import by.epam.webpharmacy.service.order.OrderServiceName;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class {@code ViewAllOrdersCommand} is an implementation of {@see Command}
 * for viewing different types of submitted orders for all users by admin or manager
 */
public class ViewAllOrdersCommand implements Command {

    private static OrderService orderService = OrderServiceFactory.getInstance().getService(OrderServiceName.ORDER_SERVICE);
    private static final String EMPTY = "";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        List<String> orderStatusList = new ArrayList<>();
        int limit = Integer.parseInt(request.getParameter(Parameter.LIMIT.getName()));
        int offset = (Integer.parseInt(request.getParameter(Parameter.PAGE_NUMBER.getName())) - 1) * limit;
        for (OrderStatus orderStatus : OrderStatus.values()) {
            if (Boolean.parseBoolean(request.getParameter(orderStatus.getName()))) {
                orderStatusList.add(orderStatus.getStatus());
            } else {
                orderStatusList.add(EMPTY);
            }
        }
        boolean isCanceled = Boolean.parseBoolean(request.getParameter(Parameter.IS_CANCELED.getName()));
        try {
            List<Order> orderList = orderService.selectAllOrdersByStatus(orderStatusList, isCanceled, limit, offset);
            int numberOfOrders = orderService.countOrdersByStatus(orderStatusList, isCanceled);
            request.setAttribute(Parameter.ORDERS.getName(),orderList);
            request.setAttribute(Parameter.NUMBER_OF_ORDERS.getName(),numberOfOrders);
            request.getRequestDispatcher(JspPage.VIEW_ALL_ORDERS.getPath()).forward(request,response);
        } catch (ServiceException | ServletException | IOException e) {
            throw new CommandException(e);
        }


    }
}

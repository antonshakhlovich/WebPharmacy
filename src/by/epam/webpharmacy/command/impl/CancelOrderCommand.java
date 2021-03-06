package by.epam.webpharmacy.command.impl;

import by.epam.webpharmacy.command.Command;
import by.epam.webpharmacy.command.CommandException;
import by.epam.webpharmacy.command.util.Parameter;
import by.epam.webpharmacy.entity.User;
import by.epam.webpharmacy.service.ServiceException;
import by.epam.webpharmacy.service.order.OrderService;
import by.epam.webpharmacy.service.order.OrderServiceFactory;
import by.epam.webpharmacy.service.order.OrderServiceName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 *  Class {@code CancelOrderCommand} is an implementation of {@see Command}
 *  for cancel an earlier submitted order or restore earlier canceled order
 */
public class CancelOrderCommand implements Command {

    private static OrderService orderService = OrderServiceFactory.getInstance().getService(OrderServiceName.ORDER_SERVICE);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        boolean setCanceled = Boolean.parseBoolean(request.getParameter(Parameter.IS_CANCELED.getName()));
        long orderId = Long.parseLong(request.getParameter(Parameter.ORDER_ID.getName()));
        HttpSession session = request.getSession();
        try {
            if (orderService.cancelOrder((User)session.getAttribute(Parameter.USER.getName()), orderId, setCanceled)) {
                session.setAttribute(Parameter.SUCCESS_MESSAGE.getName(), Boolean.TRUE);
            } else {
                session.setAttribute(Parameter.ERROR_MESSAGE.getName(),Boolean.TRUE);
            }
            response.sendRedirect(request.getHeader(Parameter.REFERER.getName()));
        } catch (ServiceException | IOException e) {
            throw new CommandException(e);
        }


    }
}

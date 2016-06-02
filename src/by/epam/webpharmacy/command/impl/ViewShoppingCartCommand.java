package by.epam.webpharmacy.command.impl;

import by.epam.webpharmacy.command.Command;
import by.epam.webpharmacy.command.CommandException;
import by.epam.webpharmacy.command.util.JspPage;
import by.epam.webpharmacy.entity.User;
import by.epam.webpharmacy.service.order.OrderService;
import by.epam.webpharmacy.service.ServiceException;
import by.epam.webpharmacy.service.order.OrderServiceFactory;
import by.epam.webpharmacy.service.order.OrderServiceName;
import by.epam.webpharmacy.command.util.Parameter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Class {@code ViewShoppingCart} is a non-guest implementation of {@see Command}
 * for viewing shopping cart of logged in user
 */
public class ViewShoppingCartCommand implements Command {

    private static OrderService orderService =  OrderServiceFactory.getInstance().getService(OrderServiceName.ORDER_SERVICE);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Parameter.USER.getName());
        try {
            session.setAttribute(Parameter.SHOPPING_CART.getName(), orderService.selectShoppingCart(user.getId()));
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        try {
            request.getRequestDispatcher(JspPage.VIEW_SHOPPING_CART.getPath()).forward(request,response);
        } catch (ServletException | IOException e) {
            throw new CommandException(e);
        }
    }
}

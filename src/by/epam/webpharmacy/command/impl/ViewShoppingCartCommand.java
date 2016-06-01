package by.epam.webpharmacy.command.impl;

import by.epam.webpharmacy.command.Command;
import by.epam.webpharmacy.command.CommandException;
import by.epam.webpharmacy.entity.User;
import by.epam.webpharmacy.service.OrderService;
import by.epam.webpharmacy.service.ServiceException;
import by.epam.webpharmacy.service.impl.OrderServiceImpl;
import by.epam.webpharmacy.util.JspPage;
import by.epam.webpharmacy.util.Parameter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Class {@code ViewShoppingCart} is a non-guest implementation of {@see Command}
 * for viewing shopping cart of logged in user
 */
public class ViewShoppingCartCommand implements Command {

    private static OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Parameter.USER.getName());
        try {
            session.setAttribute(Parameter.SHOPPING_CART.getName(), orderService.selectShoppingCart(user.getId()));
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return ViewPageCommand.VIEW_PAGE + JspPage.VIEW_SHOPPING_CART;
    }
}
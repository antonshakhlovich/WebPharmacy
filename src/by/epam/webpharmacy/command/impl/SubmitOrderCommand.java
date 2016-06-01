package by.epam.webpharmacy.command.impl;

import by.epam.webpharmacy.command.Command;
import by.epam.webpharmacy.command.CommandException;
import by.epam.webpharmacy.service.OrderService;
import by.epam.webpharmacy.service.ServiceException;
import by.epam.webpharmacy.service.impl.OrderServiceImpl;
import by.epam.webpharmacy.util.JspPage;
import by.epam.webpharmacy.util.Parameter;

import javax.servlet.http.HttpServletRequest;

/**
 * Class {@code SubmitOrderCommand} is an implementation of {@see Command}
 * for submitting the current order
 */
public class SubmitOrderCommand implements Command{

    private static OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
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

        return ViewPageCommand.VIEW_PAGE + JspPage.VIEW_ORDERS;

    }
}

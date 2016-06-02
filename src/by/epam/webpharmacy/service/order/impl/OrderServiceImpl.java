package by.epam.webpharmacy.service.order.impl;

import by.epam.webpharmacy.dao.DaoException;
import by.epam.webpharmacy.dao.order.OrderDao;
import by.epam.webpharmacy.dao.order.OrderDaoFactory;
import by.epam.webpharmacy.dao.order.OrderDaoName;
import by.epam.webpharmacy.entity.Order;
import by.epam.webpharmacy.entity.OrderStatus;
import by.epam.webpharmacy.service.order.OrderService;
import by.epam.webpharmacy.service.ServiceException;

import java.util.List;

/**
 * A implementation of the {@link OrderService} interface
 */
public class OrderServiceImpl implements OrderService {

    private static OrderDao orderDao = OrderDaoFactory.getInstance().getDao(OrderDaoName.ORDER_DAO);

    @Override
    public Order selectShoppingCart(long userId) throws ServiceException {
        try {
            return orderDao.selectShoppingCart(userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> selectOrdersByUser(long userId) throws ServiceException {
        return null;
    }

    @Override
    public List<Order> selectAllOrders() throws ServiceException {
        return null;
    }

    @Override
    public List<Order> selectAllOrdersByStatus(OrderStatus orderStatus) throws ServiceException {
        return null;
    }

    @Override
    public boolean submitOrder(long orderId) throws ServiceException {
        try {
            return orderDao.submitOrder(orderId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean addItemToOrder(long itemId, int quantity, long orderId) throws ServiceException {
        try {
            return orderDao.insertItemToOrder(itemId, quantity, orderId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean removeItemFromOrder(long itemId, long orderId) throws ServiceException {
        try {
            return orderDao.deleteItemFromOrder(itemId, orderId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

}

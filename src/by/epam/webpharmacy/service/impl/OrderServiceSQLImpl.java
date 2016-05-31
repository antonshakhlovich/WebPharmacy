package by.epam.webpharmacy.service.impl;

import by.epam.webpharmacy.dao.DaoException;
import by.epam.webpharmacy.dao.OrderDao;
import by.epam.webpharmacy.dao.impl.OrderDaoSQLImpl;
import by.epam.webpharmacy.entity.Order;
import by.epam.webpharmacy.entity.OrderStatus;
import by.epam.webpharmacy.service.OrderService;
import by.epam.webpharmacy.service.ServiceException;

import java.util.List;

/**
 * {@inheritDoc}
 *
 * A singleton implementation of the {@link OrderService} interface, using OrderDao as an underlying level
 */
public class OrderServiceSQLImpl implements OrderService {

    private static OrderService instance = new OrderServiceSQLImpl();
    private static OrderDao orderDao = OrderDaoSQLImpl.getInstance();

    private OrderServiceSQLImpl() {
    }

    public static OrderService getInstance() {
        return instance;
    }

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
        return false;
    }

    @Override
    public boolean addItemToOrder(long itemId, int count, long userId) throws ServiceException {
        return false;
    }

    @Override
    public boolean removeItemFromOrder(long itemId, long userId) throws ServiceException {
        return false;
    }

    @Override
    public long selectOrderCustomerId(long orderId) throws ServiceException {
        return 0;
    }
}

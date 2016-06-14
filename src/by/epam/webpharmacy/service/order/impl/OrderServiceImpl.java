package by.epam.webpharmacy.service.order.impl;

import by.epam.webpharmacy.command.util.Parameter;
import by.epam.webpharmacy.dao.DaoException;
import by.epam.webpharmacy.dao.order.OrderDao;
import by.epam.webpharmacy.dao.order.OrderDaoFactory;
import by.epam.webpharmacy.dao.order.OrderDaoName;
import by.epam.webpharmacy.entity.Order;
import by.epam.webpharmacy.entity.OrderStatus;
import by.epam.webpharmacy.entity.User;
import by.epam.webpharmacy.entity.UserRole;
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
    public List<Order> selectOrdersByUserId(User user, long userId, boolean isCanceled) throws ServiceException {
        if (user.getId() == userId || user.getRole() == UserRole.ADMIN || user.getRole() == UserRole.MANAGER) {
            try {
                return orderDao.selectOrdersByUserId(userId, isCanceled);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        } else {
            return null;
        }
    }

    @Override
    public Order selectOrderByOrderId(long orderId, User user) throws ServiceException {
        try {
            Order order = orderDao.selectOrderByOrderId(orderId);
            if (order == null) {
                return null;
            } else {
                if (user.getId() != order.getOwner().getId()) {
                    if (user.getRole() != UserRole.ADMIN && user.getRole() != UserRole.MANAGER) {
                        order.setStatus(Parameter.ACCESS_DENIED.getName());
                    }
                }
            }
            return order;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> selectAllOrdersByStatus(List<String> statusList, boolean isCanceled, int limit, int offset) throws ServiceException {
        try {
            return orderDao.selectAllOrdersByStatus(statusList, isCanceled, limit, offset);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int countOrdersByStatus(List<String> statusList, boolean isCanceled) throws ServiceException {
        try {
            return orderDao.countOrdersByStatus(statusList, isCanceled);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
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
    public boolean cancelOrder(User user, long orderId, boolean setCanceled) throws ServiceException {
        try {
            Order order = orderDao.selectOrderByOrderId(orderId);
            if (setCanceled == order.isCanceled()) {
                return false;
            }
            if (user.getId() != order.getOwner().getId()) {
                if (user.getRole() != UserRole.ADMIN && user.getRole() != UserRole.MANAGER) {
                    return false;
                }
            }
            if (!order.getStatus().equalsIgnoreCase(OrderStatus.COMPLETED.getStatus())) { //dont allow user to cancel orders that have status completed
                return orderDao.cancelOrder(orderId, setCanceled);
            } else {
                return false;
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean updateOrderStatus(User user, String orderStatus, long orderId) throws ServiceException {
        if (user.getRole() == UserRole.ADMIN || user.getRole() == UserRole.MANAGER) {
            try {
                Order order = orderDao.selectOrderByOrderId(orderId);
                if (order.getStatus().equalsIgnoreCase(orderStatus)) {
                    return false;
                }
                return orderDao.updateOrderStatus(orderStatus, orderId);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return false;
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

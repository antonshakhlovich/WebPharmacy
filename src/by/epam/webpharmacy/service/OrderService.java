package by.epam.webpharmacy.service;

import by.epam.webpharmacy.entity.Order;
import by.epam.webpharmacy.entity.OrderStatus;

import java.util.List;

/**
 * Represents an interface of a service providing order-related actions
 */
public interface OrderService {

    /**
     * Selects shopping cart (as order with status 'open') of the user with the given id
     * @param userId id of the order's owner
     * @return a corresponding {@link Order} object
     * @throws ServiceException if exception occurred on an underlying level
     */
    Order selectShoppingCart(long userId) throws ServiceException;

    /**
     * Returns a list of {@link Order} orders of a specified user
     * @param userId id of the user, owning the requested orders
     * @return list of user's orders
     * @throws ServiceException if exception occurred on an underlying level
     */
    List<Order> selectOrdersByUser(long userId) throws ServiceException;

    /**
     * Selects all orders made by all users
     * @return a list of all orders by all users
     * @throws ServiceException if exception occurred on an underlying level
     */
    List<Order> selectAllOrders() throws ServiceException;

    /**
     * Selects a list of all orders with given status by all users
     * @return a list of orders
     * @throws ServiceException if exception occurred on an underlying level
     */
    List<Order> selectAllOrdersByStatus(OrderStatus orderStatus) throws ServiceException;

    /**
     * Submits an order with a specified id to the system. Date and time of submission are saved as creation date of the order.
     * After submission the order can't be modified, it can only be canceled.
     * @param orderId id of the order to submit
     * @return {@code true} if submitted successfully, {@code false} if order doesn't contain any items or failed to be submitted
     * @throws ServiceException if exception occurred on an underlying level
     */
    boolean submitOrder(long orderId) throws ServiceException;

    /**
     * Adds a specified count of items with a given id to the order, identified by id
     * @param itemId id of the item to add
     * @param count count of items
     * @param userId id of the order to add to
     * @return {@code true} if added successfully
     * @throws ServiceException if exception occurred on an underlying level
     */
    boolean addItemToOrder(long itemId, int count, long userId) throws ServiceException;

    /**
     * Removes all items with a given id from the specified order
     * @param itemId id of the item
     * @param userId id of the user
     * @return {@code true} if removed successfully
     * @throws ServiceException if exception occurred on an underlying level
     */
    boolean removeItemFromOrder(long itemId, long userId) throws ServiceException;

    /**
     * Selects id of the user, owning the order
     * @param orderId id of the order
     * @return id of the user, owning the order
     * @throws ServiceException if exception occurred on an underlying level
     */
    long selectOrderCustomerId(long orderId) throws ServiceException;



}

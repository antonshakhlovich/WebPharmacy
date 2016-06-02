package by.epam.webpharmacy.service.order;

import by.epam.webpharmacy.entity.Order;
import by.epam.webpharmacy.entity.OrderStatus;
import by.epam.webpharmacy.entity.User;
import by.epam.webpharmacy.service.ServiceException;

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
     * @param isCanceled defines what type of orders to select canceled or not
     * @return list of user's orders
     * @throws ServiceException if exception occurred on an underlying level
     */
    List<Order> selectOrdersByUser(long userId, boolean isCanceled) throws ServiceException;

    /**
     * Retrieves an order with given id
     *
     * @param orderId id of the order
     * @param user User that request this order
     * @return order or {@code null} if no such order
     * or special Order object with status variable 'ACCESS DENIED' if user don't have permission for viewing this order
     * @throws ServiceException if failed to retrieve data from dao layer
     */
    Order selectOrderByOrderId(long orderId, User user) throws ServiceException;

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
     * Submits an order with a specified id to the system. Date of submission are saved as creation date of the order.
     * Also final prices and total amount saved to the order.
     * After submission the order can't be modified, it can only be canceled.
     * @param orderId id of the order to submit
     * @return {@code true} if submitted successfully, {@code false} if order doesn't contain any items or failed to be submitted
     * @throws ServiceException if exception occurred on an underlying level
     */
    boolean submitOrder(long orderId) throws ServiceException;

    /**
     * Adds a specified quantity of items with a given id to the order, identified by id
     * @param itemId id of the item to add
     * @param quantity quantity of items
     * @param orderId id of the order to add to
     * @return {@code true} if added successfully
     * @throws ServiceException if exception occurred on an underlying level
     */
    boolean addItemToOrder(long itemId, int quantity, long orderId) throws ServiceException;

    /**
     * Removes all items with a given id from the specified order
     * @param itemId id of the item
     * @param orderId id of the order
     * @return {@code true} if removed successfully
     * @throws ServiceException if exception occurred on an underlying level
     */
    boolean removeItemFromOrder(long itemId, long orderId) throws ServiceException;


}

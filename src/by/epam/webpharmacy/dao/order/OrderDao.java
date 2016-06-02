package by.epam.webpharmacy.dao.order;

import by.epam.webpharmacy.dao.DaoException;
import by.epam.webpharmacy.entity.Order;
import by.epam.webpharmacy.entity.OrderStatus;

import java.util.List;

/**
 * Represents an interface for retrieving order-related data. Contains all methods, required for getting such data from the
 * storage, e.g. database
 */
public interface OrderDao {

    /**
     * Retrieves a list of all user's orders
     *
     * @param userId     id of the user
     * @param isCanceled defines what type of orders to select canceled or not
     * @return list of user's orders or {@code null} if user has none
     * @throws DaoException if failed to retrieve data from the storage due to technical problems
     */
    List<Order> selectOrdersByUserId(long userId, boolean isCanceled) throws DaoException;

    /**
     * Retrieves an order with given id
     *
     * @param orderId     id of the order
     * @return order or {@code null} if no such order
     * @throws DaoException if failed to retrieve data from the storage due to technical problems
     */
    Order selectOrderByOrderId(long orderId) throws DaoException;

    /**
     * Create new shopping cart if user doesn't have one.
     *
     * @param userId id of the user, owning the shopping cart
     * @return true if shopping cart successfully made or false due to some technical problems
     * @throws DaoException if failed to retrieve data from the storage due to technical problems
     */
    boolean createShoppingCart(long userId) throws DaoException;

    /**
     * Retrieves current(unsubmitted) order for a specified user. Kind of shopping cart
     *
     * @param userId id of the user, owning the order
     * @return user's current shopping cart
     * @throws DaoException if failed to retrieve data from the storage due to technical problems
     */
    Order selectShoppingCart(long userId) throws DaoException;

    /**
     * Removes all items with a specified id from given order
     *
     * @param itemId  id of the item to remove
     * @param orderId id of the order
     * @return {@code true} if removed successfully, {@code false} if delete failed
     * @throws DaoException if failed to retrieve data from the storage due to technical problems
     */
    boolean deleteItemFromOrder(long itemId, long orderId) throws DaoException;

    /**
     * Adds an item with a specified id of a specified quantity to a given order
     *
     * @param itemId   id of the item to add
     * @param quantity quantity of items
     * @param orderId  id of the order
     * @return {@code true} if inserted successfully, {@code false} if insert failed
     * @throws DaoException if failed to retrieve data from the storage due to technical problems
     */
    boolean insertItemToOrder(long itemId, int quantity, long orderId) throws DaoException;

    /**
     * Mark a specified order as canceled
     *
     * @param orderId id of the order to cancel
     * @return {@code true} if canceled successfully, {@code false} if cancel failed
     * @throws DaoException if failed to retrieve data from the storage due to technical problems
     */
    boolean cancelOrder(long orderId) throws DaoException;

    /**
     * Submit order based on shopping cart
     *
     * @param orderId is id of shopping cart
     * @return {@code true} if updated successfully, {@code false} if update failed
     * @throws DaoException if failed to retrieve data from the storage due to technical problems
     */
    boolean submitOrder(long orderId) throws DaoException;


    /**
     * Update status of given order
     *
     * @param orderStatus is orderStatus to update
     * @param orderId     is id of order
     * @return {@code true} if updated successfully, {@code false} if update failed
     * @throws DaoException if failed to retrieve data from the storage due to technical problems
     */
    boolean updateOrderStatus(OrderStatus orderStatus, long orderId) throws DaoException;

    /**
     * Update prices in order based on current prices from storage
     *
     * @param orderId is id of order
     * @return {@code true} if updated successfully, {@code false} if update failed
     * @throws DaoException if failed to retrieve data from the storage due to technical problems
     */
    boolean updateOrderPrices(long orderId) throws DaoException;

    /**
     * Update order amount based on items prices from order
     *
     * @param orderId is id of order
     * @return {@code true} if updated successfully, {@code false} if update failed
     * @throws DaoException if failed to retrieve data from the storage due to technical problems
     */
    boolean updateOrderAmount(long orderId) throws DaoException;

}
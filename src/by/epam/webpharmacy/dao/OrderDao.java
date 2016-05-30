package by.epam.webpharmacy.dao;

import by.epam.webpharmacy.entity.Order;

import java.util.List;

/**
 * Represents an interface for retrieving order-related data. Contains all methods, required for getting such data from the
 * storage, e.g. database
 */
public interface OrderDao {

    /**
     * Retrieves a list of all user's orders
     *
     * @param userId id of the user
     * @return list of user's orders or {@code null} if user has none
     * @throws DaoException if failed to retrieve data from the storage due to technical problems
     */

    List<Order> selectOrdersByUserId(long userId) throws DaoException;

    /**
     * Create new shopping cart if user doesn't have one.
     * @param userId id of the user, owning the shopping cart
     * @return true if shopping cart successfully made or false due to some technical problems
     * @throws DaoException if failed to retrieve data from the storage due to technical problems
     */
    boolean createShoppingCart(long userId) throws DaoException;

    /**
     * Retrieves current(unsubmitted) order for a specified user. Kind of shopping cart
     * @param userId id of the user, owning the order
     * @return user's current shopping cart
     * @throws DaoException if failed to retrieve data from the storage due to technical problems
     */
    Order selectShoppingCart(long userId) throws DaoException;

}
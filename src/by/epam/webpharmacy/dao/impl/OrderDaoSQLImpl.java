package by.epam.webpharmacy.dao.impl;

import by.epam.webpharmacy.dao.DaoException;
import by.epam.webpharmacy.dao.ItemDao;
import by.epam.webpharmacy.dao.OrderDao;
import by.epam.webpharmacy.dao.UserDao;
import by.epam.webpharmacy.dao.util.ConnectionPool;
import by.epam.webpharmacy.dao.util.ConnectionPoolException;
import by.epam.webpharmacy.entity.Item;
import by.epam.webpharmacy.entity.Order;
import by.epam.webpharmacy.entity.OrderStatus;
import by.epam.webpharmacy.util.Parameter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * This implementation of {@see OrderDao} interface based on JDBC and MySQL. It is also a singleton.
 */

public class OrderDaoSQLImpl implements OrderDao {

    private static final String SELECT_SHOPPING_CART = "SELECT o.id, o.customer_id, d.id AS drug_id, d.label, d.dosage, " +
            "d.volume, d.volume_type,d.by_prescription,d.image_path, dro.quantity, d.price  \n" +
            "  FROM `orders` o  \n" +
            "  LEFT JOIN `drugs_ordered` dro \n" +
            "  ON o.id = dro.order_id\n" +
            "  LEFT JOIN `drugs` d ON dro.drug_id = d.id\n" +
            "  WHERE o.customer_id = ? AND o.status = \"открыт\" AND o.is_canceled = 0";

    private static final String CREATE_SHOPPING_CART = "INSERT INTO orders" +
            "  (id, customer_id, is_canceled)" +
            "  VALUES (0, ?, 0);";
    private static final String DELETE_ITEM_FROM_ORDER = "DELETE FROM `drugs_ordered` " +
            "WHERE" +
            "  order_id = ? " +
            "  AND drug_id = ?";
    private static final String INSERT_ITEM_TO_ORDER = "INSERT INTO drugs_ordered (order_id,drug_id,quantity) " +
            "VALUES (?,?,?) " +
            "ON DUPLICATE KEY UPDATE quantity = quantity + VALUES(quantity)";
    private static final String SUBMIT_ORDER = "UPDATE orders \n" +
            "SET\n" +
            "date = NOW()\n" +
            ",status =  'в работе'\n" +
            "WHERE\n" +
            "  id = ?";
    private static final String UPDATE_ORDER_PRICES = "UPDATE drugs_ordered do\n" +
            "  JOIN drugs d ON do.drug_id = d.id\n" +
            "  JOIN orders o ON do.order_id = o.id\n" +
            "  SET do.price = d.price\n" +
            "  WHERE o.id = ?";
    private static final String UPDATE_ORDER_AMOUNT = "UPDATE orders o\n" +
            "  JOIN (SELECT  d.order_id, SUM(d.quantity*d.price) AS amount FROM drugs_ordered d\n" +
            "  WHERE d.order_id = ?) AS do ON o.id = do.order_id\n" +
            "  SET o.amount = do.amount\n";

    private static OrderDaoSQLImpl ourInstance = new OrderDaoSQLImpl();

    public static OrderDaoSQLImpl getInstance() {
        return ourInstance;
    }

    private OrderDaoSQLImpl() {
    }

    @Override
    public List<Order> selectOrdersByUserId(long userId) throws DaoException {
        return null;
    }

    @Override
    public boolean createShoppingCart(long userId) throws DaoException {
        Connection cn = null;
        PreparedStatement preparedStatement = null;
        try {
            cn = ConnectionPool.getInstance().getConnection();
            preparedStatement = cn.prepareStatement(CREATE_SHOPPING_CART);
            preparedStatement.setLong(1, userId);
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResources(cn, preparedStatement);
        }
    }

    @Override
    public Order selectShoppingCart(long userId) throws DaoException {
        Order order = new Order();
        Connection cn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            cn = ConnectionPool.getInstance().getConnection();
            preparedStatement = cn.prepareStatement(SELECT_SHOPPING_CART);
            preparedStatement.setLong(1, userId);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                if (!createShoppingCart(userId)) {
                    throw new DaoException("Can't create shopping cart");
                } else {
                    resultSet = preparedStatement.executeQuery();
                }
            }
            resultSet.next();
            order.setId(resultSet.getLong(Parameter.ID.getName()));
            order.setCustomerId(resultSet.getLong(Parameter.CUSTOMER_ID.getName()));
            do {
                Item item = new Item();
                item.setId(resultSet.getLong(Parameter.DRUG_ID.getName()));
                if (resultSet.wasNull()) {
                    break;
                }
                item.setPrice(resultSet.getBigDecimal(Parameter.PRICE.getName()));
                item.setLabel(resultSet.getString(Parameter.LABEL.getName()));
                item.setDosage(resultSet.getString(Parameter.DOSAGE.getName()));
                item.setVolumeType(resultSet.getString(Parameter.VOLUME_TYPE.getName()));
                item.setVolume(resultSet.getDouble(Parameter.VOLUME.getName()));
                item.setByPrescription(resultSet.getBoolean(Parameter.BY_PRESCRIPTION.getName()));
                item.setImagePath(resultSet.getString(Parameter.IMAGE_PATH.getName()));
                order.getItems().put(item, resultSet.getInt(Parameter.QUANTITY.getName()));
            } while (resultSet.next());
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResources(cn, preparedStatement, resultSet);
        }
        return order;
    }

    @Override
    public boolean deleteItemFromOrder(long itemId, long orderId) throws DaoException {
        Connection cn = null;
        PreparedStatement preparedStatement = null;
        try {
            cn = ConnectionPool.getInstance().getConnection();
            preparedStatement = cn.prepareStatement(DELETE_ITEM_FROM_ORDER);
            preparedStatement.setLong(1, orderId);
            preparedStatement.setLong(2, itemId);
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResources(cn, preparedStatement);
        }
    }

    @Override
    public boolean insertItemToOrder(long itemId, int quantity, long orderId) throws DaoException {
        Connection cn = null;
        PreparedStatement preparedStatement = null;
        try {
            cn = ConnectionPool.getInstance().getConnection();
            preparedStatement = cn.prepareStatement(INSERT_ITEM_TO_ORDER);
            preparedStatement.setLong(1, orderId);
            preparedStatement.setLong(2, itemId);
            preparedStatement.setInt(3, quantity);
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResources(cn, preparedStatement);
        }
    }

    @Override
    public boolean cancelOrder(long orderId) throws DaoException {
        return false;
    }

    @Override
    public boolean submitOrder(long orderId) throws DaoException {
        Connection cn = null;
        PreparedStatement submitOrder = null;
        PreparedStatement updatePrices = null;
        PreparedStatement updateTotal = null;
        try {
            cn = ConnectionPool.getInstance().getConnection();
            cn.setAutoCommit(false);
            submitOrder = cn.prepareStatement(SUBMIT_ORDER);
            updatePrices = cn.prepareStatement(UPDATE_ORDER_PRICES);
            updateTotal = cn.prepareStatement(UPDATE_ORDER_AMOUNT);
            submitOrder.setLong(1, orderId);
            submitOrder.executeUpdate();
            updatePrices.setLong(1, orderId);
            updatePrices.executeUpdate();
            updateTotal.setLong(1, orderId);
            updateTotal.executeUpdate();
            cn.commit();
            return true;
        } catch (ConnectionPoolException | SQLException e) {
            if (cn != null) {
                try {
                    cn.rollback();
                    return false;
                } catch (SQLException e1) {
                    throw new DaoException(e);
                }
            }
            throw new DaoException(e);
        } finally {
            try {
                if (submitOrder != null) {
                    submitOrder.close();
                }
                if (updatePrices != null) {
                    updatePrices.close();
                }
                if (updateTotal != null) {
                    updateTotal.close();
                }
            } catch (SQLException e) {
                throw new DaoException("Can't close prepared statement", e);
            }
            if (cn != null) {
                try {
                    cn.setAutoCommit(true);
                    ConnectionPool.getInstance().releaseConnection(cn);
                } catch (ConnectionPoolException | SQLException e) {
                    throw new DaoException("Can't release connection to connection pool or set auto commit", e);
                }
            }
        }
    }

    @Override
    public boolean updateOrderStatus(OrderStatus orderStatus, long orderId) throws DaoException {
        return false;
    }

    @Override
    public boolean updateOrderPrices(long orderId) throws DaoException {
        Connection cn = null;
        PreparedStatement preparedStatement = null;
        try {
            cn = ConnectionPool.getInstance().getConnection();
            preparedStatement = cn.prepareStatement(UPDATE_ORDER_PRICES);
            preparedStatement.setLong(1, orderId);
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResources(cn, preparedStatement);
        }
    }

    @Override
    public boolean updateOrderAmount(long orderId) throws DaoException {
        Connection cn = null;
        PreparedStatement preparedStatement = null;
        try {
            cn = ConnectionPool.getInstance().getConnection();
            preparedStatement = cn.prepareStatement(UPDATE_ORDER_AMOUNT);
            preparedStatement.setLong(1, orderId);
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResources(cn, preparedStatement);
        }
    }

    private void closeResources(Connection connection, PreparedStatement preparedStatement) throws DaoException {
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (SQLException e) {
            throw new DaoException("Can't close prepared statement", e);
        }
        if (connection != null) {
            try {
                ConnectionPool.getInstance().releaseConnection(connection);
            } catch (ConnectionPoolException e) {
                throw new DaoException("Can't release connection to connection pool", e);
            }
        }
    }

    private void closeResources(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) throws DaoException {
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            throw new DaoException("Can't close prepared statement or result set", e);
        }
        if (connection != null) {
            try {
                ConnectionPool.getInstance().releaseConnection(connection);
            } catch (ConnectionPoolException e) {
                throw new DaoException("Can't release connection to connection pool", e);
            }
        }
    }
}

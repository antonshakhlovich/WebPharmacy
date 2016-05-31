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

    private static final String SELECT_SHOPPING_CART = "SELECT o.id, o.customer_id, o.timestamp, o.amount, " +
            "o.status,o.is_canceled, do.drug_id, d.label, d.dosage_form_id, d. dosage_form_name, " +
            "  d.dosage, d.volume, d.volume_type,d.manufacturer_id,d.manufacturer_name,d.by_prescription," +
            "d.description,d.image_path, do.quantity, d.price  " +
            "  FROM orders o  " +
            "  LEFT JOIN drugs_ordered do ON o.id = do.order_id" +
            "  LEFT JOIN (SELECT d.id,d.label,d.dosage_form_id, ddf.name as dosage_form_name, d.dosage, " +
            "  d.volume, d.volume_type, d.manufacturer_id, CONCAT(c.type,' \"',c.name,'\" (',c.country,')') " +
            "  AS manufacturer_name,d.price,d.by_prescription,d.description,d.image_path " +
            "  FROM drugs d" +
            "  LEFT JOIN drugs_dosage_forms ddf ON d.dosage_form_id = ddf.id" +
            "  LEFT JOIN companies c ON d.manufacturer_id = c.id) AS d " +
            "  ON do.drug_id = d.id" +
            "  WHERE o.customer_id = ? AND o.status = 'открыт' AND o.is_canceled = 0";

    private static final String CREATE_SHOPPING_CART = "INSERT INTO orders" +
            "  (id, customer_id, is_canceled)" +
            "  VALUES (0, ?, 0);";

    private static OrderDaoSQLImpl ourInstance = new OrderDaoSQLImpl();
    private static ItemDao itemDao = ItemDaoSQLImpl.getInstance();
    private static UserDao userDao = UserDaoSQLImpl.getInstance();

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
            preparedStatement.setLong(1,userId);
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
            preparedStatement.setLong(1,userId);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                if(!createShoppingCart(userId)){
                    throw new DaoException("Can't create shopping cart");
                } else {
                    resultSet = preparedStatement.executeQuery();
                }
            }
            resultSet.next();
            order.setId(resultSet.getLong(Parameter.ID.getName()));
            order.setCustomerId(resultSet.getLong(Parameter.CUSTOMER_ID.getName()));
            order.setTimestamp(resultSet.getTimestamp(Parameter.TIMESTAMP.getName()));
            order.setAmount(resultSet.getBigDecimal(Parameter.AMOUNT.getName()));
            order.setOrderStatus(resultSet.getString(Parameter.STATUS.getName()));
            order.setCanceled(resultSet.getBoolean(Parameter.IS_CANCELED.getName()));
            do {
                Item item = new Item();
                item.setId(resultSet.getLong(Parameter.DRUG_ID.getName()));
                if (resultSet.wasNull()) {
                    break;
                }
                item.setPrice(resultSet.getBigDecimal(Parameter.PRICE.getName()));
                item.setLabel(resultSet.getString(Parameter.LABEL.getName()));
                item.setDosageFormId(resultSet.getLong(Parameter.DOSAGE_FORM_ID.getName()));
                item.setDosageFormName(resultSet.getString(Parameter.DOSAGE_FORM_NAME.getName()));
                item.setDosage(resultSet.getString(Parameter.DOSAGE.getName()));
                item.setVolumeType(resultSet.getString(Parameter.VOLUME_TYPE.getName()));
                item.setVolume(resultSet.getDouble(Parameter.VOLUME.getName()));
                item.setManufacturerId(resultSet.getLong(Parameter.MANUFACTURER_ID.getName()));
                item.setManufacturerName(resultSet.getString(Parameter.MANUFACTURER_NAME.getName()));
                item.setByPrescription(resultSet.getBoolean(Parameter.BY_PRESCRIPTION.getName()));
                item.setDescription(resultSet.getString(Parameter.DESCRIPTION.getName()));
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
        return false;
    }

    @Override
    public boolean insertItemToOrder(long itemId, int count, long orderId) throws DaoException {
        return false;
    }

    @Override
    public boolean cancelOrder(long orderId) throws DaoException {
        return false;
    }

    @Override
    public boolean updateOrderStatus(OrderStatus orderStatus, long orderId) throws DaoException {
        return false;
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

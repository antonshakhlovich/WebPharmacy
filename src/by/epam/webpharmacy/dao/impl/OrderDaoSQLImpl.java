package by.epam.webpharmacy.dao.impl;

import by.epam.webpharmacy.dao.DaoException;
import by.epam.webpharmacy.dao.ItemDao;
import by.epam.webpharmacy.dao.OrderDao;
import by.epam.webpharmacy.dao.util.ConnectionPool;
import by.epam.webpharmacy.dao.util.ConnectionPoolException;
import by.epam.webpharmacy.entity.Item;
import by.epam.webpharmacy.entity.Order;
import by.epam.webpharmacy.util.Parameter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This implementation of {@see OrderDao} interface based on JDBC and MySQL. It is also a singleton.
 */

public class OrderDaoSQLImpl implements OrderDao {

    private static final String SELECT_SHOPPING_CART = "SELECT o.id, o.customer_id, o.timestamp, o.amount, o.status, " +
            "o.is_canceled, d.id AS drug_id,\n" +
            "  do.quantity, do.price,d.label,d.dosage_form_id, ddf.name as dosage_form_name, d.dosage, \n" +
            "  d.volume, d.volume_type, d.manufacturer_id, CONCAT(c.type,' \"',c.name,'\" (',c.country,')')\n" +
            "  AS manufacturer,d.by_prescription,d.description,d.image_path\n" +
            "  FROM orders o\n" +
            "  JOIN drugs_ordered do ON o.id = do.order_id\n" +
            "  JOIN drugs d ON do.drug_id = d.id\n" +
            "  LEFT JOIN drugs_dosage_forms ddf ON d.dosage_form_id = ddf.id\n" +
            "  LEFT JOIN companies c ON d.manufacturer_id = c.id\n" +
            "  WHERE o.customer_id = ? AND o.status = 'открыт' AND o.is_canceled = 0";

    private static final String CREATE_SHOPPING_CART = "INSERT INTO orders\n" +
            "  (id, customer_id, status, is_canceled)\n" +
            "  VALUES (0, ?, 'открыт', 0);";

    private static OrderDaoSQLImpl ourInstance = new OrderDaoSQLImpl();
    private static ItemDao itemDao = ItemDaoSQLImpl.getInstance();

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
            preparedStatement = cn.prepareStatement(SELECT_SHOPPING_CART);
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
            preparedStatement = cn.prepareStatement(CREATE_SHOPPING_CART);
            preparedStatement.setLong(1,userId);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                if(!createShoppingCart(userId)){
                    throw new DaoException("Can't create shopping cart");
                } else {
                    resultSet = preparedStatement.executeQuery();
                }
            }
            Map<Item, Integer> items = new HashMap<>();
            resultSet.next();
            order.setId(resultSet.getLong(Parameter.ID.getName()));
            order.setCustomerId(resultSet.getLong(Parameter.CUSTOMER_ID.getName()));
            order.setTimeStamp(resultSet.getDate(Parameter.TIMESTAMP.getName()));
            order.setAmount(resultSet.getBigDecimal(Parameter.AMOUNT.getName()));
            order.setOrderStatus(resultSet.getString(Parameter.STATUS.getName()));
            order.setCanceled(resultSet.getBoolean(Parameter.IS_CANCELED.getName()));
            do {
                Item item = new Item();
                item.setId(resultSet.getLong(Parameter.DRUG_ID.getName()));
                item.setPrice(resultSet.getBigDecimal(Parameter.PRICE.getName()));
                item.setImagePath(resultSet.getString(Parameter.IMAGE_PATH.getName()));
                item.setDescription(resultSet.getString(Parameter.DESCRIPTION.getName()));
                item.setByPrescription(resultSet.getBoolean(Parameter.BY_PRESCRIPTION.getName()));
                item.setManufacturerId(resultSet.getLong(Parameter.MANUFACTURER_ID.getName()));
                item.setVolumeType(resultSet.getString(Parameter.VOLUME_TYPE.getName()));
                item.setVolume(resultSet.getDouble(Parameter.VOLUME.getName()));
                item.setLabel(resultSet.getString(Parameter.LABEL.getName()));
                item.setDosageFormId(resultSet.getLong(Parameter.DOSAGE_FORM_ID.getName()));
                item.setDosage(resultSet.getString(Parameter.DOSAGE.getName()));
                item.setDosageFormName(resultSet.getString(Parameter.DOSAGE_FORM_NAME.getName()));
                item.setManufacturerName(resultSet.getString(Parameter.MANUFACTURER_NAME.getName()));
                items.put(item, resultSet.getInt(Parameter.QUANTITY.getName()));
            } while (resultSet.next());
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResources(cn, preparedStatement, resultSet);
        }
        return order;
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

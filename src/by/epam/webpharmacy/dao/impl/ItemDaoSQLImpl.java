package by.epam.webpharmacy.dao.impl;

import by.epam.webpharmacy.dao.DaoException;
import by.epam.webpharmacy.dao.ItemDao;
import by.epam.webpharmacy.dao.util.ConnectionPool;
import by.epam.webpharmacy.dao.util.ConnectionPoolException;
import by.epam.webpharmacy.entity.DosageForm;
import by.epam.webpharmacy.entity.Item;
import by.epam.webpharmacy.util.Parameter;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This is an implementation of the {@see ItemDao} interface.
 */
public class ItemDaoSQLImpl implements ItemDao {

    private static final String SELECT_DOSAGE_FORMS = "SELECT id, name FROM drugs_dosage_forms";
    private static final String SELECT_VOLUME_TYPES = "SHOW COLUMNS FROM drugs LIKE 'volume_type'";
    private static final String SELECT_ITEM_BY_ID = "SELECT id,label, dosage_form_id, dosage, volume, volume_type, " +
            "manufacturer_id, Price, by_prescription, description, image_path FROM drugs WHERE id = ?";
    private static final String SELECT_ITEM_BY_LABEL_DOSAGE_VOLUME = "SELECT id,label, dosage_form_id, dosage, volume, " +
            "volume_type, manufacturer_id, Price, by_prescription, description, image_path " +
            "FROM drugs WHERE label = ? AND dosage = ? AND volume = ?";
    private static final String INSERT_ITEM = "INSERT INTO drugs(id, label, dosage_form_id, dosage, volume, " +
            "volume_type, manufacturer_id, price, by_prescription, description, image_path) " +
            "VALUES(0 , ?, ?, ? , ?, ?, ?, ?, ?, ?,?)";
    private static final String REGEXP_VOLUME_TYPE = "\'([^']*)\'";

    private static ItemDao instance = new ItemDaoSQLImpl();

    public static ItemDao getInstance() {
        return instance;
    }

    private ItemDaoSQLImpl() {
    }


    @Override
    public List<DosageForm> getDosageForms() throws DaoException {
        List<DosageForm> dosageForms = new ArrayList<>();
        Connection cn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            cn = ConnectionPool.getInstance().getConnection();
            preparedStatement = cn.prepareStatement(SELECT_DOSAGE_FORMS);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    DosageForm dosageForm = new DosageForm();
                    dosageForm.setId(resultSet.getLong(1));
                    dosageForm.setName(resultSet.getString(2));
                    dosageForms.add(dosageForm);
                }
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResources(cn, preparedStatement, resultSet);
        }
        return dosageForms;
    }

    @Override
    public List<String> getVolumeTypes() throws DaoException {
        List<String> volumeTypes = new ArrayList<>();
        Connection cn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            cn = ConnectionPool.getInstance().getConnection();
            preparedStatement = cn.prepareStatement(SELECT_VOLUME_TYPES);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            String input = resultSet.getString(2);
            Pattern pattern = Pattern.compile(REGEXP_VOLUME_TYPE);
            Matcher matcher = pattern.matcher(input);
            while (matcher.find()) {
                volumeTypes.add(matcher.group(1));
            }
            return volumeTypes;
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResources(cn, preparedStatement, resultSet);
        }

    }

    @Override
    public Item selectItemById(long id) throws DaoException {
        Item item = new Item();
        Connection cn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            cn = ConnectionPool.getInstance().getConnection();
            preparedStatement = cn.prepareStatement(SELECT_ITEM_BY_ID);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                return null;
            }
            resultSet.next();
            setItemParameters(item, resultSet);
            return item;

        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResources(cn, preparedStatement, resultSet);
        }
    }

    @Override
    public Item selectItemByLabelDosageVolume(String label, String dosage, double volume) throws DaoException {
        Item item = new Item();
        Connection cn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            cn = ConnectionPool.getInstance().getConnection();
            preparedStatement = cn.prepareStatement(SELECT_ITEM_BY_LABEL_DOSAGE_VOLUME);
            preparedStatement.setString(1, label);
            preparedStatement.setString(2, dosage);
            preparedStatement.setDouble(3, volume);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                return null;
            }
            resultSet.next();
            setItemParameters(item, resultSet);
            return item;

        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResources(cn, preparedStatement, resultSet);
        }
    }
    @Override
    public boolean insertItem(Item item) throws DaoException {
        Connection cn = null;
        PreparedStatement preparedStatement = null;
        try{
            cn = ConnectionPool.getInstance().getConnection();
            preparedStatement = cn.prepareStatement(INSERT_ITEM);
            preparedStatement.setString(1, item.getLabel());
            preparedStatement.setLong(2, item.getDosageFormId());
            preparedStatement.setString(3, item.getDosage());
            preparedStatement.setDouble(4, item.getVolume());
            preparedStatement.setString(5, item.getVolumeType());
            preparedStatement.setLong(6, item.getManufacturerId());
            preparedStatement.setBigDecimal(7, item.getPrice());
            preparedStatement.setBoolean(8,item.isByPrescription());
            preparedStatement.setString(9,item.getDescription());
            preparedStatement.setString(10,item.getImagePath());
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch(MySQLIntegrityConstraintViolationException e){
            return false;
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        } finally{
            closeResources(cn, preparedStatement);
        }
    }

    private void setItemParameters(Item item, ResultSet resultSet) throws SQLException {
        item.setId(resultSet.getLong(Parameter.ID.getName()));
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
